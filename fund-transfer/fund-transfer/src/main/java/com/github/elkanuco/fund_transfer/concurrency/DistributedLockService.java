package com.github.elkanuco.fund_transfer.concurrency;

import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import com.github.elkanuco.fund_transfer.dtos.OperationDto;
import com.github.elkanuco.fund_transfer.exceptions.AccountLockedException;
import com.github.elkanuco.fund_transfer.services.OperationsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DistributedLockService {

    private final RedissonClient redissonClient;
    private final OperationsService operationsService;

    public DistributedLockService(RedissonClient redissonClient, OperationsService operationsService) {
        this.redissonClient = redissonClient;
        this.operationsService = operationsService;
    }

    public void processWithMultipleLocks(OperationDto dto) throws Exception {
        String baseLockKey = "lock:account:" + dto.getBaseAccountId();
        String targetLockKey = dto.getTargetAccountId() != null ? "lock:account:" + dto.getTargetAccountId() : null;
        List<RLock> locks = new ArrayList<>();
        locks.add(redissonClient.getLock(baseLockKey));
        if (targetLockKey != null) {
            locks.add(redissonClient.getLock(targetLockKey));
        }
        locks.sort(Comparator.comparing(RLock::getName));
        RedissonMultiLock multiLock = new RedissonMultiLock(locks.toArray(new RLock[0]));
        boolean locked = false;
        try {
            locked = multiLock.tryLock(10, 30, TimeUnit.SECONDS);
            if (!locked) {
                throw new AccountLockedException("Unable to acquire lock after 30 seconds");
            }
            operationsService.process(dto);
        } finally {
            if (locked) {
                multiLock.unlock();
            }
        }
    }
}


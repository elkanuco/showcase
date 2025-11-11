package lu.elkanuco.deltaBridge.component;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lu.elkanuco.common.dto.ProjectDto;
import lu.elkanuco.deltaBridge.service.ProjectRedisService;

@Component
public class LoadingProjectsWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private RedisTemplate<String, ProjectDto> redisTemplate;
    

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	Set<Object> keys = redisTemplate.opsForHash().keys(ProjectRedisService.PROJECTS_HASH);

        if (keys != null) {
            for (Object key : keys) {
                session.sendMessage(new TextMessage(key.toString()));
            }
        }
    }
    
}


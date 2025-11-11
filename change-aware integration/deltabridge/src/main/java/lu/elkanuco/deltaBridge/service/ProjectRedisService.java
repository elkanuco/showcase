package lu.elkanuco.deltaBridge.service;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lu.elkanuco.common.dto.ProjectDto;


@Service
public class ProjectRedisService {

    public static final String PROJECTS_HASH = "projects";

    private final RedisTemplate<String, ProjectDto> redisTemplate;

    public ProjectRedisService(RedisTemplate<String, ProjectDto> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveProject(ProjectDto project) {
        redisTemplate.opsForHash().put(PROJECTS_HASH, project.id().toString(), project);
    }

    public ProjectDto getProject(String id) {
        return (ProjectDto) redisTemplate.opsForHash().get(PROJECTS_HASH, id);
    }

    public List<ProjectDto> getAllProjects() {
        return redisTemplate.opsForHash()
                .values(PROJECTS_HASH)
                .stream()
                .map(obj -> (ProjectDto) obj)
                .toList();
    }

    public void deleteProject(String id) {
        redisTemplate.opsForHash().delete(PROJECTS_HASH, id);
    }

    public void deleteAllProjects() {
        redisTemplate.delete(PROJECTS_HASH);
    }
}

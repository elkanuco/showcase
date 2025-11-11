package lu.elkanuco.deltaBridge.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lu.elkanuco.common.dto.ProjectDto;
import lu.elkanuco.deltaBridge.component.UpdateProjectWebSocketHandler;

@RequiredArgsConstructor
@Service
public class ProjectEventListener {

    private final ProjectRedisService projectRedisService;
    private final UpdateProjectWebSocketHandler webSocketHandler;


    @KafkaListener(topics = "${app.kafka.topic.project-events}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ProjectDto projectDto) {
        projectRedisService.saveProject(projectDto);
        
        webSocketHandler.onProjectUpdate(projectDto);
        
        System.out.println("Project saved to Redis: " + projectDto.id());
    }
}


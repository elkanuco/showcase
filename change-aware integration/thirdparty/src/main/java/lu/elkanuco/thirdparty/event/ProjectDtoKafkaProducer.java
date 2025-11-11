package lu.elkanuco.thirdparty.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lu.elkanuco.common.dto.ProjectDto;

@RequiredArgsConstructor
@Service
public class ProjectDtoKafkaProducer {
	private final KafkaTemplate<String, ProjectDto> kafkaTemplate;
	
	@Value("${app.kafka.topic.project-events}")
	private String projectEventsTopic;

    public void sendProjectEvent(ProjectDto projectDto) {
        String key = projectDto.id().toString(); 
        kafkaTemplate.send(projectEventsTopic, key, projectDto);
    }
}


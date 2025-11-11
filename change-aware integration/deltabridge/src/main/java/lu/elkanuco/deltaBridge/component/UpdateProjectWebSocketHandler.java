package lu.elkanuco.deltaBridge.component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import lu.elkanuco.common.dto.ProjectDto;
import lu.elkanuco.deltaBridge.service.ProjectRedisService;

@Slf4j
@Component
public class UpdateProjectWebSocketHandler extends TextWebSocketHandler {

	@Autowired
	private ProjectRedisService projectRedisService;

	private final ObjectMapper objectMapper = new ObjectMapper();

	private final ConcurrentHashMap<String, CopyOnWriteArraySet<WebSocketSession>> subscriptions = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("MESSAGE");
		String projectId = message.getPayload();
		log.info("##################### MESSAGE"+projectId);
		if (projectId == null || projectId.isEmpty()) {
			return; 
		}
		subscriptions.computeIfAbsent(projectId, k -> new CopyOnWriteArraySet<>()).add(session);

		ProjectDto projectDto = projectRedisService.getProject(projectId);
		if (projectDto != null) {
			sendProjectDto(session, projectDto);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
		subscriptions.values().forEach(set -> set.remove(session));
	}

	public void onProjectUpdate(ProjectDto projectDto) {
		String projectId = projectDto.id().toString();
		CopyOnWriteArraySet<WebSocketSession> sessions = subscriptions.get(projectId);
		if (sessions != null) {
			for (WebSocketSession session : sessions) {
				try {
					sendProjectDto(session, projectDto);
				} catch (IOException e) {
				}
			}
		}
	}

	private void sendProjectDto(WebSocketSession session, ProjectDto projectDto) throws IOException {
		String json = objectMapper.writeValueAsString(projectDto);
		session.sendMessage(new TextMessage(json));
	}
}

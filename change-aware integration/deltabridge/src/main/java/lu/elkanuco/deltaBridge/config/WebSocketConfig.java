package lu.elkanuco.deltaBridge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;
import lu.elkanuco.deltaBridge.component.LoadingProjectsWebSocketHandler;
import lu.elkanuco.deltaBridge.component.UpdateProjectWebSocketHandler;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final LoadingProjectsWebSocketHandler loadProjectsHandler;
	private final UpdateProjectWebSocketHandler updateProjectsHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(loadProjectsHandler, "/ws/projects")
            .setAllowedOrigins("*");
        
        registry.addHandler(updateProjectsHandler, "/ws/projects/update")
        .setAllowedOrigins("*");
    }
}


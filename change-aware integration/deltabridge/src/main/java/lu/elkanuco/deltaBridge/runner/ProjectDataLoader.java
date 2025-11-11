package lu.elkanuco.deltaBridge.runner;

import lu.elkanuco.common.dto.ProjectDto;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lu.elkanuco.deltaBridge.feign.ThirdPartyClient;
import lu.elkanuco.deltaBridge.service.ProjectRedisService;

@RequiredArgsConstructor
@Component
public class ProjectDataLoader implements ApplicationRunner {

    private final ThirdPartyClient thirdPartyClient;
    private final ProjectRedisService projectRedisService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<ProjectDto> projects = thirdPartyClient.getAllProjects();
        projects.forEach( p -> projectRedisService.saveProject(p));
    }
}


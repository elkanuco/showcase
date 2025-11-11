package lu.elkanuco.deltaBridge.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import lu.elkanuco.common.dto.ProjectDto;

@FeignClient(name = "thirdparty", url = "${thirdparty.api.url}")
public interface ThirdPartyClient {

    @GetMapping("/api/projects")
    List<ProjectDto> getAllProjects();
}

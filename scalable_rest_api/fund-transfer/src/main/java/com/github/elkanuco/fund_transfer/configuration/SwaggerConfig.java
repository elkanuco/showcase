package com.github.elkanuco.fund_transfer.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi dataRestApiGroup() {
		return GroupedOpenApi.builder().group("data-rest-api").pathsToMatch("/datarestapi/**").build();
	}

	@Bean
	public GroupedOpenApi basicCrudGroup() {
		return GroupedOpenApi.builder().group("basic-crud").pathsToMatch("/basiccrud/**").build();
	}

	@Bean
	public GroupedOpenApi apiGroup() {
		return GroupedOpenApi.builder().group("operations").pathsToMatch("/operations/**").build();
	}
}

package lu.elkanuco.deltaBridge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lu.elkanuco.common.dto.ProjectDto;

@Configuration
public class RedisConfig {
	
	@Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;
	
	
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
	    return new LettuceConnectionFactory(redisHost, redisPort);
	}

	
	@Bean
    public RedisTemplate<String, ProjectDto> redisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, ProjectDto> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        Jackson2JsonRedisSerializer<ProjectDto> serializer = new Jackson2JsonRedisSerializer<>(ProjectDto.class);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }


}

package com.mykare.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.1"),
security = {
		@SecurityRequirement(name = "basicAuth")
	},
servers = {
		@Server(url = "/",description = "Default Server URL")
	}
)
@SecuritySchemes({
	@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP,scheme = "basic")
})
@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}


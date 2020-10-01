package co.org.karianov.transportmanagementapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private Contact buildApiContact() {
		return new Contact("Kevin Alexandre Riaño Vargas", "", "karianov@correo.udistrital.edu.co");
	}

	private ApiInfo buildApiInfo() {
		// @formatter:off
		return new ApiInfoBuilder().title("App for transport management")
				.description("RESTful API for this app.")
				.version("1.0")
				.termsOfServiceUrl("URL for terms of service")
				.contact(buildApiContact())
				.license("Licencia de uso")
				.licenseUrl("License URL")
				.build();
		// @formatter:on
	}

	@Bean
	public Docket buildSwaggerDocumentation() {
		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("co.org.karianov.transportmanagementapi.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(buildApiInfo());
		// @formatter:on
	}

}

package com.example.firebase.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30).select().apis(RequestHandlerSelectors.basePackage("com.example.firebase.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		Contact contact = new Contact("Amal jeev s", "https://amaljeevs.netlify.app/", "amaljeev3739@gmail.com");
		return new ApiInfoBuilder().title("FireBase FireStore CRUD Api Documentaion")
				.description("API reference")
				.termsOfServiceUrl("https://sample.com/")
				.contact(contact ).license("AntSpace License")
				.licenseUrl("https://amaljeevs.netlify.app/").version("1.0").build();
	}
}

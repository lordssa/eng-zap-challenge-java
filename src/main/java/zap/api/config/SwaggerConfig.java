package zap.api.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.PathSelectors;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public Docket buildingApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			   .select()
			   .apis(RequestHandlerSelectors.basePackage("zap.api"))
			   .paths(PathSelectors.any())
			   .build()
			   .apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		ApiInfo info = new ApiInfo("API REST GRUPO ZAP"
				                   , "Consulta de imóveis elegíveis para o ZAP e Viva Real",
				                   "1.0",
				                   "https://grupozap.github.io/cultura/challenges/engineering.html", 
				                   "Cid Soares - cidssa@gmail.com", "", "");
		return info;
	}
}

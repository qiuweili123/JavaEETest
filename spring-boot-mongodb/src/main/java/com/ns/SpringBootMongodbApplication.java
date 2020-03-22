package com.ns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

@SpringBootApplication
public class SpringBootMongodbApplication  implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbApplication.class, args);
	}

	@Override
	public void customize(UndertowServletWebServerFactory factory) {
		factory.addBuilderCustomizers((UndertowBuilderCustomizer) builder -> {
			builder.addHttpListener(8080, "0.0.0.0");
		});
	}
}


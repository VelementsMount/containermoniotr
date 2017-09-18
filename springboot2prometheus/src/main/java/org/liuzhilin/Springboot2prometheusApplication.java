package org.liuzhilin;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@EnableAsync
@SpringBootApplication
public class Springboot2prometheusApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2prometheusApplication.class, args);
	}
}

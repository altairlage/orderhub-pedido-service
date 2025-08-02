package com.fiap.orderhub.orderhub_pedido_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.fiap.orderhub.orderhub_pedido_service.configurations.feign")
public class OrderhubPedidoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderhubPedidoServiceApplication.class, args);
	}

}

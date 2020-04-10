package com.association;

import com.association.service.ConfigService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@MapperScan("com.association.mapper")
@ComponentScan(value = "com.association")
public class AssociationApplication {
	public static void main(String[] args) {
		SpringApplication.run(AssociationApplication.class, args);
	}

}

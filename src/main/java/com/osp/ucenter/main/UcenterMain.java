package com.osp.ucenter.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 2017/08/02
 * @author fly
 *
 */
//@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.osp.ucenter")
public class UcenterMain {

	public static void main(String[] args) {
		SpringApplication.run(UcenterMain.class, args);
	}
   
}

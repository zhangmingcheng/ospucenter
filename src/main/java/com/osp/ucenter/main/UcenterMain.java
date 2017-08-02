package com.osp.ucenter.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 2017/08/02
 * @author fly
 *
 */
@Controller
@EnableAutoConfiguration
public class UcenterMain {

	public static void main(String[] args) {
		SpringApplication.run(UcenterMain.class, args);
	}
	
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}

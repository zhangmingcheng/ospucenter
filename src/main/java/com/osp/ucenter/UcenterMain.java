	package com.osp.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.osp.ucenter.filter.CrossDomainFilter;
import com.osp.ucenter.filter.Initfilter;
import com.osp.ucenter.filter.SecurityFilter;

/**
 * 2017/08/02
 * @author fly
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class UcenterMain {

	public static void main(String[] args) {
		SpringApplication.run(UcenterMain.class, args);
	}
	
	@Bean
    public FilterRegistrationBean crossDomainFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CrossDomainFilter());
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("Initfilter");
        registration.setOrder(100);
        return registration;
    }
	
	
	@Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Initfilter());
//        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("Initfilter");
        registration.setOrder(101);
        return registration;
    }
	
	@Bean
    public FilterRegistrationBean securityFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SecurityFilter());
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("SecurityFilter");
        registration.setOrder(102);
        return registration;
    }

}

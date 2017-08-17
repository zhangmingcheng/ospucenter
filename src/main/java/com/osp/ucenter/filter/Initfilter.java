package com.osp.ucenter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class Initfilter implements Filter {

	Logger logger = Logger.getLogger(Initfilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	/**
	 * 初始化 系统环境变量
	 * URI
	 * 配置参数
	 * EAI
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		arg0.getServletContext();
		logger.info("=============ospUcenter init=============");
	}

}

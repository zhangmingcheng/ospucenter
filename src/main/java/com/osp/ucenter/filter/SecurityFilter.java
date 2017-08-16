package com.osp.ucenter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 权限控制
 * 
 * @author fly
 *
 */
public class SecurityFilter implements Filter {

	Logger logger = Logger.getLogger(SecurityFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 1. 检查用户是否已登录
//        User user = (User) request.getSession().getAttribute("user");

        // 2. 没登录，登录去
//        if (user == null) {
//            request.setAttribute("message", "请先登录！！！");
//            request.getRequestDispatcher("/message.jsp").forward(request, response);
//            return;
//        }

        // 3. 得到用户想访问的资源
        String uri = request.getRequestURI();

        // 4. 得到访问该资源需要的权限
//        SecurityService service = new SecurityService();
//        Resource r = service.findResource(uri);
        /*
         * 你要访问的资源，我在权限管理系统里面没有说访问这个资源需要权限，
         * 也即这个资源不需要被权限系统控制，只有被权限系统控制的资源，在数据库里面
         * 才有，如果为null，这个资源不受权限系统控制。
         */
//        if (r == null) {
//            chain.doFilter(request, response);
//            return;
//        }
//        Privilege required_Privilege = r.getPrivilege(); // 得到访问资源需要的权限

        // 5. 判断用户是否有相应权限
//        List<Privilege> list = service.getUserAllPrivilege(user.getId()); // 得到用户所有权限
//        if (!list.contains(required_Privilege)) {
//            // 6. 没有权限，则提示用户权限不足，联系管理员
//            request.setAttribute("message", "对不起，您没有权限，请联系管理员！！！");
//            request.getRequestDispatcher("/message.jsp").forward(request, response);
//            return;
//        }

        // 7. 如果有，则则放行  允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
		logger.info("=============SecurityFilter dofilter=============");

        chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("=============SecurityFilter init=============");
	}

}
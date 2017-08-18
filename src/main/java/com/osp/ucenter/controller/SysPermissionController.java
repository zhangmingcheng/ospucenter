package com.osp.ucenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osp.ucenter.service.UcPermissionService;

/**
 * 权限控制类
 * @author zhangmingcheng
 */
@Controller
@Scope(value="prototype")
@RequestMapping("permission")
// extends BaseController
public class SysPermissionController {
	
	@Autowired
	UcPermissionService ucPermissionService;
	
}


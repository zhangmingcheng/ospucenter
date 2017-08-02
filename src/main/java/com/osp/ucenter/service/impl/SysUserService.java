package com.osp.ucenter.service.impl;

import com.osp.ucenter.persistence.model.SysUser;
import com.osp.ucenter.persistence.dao.SysUserMapper;
import com.osp.ucenter.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zivy
 * @since 2017-08-02
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	
}

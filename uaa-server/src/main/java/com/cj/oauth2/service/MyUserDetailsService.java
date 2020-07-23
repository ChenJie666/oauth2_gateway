package com.cj.oauth2.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cj.oauth2.dao.MyUserDetailsMapper;
import com.cj.oauth2.entities.MyUserDetails;
import com.cj.oauth2.entities.TbUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/23 14:45
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private MyUserDetailsMapper myUserDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUser user = myUserDetailsMapper.findUserByUsername(username);
        List<String> roleCodes = myUserDetailsMapper.findRoleByUsername(username);
        List<String> permissions = myUserDetailsMapper.findPermissionByRoles(roleCodes);

        permissions.addAll(roleCodes);

        MyUserDetails myUserDetails = BeanUtil.copyProperties(user, MyUserDetails.class);
        myUserDetails.setAuthorities(AuthorityUtils.createAuthorityList(String.join(",",permissions)));

        return myUserDetails;
    }
}

package com.cj.oauth2.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cj.oauth2.dao.MyUserDetailsMapper;
import com.cj.oauth2.entities.MyUserDetails;
import com.cj.oauth2.entities.TbRole;
import com.cj.oauth2.entities.TbUser;
import org.assertj.core.util.Lists;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<TbRole> roles = myUserDetailsMapper.findRoleByUsername(username);

        ArrayList<String> roleNames = Lists.newArrayList();
        ArrayList<String> roleEnnames = Lists.newArrayList();
        roles.forEach(role -> {
            roleNames.add(role.getName());
            roleEnnames.add(role.getEnname());
        });

        List<String> permissions = myUserDetailsMapper.findPermissionByRoles(roleEnnames);

        permissions.addAll(roleEnnames);

        MyUserDetails myUserDetails = BeanUtil.copyProperties(user, MyUserDetails.class);
        String userStr = new JSONObject(user.setPassword(null).setRoles(roleNames), true).toString();
        System.out.println(userStr);
        myUserDetails.setUsername(userStr);
        myUserDetails.setAuthorities(AuthorityUtils.createAuthorityList(String.join(",", permissions)));
        System.out.println("*****myUserDetails:" + myUserDetails);

        return myUserDetails;
    }
}

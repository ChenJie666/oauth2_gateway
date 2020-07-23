package com.cj.oauth2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cj.oauth2.entities.TbRole;
import com.cj.oauth2.entities.TbUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/23 14:46
 */
@Component
public interface MyUserDetailsMapper {

    @Select("SELECT * " +
            "FROM tb_user " +
            "WHERE username = #{username}")
    TbUser findUserByUsername(@Param("username") String username);

    @Select("SELECT r.name,r.enname " +
            "FROM tb_role r " +
            "LEFT JOIN tb_user_role ur ON ur.role_id=r.id " +
            "LEFT JOIN tb_user u ON u.id=ur.user_id " +
            "WHERE u.username=#{username} ")
    List<TbRole> findRoleByUsername(@Param("username") String username);

    @Select("<script>" +
            "SELECT p.url " +
            "FROM tb_permission p " +
            "LEFT JOIN tb_role_permission rp ON rp.permission_id=p.id " +
            "LEFT JOIN tb_role r ON r.id=rp.role_id " +
            "WHERE r.enname IN " +
            "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'> " +
            "#{roleCode} " +
            "</foreach>" +
            "</script>")
    List<String> findPermissionByRoles(@Param("roleCodes") List<String> roleCodes);

}

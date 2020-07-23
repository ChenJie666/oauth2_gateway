package com.cj.oauth2.entities;

import javax.persistence.*;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/23 14:48
 */
@Entity
public class TbUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long roleId;

}

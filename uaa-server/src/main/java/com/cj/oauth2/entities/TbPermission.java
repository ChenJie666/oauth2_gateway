package com.cj.oauth2.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/23 14:48
 */
@Entity
public class TbPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long parentId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String enname;
    @Column(unique = true)
    private String url;
    private String description;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated;

}

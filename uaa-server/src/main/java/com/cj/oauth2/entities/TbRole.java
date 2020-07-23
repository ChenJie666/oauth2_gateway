package com.cj.oauth2.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2020/7/23 14:48
 */
@Entity
public class TbRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long parentId;
    private String name;
    private String enname;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

}

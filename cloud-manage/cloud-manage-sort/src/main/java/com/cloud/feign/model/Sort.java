package com.cloud.feign.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 分类
 *
 * @author dayun_wang
 */
@Data
@Entity
@Table(name = "sort")
public class Sort implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sortId;
    private Integer sortType;
    private Timestamp createTime;

}

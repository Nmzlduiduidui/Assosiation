package com.association.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author baozi
 * @version 1.0
 * @date 2020/4/9 20:59
 */
@Data
public class PrimaryKeyUserRole implements Serializable {

    /** 岗位ID */
    private Long roleId;

    /** 用户ID */
    private Long userId;

}
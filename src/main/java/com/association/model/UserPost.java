package com.association.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户和岗位关联 sys_user_post
 * 
 * @author baozi
 */
@Entity
@Data
@IdClass(PrimaryKeyUserPost.class)
@Table(name="userPost")

public class UserPost
{
    /** 用户ID */
    @Id
    private Long userId;
    
    /** 岗位ID */
    @Id
    private Long postId;

}

package com.association.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="type")
public class Type {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long typeId;

    private String name;



/*
    @OneToMany(mappedBy = "type",fetch = FetchType.EAGER)  //type主动维护blog的关系
    private List<Blog> blogs = new ArrayList<>();
*/


}

package com.association.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="tag")
public class Tag {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long tagId;

    private String tagName;


/*
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
*/

}

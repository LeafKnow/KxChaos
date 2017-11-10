package com.yq.action.model;

//@Entity
public class User {
//    @Id
    private Long id; 
    private String name; 
//    @Transient
    private int tempUsageCount; // not persisted  
}

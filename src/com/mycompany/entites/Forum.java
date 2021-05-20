/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class Forum {
    private int id,idc;
    private String email,name,description,comments,image;
      private ArrayList<comments> Posts;
    public Forum() {
    }

    public Forum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Forum( String name, String description,int id) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    

    public Forum(int id, int idc, String email, String name, String description, String comments, String image, ArrayList<comments> Posts) {
        this.id = id;
        this.idc = idc;
        this.email = email;
        this.name = name;
        this.description = description;
        this.comments = comments;
        this.image = image;
        this.Posts = Posts;
    }

    public Forum(String email, String name, String description, String comments, String image) {
        this.email = email;
        this.name = name;
        this.description = description;
        this.comments = comments;
        this.image = image;
    }

    
    
    public Forum(int id, String email, String name, String description, String comments, String image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.description = description;
        this.comments = comments;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
}

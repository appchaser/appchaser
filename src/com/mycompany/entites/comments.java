/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;


/**import java.util.Date;
import java.time.*;
*/

/**
 *
 * @author pc
 */
public class comments {
    private int id,idf;
    private String autor,content,forum;
    //private Date created_at=java.sql.Date.valueOf(LocalDate.now());

    public comments(int id, String autor, String content, String forum) {
        this.id = id;
        this.autor = autor;
        this.content = content;
        this.forum = forum;
     
    }

    public comments() {
    }

    public comments(String autor, String content, String forum) {
        this.autor = autor;
        this.content = content;
        this.forum = forum;
    }

    public comments( String autor, String content,int idf) {
        this.idf = idf;
        this.autor = autor;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getForum() {
        return forum;
    }

    public void setForum(String forum) {
        this.forum = forum;
    }

    
    
    
}


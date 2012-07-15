package com.siu.android.twok.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 15/07/12
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class Comment implements Serializable{

    private String content;
    private String date;
    private String auteur;

    public Comment() {
    }

    public Comment (String content, String date, String auteur){
        this.content = content;
        this.auteur = auteur;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}

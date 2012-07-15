package com.siu.android.twok.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 23/05/12
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
public class Idea implements Serializable {

    private String id;
    private String nickname;
    private String name;
    private String category;
    private String content;
    private String date;
    private Long already;
    private Long dontwant;
    private Long want;

    public Idea() {
    }

    public Idea( String nickname, String name,String content) {
        this.nickname = nickname;
        this.name = name;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAlready() {
        return already;
    }

    public void setAlready(Long already) {
        this.already = already;
    }

    public Long getDontwant() {
        return dontwant;
    }

    public void setDontwant(Long dontwant) {
        this.dontwant = dontwant;
    }

    public Long getWant() {
        return want;
    }

    public void setWant(Long want) {
        this.want = want;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

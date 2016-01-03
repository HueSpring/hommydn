
package dn.hommy.entity;

import java.util.Date;


public class Ads {
    
    private int idads;
    private String username;
    private String subject;
    private String content;
    private String link;
    private String image;
    private String type_ads;
    private Date time_request;
    private int check;
    private Date time_begin;
    private Date time_end;
    private String username_check;
    private Date time_check;

    public Ads() {
    }

    public int getIdads() {
        return idads;
    }

    public void setIdads(int idads) {
        this.idads = idads;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType_ads() {
        return type_ads;
    }

    public void setType_ads(String type_ads) {
        this.type_ads = type_ads;
    }

    public Date getTime_request() {
        return time_request;
    }

    public void setTime_request(Date time_request) {
        this.time_request = time_request;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public Date getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(Date time_begin) {
        this.time_begin = time_begin;
    }

    public Date getTime_end() {
        return time_end;
    }

    public void setTime_end(Date time_end) {
        this.time_end = time_end;
    }

    public String getUsername_check() {
        return username_check;
    }

    public void setUsername_check(String username_check) {
        this.username_check = username_check;
    }

    public Date getTime_check() {
        return time_check;
    }

    public void setTime_check(Date time_check) {
        this.time_check = time_check;
    }
    
}

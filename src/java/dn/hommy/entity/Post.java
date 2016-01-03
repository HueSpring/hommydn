
package dn.hommy.entity;

import java.util.Date;


public class Post {

    private int idpost;
    private String username;
    private String type_topic;
    private String type_request;
    private String subject;
    private String content;
    private String district;
    private String wards;
    private String address;
    private String image_1;
    private String image_2;
    private String image_3;
    private String area;
    private String cost;
    private Date time_post;
    private int hide;
    private int check;
    private String username_check;
    private Date time_check;
    private int rank;

    public Post() {
    }

    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType_topic() {
        return type_topic;
    }

    public void setType_topic(String type_topic) {
        this.type_topic = type_topic;
    }

    public String getType_request() {
        return type_request;
    }

    public void setType_request(String type_request) {
        this.type_request = type_request;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage_1() {
        return image_1;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }

    public String getImage_3() {
        return image_3;
    }

    public void setImage_3(String image_3) {
        this.image_3 = image_3;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Date getTime_post() {
        return time_post;
    }

    public void setTime_post(Date time_post) {
        this.time_post = time_post;
    }

    public int getHide() {
        return hide;
    }

    public void setHide(int hide) {
        this.hide = hide;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
}

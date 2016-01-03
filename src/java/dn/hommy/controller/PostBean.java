//post + save post
package dn.hommy.controller;

import dn.hommy.dao.PostDao;
import dn.hommy.entity.Post;
import dn.hommy.entity.SavePost;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PostBean {

    private Post post = new Post();
    private SavePost savePost = new SavePost();
    private String message;
    private String messageAjax;

    public PostBean() {
    }

    //-------------------------------------------------FIND----------------------------------------------------------------
    //find all post by home
    //find posts by home rent
    //find posts by home buy
    //find posts by home lease
    //find posts by home sell
    //find all post by apartment
    //find posts by apartment rent
    //find posts by apartment buy
    //find posts by apartment lease
    //find posts by apartment sell
    //find all post by motel
    //find posts by motel rent
    //find posts by motel lease
    //find posts by username
    
    
    //use for mod to check post
    //find posts hide = 0, check = 0
    public ArrayList<Post> getPostsShowNoCheck() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PostDao dao = new PostDao();
        return dao.findPostsShowNoCheckDao();
    }

    //use to show post to member & visitor
    //find posts hide = 0, check = 1
    public ArrayList<Post> getPostsShowCheck() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PostDao dao = new PostDao();
        return dao.findPostsShowCheckDao();
    }
    
    
    //-------------------------------------------------CHECK----------------------------------------------------------------
    //check post is same
    //

    //------------------------------------------------CREATE-----------------------------------------------------------------
    //create new post
    public void createPost() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Date date = new Date();
        post.setTime_post(date);
        PostDao dao = new PostDao();
        dao.createPostDao(post.getUsername(), post.getType_topic(), post.getType_request(),
                post.getSubject(), post.getContent(), post.getDistrict(), post.getWards(), post.getAddress(),
                post.getImage_1(), post.getImage_2(), post.getImage_3(), post.getArea(), post.getCost(), post.getTime_post());
    }

    //-----------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------COUNT----------------------------------------------------------------
    //count posts by username
    //count posts by home
    //count posts by aparterment
    //count posts by motel
    //------------------------------------------------SET & GET-----------------------------------------------------------------
    public String getMember_username() {
        return savePost.getMember_username();
    }

    public void setMember_username(String member_username) {
        savePost.setMember_username(member_username);
    }

    public int getPost_idpost() {
        return savePost.getPost_idpost();
    }

    public void setPost_idpost(int post_idpost) {
        savePost.setPost_idpost(post_idpost);
    }

    public Date getTime_save() {
        return savePost.getTime_save();
    }

    public void setTime_save(Date time_save) {
        savePost.setTime_save(time_save);
    }

    public int getIdpost() {
        return post.getIdpost();
    }

    public void setIdpost(int idpost) {
        post.setIdpost(idpost);
    }

    public String getUsername() {
        return post.getUsername();
    }

    public void setUsername(String username) {
        post.setUsername(username);
    }

    public String getType_topic() {
        return post.getType_topic();
    }

    public void setType_topic(String type_topic) {
        post.setType_topic(type_topic);
    }

    public String getType_request() {
        return post.getType_request();
    }

    public void setType_request(String type_request) {
        post.setType_request(type_request);
    }

    public String getContent() {
        return post.getContent();
    }

    public void setContent(String content) {
        post.setContent(content);
    }

    public String getDistrict() {
        return post.getDistrict();
    }

    public void setDistrict(String district) {
        post.setDistrict(district);
    }

    public String getWards() {
        return post.getWards();
    }

    public void setWards(String wards) {
        post.setWards(wards);
    }

    public String getAddress() {
        return post.getAddress();
    }

    public void setAddress(String address) {
        post.setAddress(address);
    }

    public String getImage_1() {
        return post.getImage_1();
    }

    public void setImage_1(String image_1) {
        post.setImage_1(image_1);
    }

    public String getImage_2() {
        return post.getImage_2();
    }

    public void setImage_2(String image_2) {
        post.setImage_2(image_2);
    }

    public String getImage_3() {
        return post.getImage_3();
    }

    public void setImage_3(String image_3) {
        post.setImage_3(image_3);
    }

    public String getSubject() {
        return post.getSubject();
    }

    public void setSubject(String subject) {
        post.setSubject(subject);
    }

    public String getArea() {
        return post.getArea();
    }

    public void setArea(String area) {
        post.setArea(area);
    }

    public String getCost() {
        return post.getCost();
    }

    public void setCost(String cost) {
        post.setCost(cost);
    }

    public Date getTime_post() {
        return post.getTime_post();
    }

    public void setTime_post(Date time_post) {
        post.setTime_post(time_post);
    }

    public int getHide() {
        return post.getHide();
    }

    public void setHide(int hide) {
        post.setHide(hide);
    }

    public int getCheck() {
        return post.getCheck();
    }

    public void setCheck(int check) {
        post.setCheck(check);
    }

    public String getUsername_check() {
        return post.getUsername_check();
    }

    public void setUsername_check(String username_check) {
        post.setUsername_check(username_check);
    }

    public Date getTime_check() {
        return post.getTime_check();
    }

    public void setTime_check(Date time_check) {
        post.setTime_check(time_check);
    }

    public int getRank() {
        return post.getRank();
    }

    public void setRank(int rank) {
        post.setRank(rank);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public SavePost getSavePost() {
        return savePost;
    }

    public void setSavePost(SavePost savePost) {
        this.savePost = savePost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageAjax() {
        return messageAjax;
    }

    public void setMessageAjax(String messageAjax) {
        this.messageAjax = messageAjax;
    }

}

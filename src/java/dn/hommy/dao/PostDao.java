//post + save post
package dn.hommy.dao;

import dn.hommy.connect.ConnectionFactory;
import dn.hommy.entity.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PostDao {

    public PostDao() {
    }

    //---------------------------------------------------CREATE--------------------------------------------------------------
    //create new post
    public boolean createPostDao(String username, String type_topic, String type_request, String subject, String content,
            String district, String wards, String address, String image1, String image2, String image3,
            String area, String cost, Date time_post) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO post (username, type_topic, type_request, subject, content, "
                    + "district, wards, address, image1, image2, image3, area, cost, time_post)"
                    + "VALUES ('" + username + "','" + type_topic + "','" + type_request + "','" + subject + "','" + content + "','"
                    + district + "','" + wards + "','" + address + "','" + image1 + "','" + image2 + "','" + image3 + "','"
                    + area + "','" + cost + "','" + toStringDateTime(time_post) + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //============================================SAVE POST - CREATE=====================================================================
    //create save post
    public boolean createSavePostDao(String member_username, int post_idpost, Date time_save) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO post (mmeber_username, post_idpost, time_save)"
                    + "VALUES ('" + member_username + "','" + post_idpost + "','" + toStringDateTime(time_save) + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    //---------------------------------------------------UPDATE/EDIT--------------------------------------------------------------
    //hide post
    public boolean hidePostDao(int idpost) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE post SET hide = '1' WHERE idpost = '" + idpost + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //show post
    public boolean showPostDao(int idpost) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE post SET hide = '0' WHERE idpost = '" + idpost + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update check post -  hide = 0, check = 1, username_check, time_check
    public boolean checkPostDao(int idpost, String username_check, Date time_check) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE post SET hide = '0', check = '1', username_check = '" + username_check + "',  "
                    + "time_check = '" + toStringDateTime(time_check) + "' WHERE idpost = '" + idpost + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //edit post
    public boolean editPostDao(int idpost, String username, String type_topic, String type_request, String subject, String content,
            String district, String wards, String address, String image1, String image2, String image3,
            String area, String cost, Date time_post) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE post SET username = '" + username + "', type_topic = '" + type_topic
                    + "' type_request = '" + type_request + "' subject = '" + subject + "', content = '" + content
                    + "', district = '" + district + "', wards = '" + wards + "', address = '" + address
                    + "', image1 = '" + image1 + "', image2 = '" + image2 + "', image3 = '" + image3
                    + "', area = '" + area + "', cost = '" + cost + "', time_post = '" + toStringDateTime(time_post) + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //---------------------------------------------------DELETE--------------------------------------------------------------
    //delete 1 post
    //delete n post
    //---------------------------------------------------FIND--------------------------------------------------------------
    //find post by district
    public ArrayList<Post> findPostsByDistrictDao(String district) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM post WHERE district = '" + district + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Post> list = new ArrayList<>();
                while (rs.next()) {
                    Post entity = new Post();
                    entity.setIdpost(rs.getInt("idpost"));
                    entity.setUsername(rs.getString("username"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setType_request(rs.getString("type_request"));
                    entity.setSubject(rs.getString("subject"));
                    entity.setContent(rs.getString("content"));
                    entity.setDistrict(rs.getString("district"));
                    entity.setWards(rs.getString("wards"));
                    entity.setAddress(rs.getString("address"));
                    entity.setImage_1(rs.getString("image_1"));
                    entity.setImage_2(rs.getString("image_2"));
                    entity.setImage_3(rs.getString("image_3"));
                    entity.setArea(rs.getString("area"));
                    entity.setCost(rs.getString("cost"));
                    entity.setTime_post(rs.getDate("time_post"));
                    entity.setHide(rs.getInt("hide"));
                    entity.setCheck(rs.getInt("check"));
                    entity.setUsername_check(rs.getString("username_check"));
                    entity.setTime_check(rs.getDate("time_check"));
                    entity.setRank(rs.getInt("rank"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find post by username
    public ArrayList<Post> findPostsByUsernameDao(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM post WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Post> list = new ArrayList<>();
                while (rs.next()) {
                    Post entity = new Post();
                    entity.setIdpost(rs.getInt("idpost"));
                    entity.setUsername(rs.getString("username"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setType_request(rs.getString("type_request"));
                    entity.setSubject(rs.getString("subject"));
                    entity.setContent(rs.getString("content"));
                    entity.setDistrict(rs.getString("district"));
                    entity.setWards(rs.getString("wards"));
                    entity.setAddress(rs.getString("address"));
                    entity.setImage_1(rs.getString("image_1"));
                    entity.setImage_2(rs.getString("image_2"));
                    entity.setImage_3(rs.getString("image_3"));
                    entity.setArea(rs.getString("area"));
                    entity.setCost(rs.getString("cost"));
                    entity.setTime_post(rs.getDate("time_post"));
                    entity.setHide(rs.getInt("hide"));
                    entity.setCheck(rs.getInt("check"));
                    entity.setUsername_check(rs.getString("username_check"));
                    entity.setTime_check(rs.getDate("time_check"));
                    entity.setRank(rs.getInt("rank"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //use
    //find post by type_topic
    public ArrayList<Post> findPostsByTypeTopicDao(String type_topic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM post WHERE type_topic = '" + type_topic + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Post> list = new ArrayList<>();
                while (rs.next()) {
                    Post entity = new Post();
                    entity.setIdpost(rs.getInt("idpost"));
                    entity.setUsername(rs.getString("username"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setType_request(rs.getString("type_request"));
                    entity.setSubject(rs.getString("subject"));
                    entity.setContent(rs.getString("content"));
                    entity.setDistrict(rs.getString("district"));
                    entity.setWards(rs.getString("wards"));
                    entity.setAddress(rs.getString("address"));
                    entity.setImage_1(rs.getString("image_1"));
                    entity.setImage_2(rs.getString("image_2"));
                    entity.setImage_3(rs.getString("image_3"));
                    entity.setArea(rs.getString("area"));
                    entity.setCost(rs.getString("cost"));
                    entity.setTime_post(rs.getDate("time_post"));
                    entity.setHide(rs.getInt("hide"));
                    entity.setCheck(rs.getInt("check"));
                    entity.setUsername_check(rs.getString("username_check"));
                    entity.setTime_check(rs.getDate("time_check"));
                    entity.setRank(rs.getInt("rank"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find post by type_request
    public ArrayList<Post> findPostsByTypeRequestDao(String type_request) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM post WHERE type_request = '" + type_request + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Post> list = new ArrayList<>();
                while (rs.next()) {
                    Post entity = new Post();
                    entity.setIdpost(rs.getInt("idpost"));
                    entity.setUsername(rs.getString("username"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setType_request(rs.getString("type_request"));
                    entity.setSubject(rs.getString("subject"));
                    entity.setContent(rs.getString("content"));
                    entity.setDistrict(rs.getString("district"));
                    entity.setWards(rs.getString("wards"));
                    entity.setAddress(rs.getString("address"));
                    entity.setImage_1(rs.getString("image_1"));
                    entity.setImage_2(rs.getString("image_2"));
                    entity.setImage_3(rs.getString("image_3"));
                    entity.setArea(rs.getString("area"));
                    entity.setCost(rs.getString("cost"));
                    entity.setTime_post(rs.getDate("time_post"));
                    entity.setHide(rs.getInt("hide"));
                    entity.setCheck(rs.getInt("check"));
                    entity.setUsername_check(rs.getString("username_check"));
                    entity.setTime_check(rs.getDate("time_check"));
                    entity.setRank(rs.getInt("rank"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //use
    //find post by type_topic & type_request
    public ArrayList<Post> findPostsByTypeTopicRequestDao(String type_topic, String type_request) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM post WHERE type_topic = '" + type_topic + "' AND type_request = '" + type_request + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Post> list = new ArrayList<>();
                while (rs.next()) {
                    Post entity = new Post();
                    entity.setIdpost(rs.getInt("idpost"));
                    entity.setUsername(rs.getString("username"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setType_request(rs.getString("type_request"));
                    entity.setSubject(rs.getString("subject"));
                    entity.setContent(rs.getString("content"));
                    entity.setDistrict(rs.getString("district"));
                    entity.setWards(rs.getString("wards"));
                    entity.setAddress(rs.getString("address"));
                    entity.setImage_1(rs.getString("image_1"));
                    entity.setImage_2(rs.getString("image_2"));
                    entity.setImage_3(rs.getString("image_3"));
                    entity.setArea(rs.getString("area"));
                    entity.setCost(rs.getString("cost"));
                    entity.setTime_post(rs.getDate("time_post"));
                    entity.setHide(rs.getInt("hide"));
                    entity.setCheck(rs.getInt("check"));
                    entity.setUsername_check(rs.getString("username_check"));
                    entity.setTime_check(rs.getDate("time_check"));
                    entity.setRank(rs.getInt("rank"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find post by time_check
    //find post by time_post

    //find posts hide = 0, check = 1
    public ArrayList<Post> findPostsShowCheckDao() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM post WHERE hide = '0' AND check = '1'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Post> list = new ArrayList<>();
                while (rs.next()) {
                    Post entity = new Post();
                    entity.setIdpost(rs.getInt("idpost"));
                    entity.setUsername(rs.getString("username"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setType_request(rs.getString("type_request"));
                    entity.setSubject(rs.getString("subject"));
                    entity.setContent(rs.getString("content"));
                    entity.setDistrict(rs.getString("district"));
                    entity.setWards(rs.getString("wards"));
                    entity.setAddress(rs.getString("address"));
                    entity.setImage_1(rs.getString("image_1"));
                    entity.setImage_2(rs.getString("image_2"));
                    entity.setImage_3(rs.getString("image_3"));
                    entity.setArea(rs.getString("area"));
                    entity.setCost(rs.getString("cost"));
                    entity.setTime_post(rs.getDate("time_post"));
                    entity.setHide(rs.getInt("hide"));
                    entity.setCheck(rs.getInt("check"));
                    entity.setUsername_check(rs.getString("username_check"));
                    entity.setTime_check(rs.getDate("time_check"));
                    entity.setRank(rs.getInt("rank"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
     //find posts hide = 0, check = 0
    public ArrayList<Post> findPostsShowNoCheckDao() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM post WHERE hide = '0' AND check = '0'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Post> list = new ArrayList<>();
                while (rs.next()) {
                    Post entity = new Post();
                    entity.setIdpost(rs.getInt("idpost"));
                    entity.setUsername(rs.getString("username"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setType_request(rs.getString("type_request"));
                    entity.setSubject(rs.getString("subject"));
                    entity.setContent(rs.getString("content"));
                    entity.setDistrict(rs.getString("district"));
                    entity.setWards(rs.getString("wards"));
                    entity.setAddress(rs.getString("address"));
                    entity.setImage_1(rs.getString("image_1"));
                    entity.setImage_2(rs.getString("image_2"));
                    entity.setImage_3(rs.getString("image_3"));
                    entity.setArea(rs.getString("area"));
                    entity.setCost(rs.getString("cost"));
                    entity.setTime_post(rs.getDate("time_post"));
                    entity.setHide(rs.getInt("hide"));
                    entity.setCheck(rs.getInt("check"));
                    entity.setUsername_check(rs.getString("username_check"));
                    entity.setTime_check(rs.getDate("time_check"));
                    entity.setRank(rs.getInt("rank"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    //===========================================SAVE POST - FIND==================================================================================
    //find post saved by username
    public ArrayList<Post> findPostsSaveByUsernameDao(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT p.* FROM post p, save_post sp WHERE p.idpost = sp.post_idpost AND sp.member_usename = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Post> list = new ArrayList<>();
                while (rs.next()) {
                    Post entity = new Post();
                    entity.setIdpost(rs.getInt("idpost"));
                    entity.setUsername(rs.getString("username"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setType_request(rs.getString("type_request"));
                    entity.setSubject(rs.getString("subject"));
                    entity.setContent(rs.getString("content"));
                    entity.setDistrict(rs.getString("district"));
                    entity.setWards(rs.getString("wards"));
                    entity.setAddress(rs.getString("address"));
                    entity.setImage_1(rs.getString("image_1"));
                    entity.setImage_2(rs.getString("image_2"));
                    entity.setImage_3(rs.getString("image_3"));
                    entity.setArea(rs.getString("area"));
                    entity.setCost(rs.getString("cost"));
                    entity.setTime_post(rs.getDate("time_post"));
                    entity.setHide(rs.getInt("hide"));
                    entity.setCheck(rs.getInt("check"));
                    entity.setUsername_check(rs.getString("username_check"));
                    entity.setTime_check(rs.getDate("time_check"));
                    entity.setRank(rs.getInt("rank"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    
    //-------------------------------------COUNT------------------------------------------------------------------------
    //count posts by username
    //count posts by home
    //count posts by aparterment
    //count posts by motel
    //---------------------------------------------------PROCESS OTHER--------------------------------------------------------------
    //----------------------------------------------------FORMAT-----------------------------------------------------------
    public String toStringDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}

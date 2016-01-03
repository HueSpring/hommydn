//manager + roles
package dn.hommy.dao;

import dn.hommy.connect.ConnectionFactory;
import dn.hommy.entity.Manager;
import dn.hommy.entity.Roles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManagerDao {

    public ManagerDao() {
    }

    //create new manager full
    public boolean createManagerFullDao(String username, String password, String firstname, String lastname,
            String city, String avatar, String gender, String phone, String email, Date time_create_acc) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO manager (username, password, firstname, lastname, city, "
                    + "avatar, gender, phone, email, time_creat_acc) "
                    + "VALUES ('" + username + "','" + password + "','" + firstname + "','" + lastname + "','" + city + "','"
                    + avatar + "','" + gender + "','" + phone + "','" + email + "','" + toStringDateTime(time_create_acc) + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //create new manager short
    public boolean createManagerShortDao(String username, String password, String avatar, Date time_create_acc) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO manager (username, password, avatar, time_create_acc) " 
                    + "VALUES ('" + username + "','" + password + "','" + avatar + "','" + toStringDateTime(time_create_acc) + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //create new roles & job
    public boolean createRolesJobDao(String manager_username, String role, String type_topic, Date time_create_topic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO roles (manager_username, role, type_topic, time_create_topic) "
                    + "VALUES ('" + manager_username + "','" + role + "','" + type_topic + "','" + toStringDateTime(time_create_topic) + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //create new roles & no job
    public boolean createRolesNoJobDao(String manager_username, String role) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO roles (manager_username, role) "
                    + "VALUES ('" + manager_username + "','" + role + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update job (create job)
    public boolean updateJobDao(String manager_username, String type_topic, Date time_create_mission) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE roles SET type_topic = '" + type_topic
                    + "', time_create_topic = '" + toStringDateTime(time_create_mission) + "' WHERE manager_username = '" + manager_username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update roles
    public boolean updateRoleDao(String manager_username, String role) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE roles SET role = '" + role + "' WHERE manager_username = '" + manager_username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update roles & job
    public boolean updateRoleJobDao(String manager_username, String role, String type_topic, Date time_create_mission) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE roles SET role = '" + role + "', type_topic = '" + type_topic
                    + "', time_create_topic = '" + toStringDateTime(time_create_mission) + "' WHERE manager_username = '" + manager_username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update all infor personal manager
    public boolean updateManagerDao(String username, String firstname, String lastname,
            String city, String avatar, String gender, String phone, String email) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE manager SET firstname = '" + firstname + "', lastname = '" + lastname + "', city = '" + city
                    + "', avatar = '" + avatar + "', gender = '" + gender + "', phone = '" + phone
                    + "',email = '" + email + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update infor not avatar
    public boolean updateManagerNoAvatarDao(String username, String firstname, String lastname,
            String city, String gender, String phone, String email) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE manager SET firstname = '" + firstname + "', lastname = '" + lastname + "', city = '" + city
                    + "', gender = '" + gender + "', phone = '" + phone
                    + "',email = '" + email + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    //update avatar
    public boolean updateAvatarDao(String username, String avatar) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE manager SET avatar = '" + avatar + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update name
    public boolean updateNameDao(String username, String firstname, String lastname) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE manager SET firstname = '" + firstname + "', lastname = '" + lastname + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update phone
    public boolean updatePhoneDao(String username, String phone) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE manager SET phone = '" + phone + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update email
    public boolean updateEmailDao(String username, String email) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE manager SET email = '" + email + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update gender
    public boolean updateGenderDao(String username, String gender) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE manager SET gender = '" + gender + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }
    
    //update address
    public boolean updateAddressDao(String username, String city) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE manager SET city = '" + city + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //find all roles
    public ArrayList<Roles> findAllRolesDao() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM roles";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Roles> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Roles entity = new Roles();
                    entity.setManager_username(rs.getString("manager_username"));
                    entity.setRole(rs.getString("role"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setTime_create_topic(rs.getDate("time_create_topic"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find roles by manager_username
    public ArrayList<Roles> findRolesByUsername(String manager_username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM roles WHERE manager_username = '" + manager_username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Roles> list = new ArrayList<>();
                while (rs.next()) {
                    Roles entity = new Roles();
                    entity.setManager_username(rs.getString("manager_username"));
                    entity.setRole(rs.getString("role"));
                    entity.setType_topic(rs.getString("type_topic"));
                    entity.setTime_create_topic(rs.getDate("time_create_topic"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    

    //find manager by username
    public Manager findManagerByUsernameDao(String username) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM manager WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                Manager entity = new Manager();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    entity.setFirstname(rs.getString("firstname"));
                    entity.setLastname(rs.getString("lastname"));
                    entity.setGender(rs.getString("gender"));
                    entity.setAvatar(rs.getString("avatar"));
                    entity.setCity(rs.getString("city"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    entity.setTime_create_acc(rs.getDate("time_create_acc"));
                }
                return entity;
            }
        }
    }

    //find all mods
    public ArrayList<Manager> findAllModsDao() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT m.* FROM manager m, roles r WHERE m.username = r.manager_username "
                    + "AND r.role LIKE Moderator";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Manager> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Manager entity = new Manager();
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    entity.setFirstname(rs.getString("firstname"));
                    entity.setLastname(rs.getString("lastname"));
                    entity.setGender(rs.getString("gender"));
                    entity.setAvatar(rs.getString("avatar"));
                    entity.setCity(rs.getString("city"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    entity.setTime_create_acc(rs.getDate("time_create_acc"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
     //find all admins
    public ArrayList<Manager> findAllAdminsDao() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT m.* FROM manager m, roles r WHERE m.username = r.manager_username "
                    + "AND r.role LIKE Administrator";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Manager> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Manager entity = new Manager();
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    entity.setFirstname(rs.getString("firstname"));
                    entity.setLastname(rs.getString("lastname"));
                    entity.setGender(rs.getString("gender"));
                    entity.setAvatar(rs.getString("avatar"));
                    entity.setCity(rs.getString("city"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    entity.setTime_create_acc(rs.getDate("time_create_acc"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find manager same same username
    public ArrayList<Manager> findManagerSameUsernameDao(String username) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM manager WHERE username LIKE '%" + username + "%'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Manager> list = new ArrayList<>();
                while (rs.next()) {
                    Manager entity = new Manager();
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    entity.setFirstname(rs.getString("firstname"));
                    entity.setLastname(rs.getString("lastname"));
                    entity.setGender(rs.getString("gender"));
                    entity.setAvatar(rs.getString("avatar"));
                    entity.setCity(rs.getString("city"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    entity.setTime_create_acc(rs.getDate("time_create_acc"));
                }
                return list;
            }
        }
    }
    
    //find Manager by role
    public ArrayList<Manager> findManagersByRoleDao(String role) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT m.* FROM manager m, roles r WHERE m.username = r.manager_username "
                    + "AND r.role LIKE '" + role + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Manager> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Manager entity = new Manager();
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    entity.setFirstname(rs.getString("firstname"));
                    entity.setLastname(rs.getString("lastname"));
                    entity.setGender(rs.getString("gender"));
                    entity.setAvatar(rs.getString("avatar"));
                    entity.setCity(rs.getString("city"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    entity.setTime_create_acc(rs.getDate("time_create_acc"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    //find Manager by gender
    public ArrayList<Manager> findManagersByGenderDao(String gender) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT m.* FROM manager m WHERE m.gender LIKE '" + gender + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Manager> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Manager entity = new Manager();
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    entity.setFirstname(rs.getString("firstname"));
                    entity.setLastname(rs.getString("lastname"));
                    entity.setGender(rs.getString("gender"));
                    entity.setAvatar(rs.getString("avatar"));
                    entity.setCity(rs.getString("city"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    entity.setTime_create_acc(rs.getDate("time_create_acc"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    //find Manager by city
    public ArrayList<Manager> findManagersByCityDao(String city) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT m.* FROM manager m WHERE m.city LIKE '" + city + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Manager> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Manager entity = new Manager();
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    entity.setFirstname(rs.getString("firstname"));
                    entity.setLastname(rs.getString("lastname"));
                    entity.setGender(rs.getString("gender"));
                    entity.setAvatar(rs.getString("avatar"));
                    entity.setCity(rs.getString("city"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    entity.setTime_create_acc(rs.getDate("time_create_acc"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    //find mods by type_topic
    public ArrayList<Manager> findModsByTypeTopicDao(String type_topic) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT m.* FROM manager m, roles r WHERE m.username = r.manager_username "
                    + "AND r.role LIKE Moderator AND r.type_topic = '" + type_topic + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Manager> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Manager entity = new Manager();
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    entity.setFirstname(rs.getString("firstname"));
                    entity.setLastname(rs.getString("lastname"));
                    entity.setGender(rs.getString("gender"));
                    entity.setAvatar(rs.getString("avatar"));
                    entity.setCity(rs.getString("city"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setEmail(rs.getString("email"));
                    entity.setTime_create_acc(rs.getDate("time_create_acc"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find all manager by day
    
    
    //find manager by day and role
    //find all manager by month
    //find manager by month and role
    //find all manager by year
    //find manager by year and role
    
    
    //format
    public String toStringDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    
}

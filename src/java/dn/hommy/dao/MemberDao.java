//member + clock
package dn.hommy.dao;

import dn.hommy.connect.ConnectionFactory;
import dn.hommy.controller.ProcessDay;
import dn.hommy.entity.Clock;
import dn.hommy.entity.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {

    public MemberDao() {
    }

    //------------------------------------CREATE------------------------------------------------------------
    //create new
    public boolean createNewMemberDao(String username, String password, String firstname, String lastname,
            String city, String avatar, String gender, String phone, String email, Date time_create_acc) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO member (username, password, firstname, lastname, city, avatar, gender, phone, email, time_create_acc) "
                    + "VALUES ('" + username + "','" + password + "','" + firstname + "','" + lastname + "','" + city + "','"
                    + avatar + "','" + gender + "','" + phone + "','" + email + "','" + toStringDateTime(time_create_acc) + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //create clock
    public boolean createClockDao(String username, Date time_current, int step) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            ProcessDay pd = new ProcessDay();
            String time_end = pd.addDate(time_current, step);
            String sql = "INSERT INTO clock (member_username, time_current, step, time_end) "
                    + "VALUES ('" + username + toStringDateTime(time_current) + "','" + step + "','" + time_end + "')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //------------------------------------------DELETE-------------------------------------------------------------
    //delete member
    public boolean deleteMemberDao(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM member WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //delete clock
    public boolean deleteClockDao(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM clock WHERE member_username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //-------------------------------------------FIND---------------------------------------------------------------
    //find all members
    public ArrayList<Member> findAllMembersDao() throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM member";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Member> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Member entity = new Member();
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
                    entity.setRole_login(rs.getInt("role_login"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find member by username
    public Member findMemberByUsernameDao(String username) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM member WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                Member entity = new Member();
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
                    entity.setRole_login(rs.getInt("role_login"));
                }
                return entity;
            }
        }
    }

    //find clocks by member_username
    public ArrayList<Clock> findClocksByUsernameDao(String member_username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM clock WHERE member_username = '" + member_username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Clock> list = new ArrayList<>();
                while (rs.next()) {
                    Clock entity = new Clock();
                    entity.setMember_username(rs.getString("member_username"));
                    entity.setTime_current(rs.getDate("time_current"));
                    entity.setStep(rs.getInt("step"));
                    entity.setTime_end(rs.getDate("time_end"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find all clocks
    public ArrayList<Clock> findAllClocksDao() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM clock";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Clock> list = new ArrayList<>();
                while (rs.next()) {
                    Clock entity = new Clock();
                    entity.setMember_username(rs.getString("member_username"));
                    entity.setTime_current(rs.getDate("time_current"));
                    entity.setStep(rs.getInt("step"));
                    entity.setTime_end(rs.getDate("time_end"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find members by city
    public ArrayList<Member> findMembersByCityDao(String city) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM member WHERE city = '" + city + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Member> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Member entity = new Member();
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
                    entity.setRole_login(rs.getInt("role_login"));
                    list.add(entity);
                }
                return list;
            }
        }
    }

    //find members is being clocked
    public ArrayList<Member> findMemberIsClockingDao() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            Date date = new Date();
            String sql = "SELECT m.* FROM member m, Roles r WHERE r.time_end > '" + toStringDateTime(date) + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ArrayList<Member> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Member entity = new Member();
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
                    entity.setRole_login(rs.getInt("role_login"));
                    list.add(entity);
                }
                return list;
            }
        }
    }
    
    //----------------------------------------UPDATE------------------------------------------------------
    //update all infor personal member
    public boolean updateMemberDao(String username, String firstname, String lastname,
            String city, String gender, String phone, String email, Date time_create_acc) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE member SET firstname = '" + firstname + "', lastname = '" + lastname + "', city = '" + city
                    + "', gender = '" + gender + "', phone = '" + phone
                    + "',email = '" + email + "',time_create_acc'" + toStringDateTime(time_create_acc) + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update avatar
    public boolean updateAvatarDao(String username, String avatar) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE member SET avatar = '" + avatar + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update name
    public boolean updateNameDao(String username, String firstname, String lastname) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE member SET firstname = '" + firstname + "', lastname = '" + lastname + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update phone
    public boolean updatePhoneDao(String username, String phone) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE member SET phone = '" + phone + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update email
    public boolean updateEmailDao(String username, String email) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE member SET email = '" + email + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update gender
    public boolean updateGenderDao(String username, String gender) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE member SET gender = '" + gender + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update address
    public boolean updateAddressDao(String username, String city) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE member SET city = '" + city + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update password
    public boolean updatePasswordDao(String username, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE member SET password = '" + password + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //update role_login
    public boolean updateRoleLoginDao(String username, int role_login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE member SET role_login = '" + role_login + "' WHERE username = '" + username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                return ps.executeUpdate() > 0;
            }
        }
    }

    //-----------------------------------------COUNT------------------------------------------------------------------
    //count all quantity members
    public int countQuantityMember() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(username) FROM member";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                int quantity = 0;
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    quantity = rs.getInt("COUNT(username");
                }
                return quantity;
            }
        }
    }

    //count quantity member by month & year
    public int countQuantityMemberByMonth(int month, int year) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String begin = year + "-" + month + "-01";
            String end = year + "-" + month + "-31";
            String sql = "SELECT COUNT(username) FROM member WHERE time_create_acc >= '" + begin
                    + "' AND time_create_acc <= '" + end + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                int quantity = 0;
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    quantity = rs.getInt("COUNT(username");
                }
                return quantity;
            }
        }
    }
    
     //count quantity member by day & month & year
    public int countQuantityMemberByDate(int day, int month, int year) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String begin = year + "-" + month + "-" + day;
            String end = year + "-" + month + "-" + day;
            String sql = "SELECT COUNT(username) FROM member WHERE time_create_acc >= '" + begin
                    + "' AND time_create_acc <= '" + end + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                int quantity = 0;
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    quantity = rs.getInt("COUNT(username");
                }
                return quantity;
            }
        }
    }

    //count quantity times was clocked
    public int countQuantityTimesClocked(String member_username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT COUNT(member_username) FROM roles WHERE member_username = '" + member_username + "'";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                int quantity = 0;
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    quantity = rs.getInt("COUNT(member_username");
                }
                return quantity;
            }
        }
    }
    
    //---------------------------------FORMAT--------------------------------------------------------------------
    public String toStringDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}

//member + clock
package dn.hommy.controller;

import dn.hommy.dao.ManagerDao;
import dn.hommy.dao.MemberDao;
import dn.hommy.entity.Clock;
import dn.hommy.entity.Member;
import dn.hommy.entity.SavePost;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class MemberBean {

    private Part file;
    private static final String addressPath = "D:\\ACENTER\\hommy\\web\\resources\\default\\img\\img_members\\";
    private Member member = new Member();
    private SavePost savePost = new SavePost();
    private String message;
    private String passwordOld;
    private String passwordNew1;
    private String passwordNew2;
    private String messageAjax;

    public MemberBean() {
    }

    //-------------------------------------------------FIND------------------------------------------------------------
    //find Member by usename
    public Member getMemberByUsername(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        MemberDao dao = new MemberDao();
        return dao.findMemberByUsernameDao(username);
    }

    //find clock by username
    public ArrayList<Clock> getClockByUsername(String member_username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        MemberDao dao = new MemberDao();
        return dao.findClocksByUsernameDao(member_username);
    }

    //find member is clocking
    public ArrayList<Member> getMembersClocking() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        MemberDao dao = new MemberDao();
        return dao.findMemberIsClockingDao();
    }

    //-----------------------------------------------CHECK-------------------------------------------------------------------
    //check username - true: exist
    public boolean checkUsername() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Member item = getMemberByUsername(member.getUsername());
        if (item.getUsername() != null) {
            return true;
        }
        return false;
    }

    //use for member
    //check time clock - true: member is being clocked
    public boolean checkClock() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Clock> list = getClockByUsername(member.getUsername());
        Date date = new Date();
        String timeCurrent = toStringDateTime(date);
        for (Clock item : list) {
            String timeClock = toStringDateTime(item.getTime_end());
            if (timeCurrent.compareTo(timeClock) < 0) {
                return true;
            }
        }
        return false;
    }

    //check password - true: pass is right
    public boolean checkPassword() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Member m = getMemberByUsername(member.getUsername());
        if (m.getUsername() != null && m.getPassword().equals(member.getPassword())) {
            return true;
        }
        return false;
    }

    //check lenght password >= 6
    public boolean checkLenghtPassword() {
        if (member.getPassword().length() >= 6) {
            return true;
        }
        return false;
    }

    //check lenght username < 15
    public boolean checkLenghtUsername() {
        if (member.getUsername().length() < 15) {
            return true;
        }
        return false;
    }

    //check lenght firstname & lastname < 20
    public boolean checkLenghtName() {
        if (member.getFirstname().length() < 20 && member.getLastname().length() < 20) {
            return true;
        }
        return false;
    }

    //check lenght email < 20
    public boolean checkLenghtEmail() {
        if (member.getEmail().length() < 20) {
            return true;
        }
        return false;
    }

    //check create new password not null
    public boolean checkNotNullPass() {
        if (!passwordOld.equals("") && !passwordNew1.equals("") && !passwordNew2.equals("")) {
            return true;
        }
        return false;
    }

    //check password old
    public boolean checkPasswordOld(){
        if(member.getPassword().equals(passwordOld)){
            return true;
        }
        return false;
    }
    
    //check password new
    public boolean checkPasswordNew(){
        if(passwordNew1.equals(passwordNew2)){
            return true;
        }
        return false;
    }
    
    //check personal infor not null 
    public boolean checkNew() {
        if (!member.getUsername().equals("") && !member.getFirstname().equals("") && !member.getLastname().equals("")
                && !member.getPhone().equals("") && !member.getPassword().equals("") && !member.getCity().equals("")
                && !member.getGender().equals("")) {
            return true;
        }
        return false;
    }

    //check role_login
    public boolean checkRoleLogin() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Member m = getMemberByUsername(member.getUsername());
        if (m.getRole_login() == 1) {
            return true;
        }
        return false;
    }

    //-----------------------------------------------CHECK SPECIAL-----------------------------------------------------
    //check username ajax - use for create new acc
    public void checkUsernameAjax(AjaxBehaviorEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Member item = getMemberByUsername(member.getUsername());
        if (item.getUsername() != null) {
            messageAjax = "Username has been exist.";
        } else {
            messageAjax = "It's ok.";
        }
    }
    
    //check username ajax - user for login
    public void checkUsernameLoginAjax(AjaxBehaviorEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Member item = getMemberByUsername(member.getUsername());
        if (item.getUsername() != null) {
            messageAjax = "";
        } else {
            messageAjax = "Username isn't exist.";
        }
    }

    //check password ajax
    public void checkPasswordAjax(AjaxBehaviorEvent event) {
        if (member.getPassword().length() >= 6) {
            messageAjax = "Password is valid.";
        } else {
            messageAjax = "Password must has least 6 character.";
        }
    }

    //check session - true: exist
     public boolean checkSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.isNew()) {
            return false;
        } else {
            return true;
        }
    }
    
    //-----------------------------------------------CREATE-------------------------------------------------------------
    //create new member
    public String createNewMember() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        if (checkNew()) {
            if (!checkUsername()) {
                if (checkLenghtPassword()) {
                    MemberDao dao = new MemberDao();
                    ImageBean image = new ImageBean();
                    if (member.getGender().equalsIgnoreCase("Male")) {
                        image.copyFileMale(addressPath + member.getUsername() + ".png");
                    } else if (member.getGender().equalsIgnoreCase("Female")) {
                        image.copyFileFemale(addressPath + member.getUsername() + ".png");
                    } else {
                        image.copyFileNoGender(addressPath + member.getUsername() + ".png");
                    }
                    member.setAvatar(member.getUsername() + ".png");
                    Date date = new Date();
                    member.setTime_create_acc(date);
                    dao.createNewMemberDao(member.getUsername(), member.getPassword(), member.getFirstname(),
                            member.getLastname(), member.getCity(), member.getAvatar(), member.getGender(),
                            member.getPhone(), member.getEmail(), member.getTime_create_acc());
                    return "loginmember";
                } else {
                    message = "Password must has least 6 character";
                    return "../index";
                }
            } else {
                message = "Username has been exist.";
                return "../index";
            }
        } else {
            message = "Value is not null.";
            return "../index";
        }
    }

    //use to clock member
    //create clock member - default clock in 3 days
    public void createClockDefault(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Date date = new Date();
        MemberDao dao = new MemberDao();
        dao.createClockDao(username, date, 3);
    }

    //create clock member - custom clock days
    public void createClockCustom(String username, int step) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Date date = new Date();
        MemberDao dao = new MemberDao();
        dao.createClockDao(username, date, step);
    }

    //--------------------------------------------UPDATE------------------------------------------------------------
    //----------------------------------------------PROCESS OTHER------------------------------------------------------------
    //login
    public String login() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        message = "";
        if (checkUsername() && checkPassword()) {
            if (checkRoleLogin()) {
                if (!checkClock()) {
                    Member entity = getMemberByUsername(member.getUsername());
                    member.setUsername(entity.getUsername());
                    member.setFirstname(entity.getFirstname());
                    member.setLastname(entity.getLastname());
                    member.setAvatar(entity.getAvatar());
                    member.setCity(entity.getCity());
                    member.setPhone(entity.getPhone());
                    member.setEmail(entity.getEmail());
                    member.setTime_create_acc(entity.getTime_create_acc());
                    return "homeMember";
                } else {
                    return "clock";
                }
            } else {
                return "clock_forever";
            }
        } else {
            message = "Username or password is wrong.";
            return "loginmember";
        }
    }

    //change password
    public void changePassword() {
        if (checkNotNullPass()&& checkPasswordOld() && checkPasswordNew()) {
            if (checkLenghtPassword()) {
                member.setPassword(passwordNew1);
                passwordOld = null;
                passwordNew1 = null;
                passwordNew2 = null;
                message = "Change password .. SUCCESSFUL.";
            } else {
                message = "Password must has least 6 character.";
            }
        } else {
            message = "Change password .. FAIL.";
        }
    }

    //change avatar
    public void changeAvatar() throws IOException {
        ImageBean image = new ImageBean();
        image.changeAvatar(file, addressPath, member.getUsername());
    }

    //change name
    public void changeName() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updateNameDao(member.getUsername(), member.getFirstname(), member.getLastname());
    }

    //change address
    public void changeAddress() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updateAddressDao(member.getUsername(), member.getCity());
    }

    //change phone
    public void changePhone() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updatePhoneDao(member.getUsername(), member.getPhone());
    }

    //change email
    public void changeEmail() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updateEmailDao(member.getUsername(), member.getEmail());
    }
    
    //count all member
    //count member new in day
    //count member new in month
    //count member new in year
    //show email
    public String showEmail() {
        if (checkLenghtEmail()) {
            return member.getEmail();
        } else {
            String[] se = splitEmail(member.getEmail());
            return se[0].substring(0, 6) + ".." + se[1];
        }
    }

    //split email
    public String[] splitEmail(String email) {
        String[] s1 = email.split("@");
        return s1;
    }

    //----------------------------------------FORMAT----------------------------------------------------------------------
    public String toStringDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /*------------------------------SET & GET-------------------------------------------------------------------------*/
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public String getUsername() {
        return member.getUsername();
    }

    public void setUsername(String username) {
        member.setUsername(username);
    }

    public String getPassword() {
        return member.getPassword();
    }

    public void setPassword(String password) {
        member.setPassword(password);
    }

    public String getFirstname() {
        return member.getFirstname();
    }

    public void setFirstname(String firstname) {
        member.setFirstname(firstname);
    }

    public String getLastname() {
        return member.getLastname();
    }

    public void setLastname(String lastname) {
        member.setLastname(lastname);
    }

    public String getCity() {
        return member.getCity();
    }

    public void setCity(String city) {
        member.setCity(city);
    }

    public String getAvatar() {
        return member.getAvatar();
    }

    public void setAvatar(String avatar) {
        member.setAvatar(avatar);
    }

    public String getGender() {
        return member.getGender();
    }

    public void setGender(String gender) {
        member.setGender(gender);
    }

    public String getPhone() {
        return member.getPhone();
    }

    public void setPhone(String phone) {
        member.setPhone(phone);
    }

    public String getEmail() {
        return member.getEmail();
    }

    public void setEmail(String email) {
        member.setEmail(email);
    }

    public Date getTime_create_acc() {
        return member.getTime_create_acc();
    }

    public void setTime_create_acc(Date time_create_acc) {
        member.setTime_create_acc(time_create_acc);
    }

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

    public String getMessageAjax() {
        return messageAjax;
    }

    public void setMessageAjax(String messageAjax) {
        this.messageAjax = messageAjax;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew1() {
        return passwordNew1;
    }

    public void setPasswordNew1(String passwordNew1) {
        this.passwordNew1 = passwordNew1;
    }

    public String getPasswordNew2() {
        return passwordNew2;
    }

    public void setPasswordNew2(String passwordNew2) {
        this.passwordNew2 = passwordNew2;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

}

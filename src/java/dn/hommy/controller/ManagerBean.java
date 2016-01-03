package dn.hommy.controller;

import dn.hommy.dao.ManagerDao;
import dn.hommy.entity.Manager;
import dn.hommy.entity.Roles;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class ManagerBean {

    private static final String pathManager = "D:\\ACENTER\\hommy\\web\\resources\\default\\img\\img_managers\\";
    private Part file;
    private Manager manager = new Manager();
    private Roles roles = new Roles();
    private String message;
    private String passwordOld;
    private String passwordNew1;
    private String passwordNew2;

    public ManagerBean() {
    }

    //--------------------------------------------------------FIND-----------------------------------------------------------------------------------------------
    //find all roles
    public ArrayList<Roles> getAllRoles() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        return dao.findAllRolesDao();
    }

    //find roles by manager_username
    public ArrayList<Roles> getRolesByUsername(String manager_username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        return dao.findRolesByUsername(manager_username);
    }

    //find manager by username
    public Manager getManagerByUsername(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        return dao.findManagerByUsernameDao(username);
    }

    //find admin
    public ArrayList<Manager> getAdmins() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        return dao.findAllAdminsDao();
    }

    //find mod
    public ArrayList<Manager> getMods() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        return dao.findAllModsDao();
    }
    //-----------------------------------------------------------------CREATE--------------------------------------------------------------------------------------

    //---------------------------------------------------------------CHECK--------------------------------------------------------------------------------------
    //check username - true: exist
    public boolean checkUsername() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Manager item = getManagerByUsername(manager.getUsername());
        if (item.getUsername() != null) {
            return true;
        }
        return false;
    }

    //check roles -> -1: current page, 0: administrator, 1: moderator, 2: choice
    public int checkRole() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        ArrayList<Roles> list = getRolesByUsername(manager.getUsername());
        if (list.size() > 1) {
            return 2;
        }
        if (list.size() == 1) {
            for (Roles item : list) {
                if (item.getRole().equalsIgnoreCase("Administrator")) {
                    return 0;
                }
                if (item.getRole().equalsIgnoreCase("Moderator")) {
                    return 1;
                }
            }
        }
        return -1;
    }

    //check lenght password >= 6
    public boolean checkLenghtPassword() {
        if (manager.getPassword().length() >= 6) {
            return true;
        }
        return false;
    }

    //check password
    public boolean checkPassword() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Manager item = getManagerByUsername(manager.getUsername());
        if (item.getUsername() != null && item.getPassword().equals(manager.getPassword())) {
            return true;
        }
        return false;
    }

    //check value passwords not null
    public boolean checkNotNull() {
        if (!passwordOld.equals("") && !passwordNew1.equals("") && !passwordNew2.equals("")) {
            return true;
        }
        return false;
    }

    //check passwordOld
    public boolean checkPasswordOld() {
        if (manager.getPassword().equals(passwordOld)) {
            return true;
        }
        return false;
    }

    //check passworNew
    public boolean checkPasswordNew() {
        if (passwordNew1.equals(passwordNew2)) {
            return true;
        }
        return false;
    }

    //mod or admin. First time login, personal infor must enter full
    //check personal information is not full
    public boolean checkFullInfor() {
        if (manager.getFirstname().equals("") || manager.getLastname().equals("") || manager.getCity().equals("")
                || manager.getEmail().equals("") || manager.getPhone().equals("") || manager.getGender().equals("")) {
            return false;
        }
        return true;
    }

    //-----------------------------------------------------------UPDATE---------------------------------------------------------------------------------------------
    //update full personal information
    public void updateFullManager() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updateManagerNoAvatarDao(manager.getUsername(), manager.getFirstname(), manager.getLastname(), manager.getCity(),
                manager.getGender(), manager.getPhone(), manager.getEmail());
    }

    //-----------------------------------------------------------DELETE---------------------------------------------------------------------------------------------
    //-----------------------------------------------------------SUPPORT---------------------------------------------------------------------------------------------
    //
    //------------------------------------------------------------PROCESS OTHER--------------------------------------------------------------------------------------------
    //login
    public String login() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (checkUsername() && checkPassword()) {
            int i = checkRole();
            if (i != -1) {
                Manager entity = getManagerByUsername(manager.getUsername());
                manager.setUsername(entity.getUsername());
                manager.setFirstname(entity.getFirstname());
                manager.setLastname(entity.getLastname());
                manager.setAvatar(entity.getAvatar());
                manager.setCity(entity.getCity());
                manager.setPhone(entity.getPhone());
                manager.setEmail(entity.getEmail());
                manager.setTime_create_acc(entity.getTime_create_acc());
            }
            if (i == 0) {
                return "admin";
            }
            if (i == 1) {
                return "/moderator/mod";
            }
            if (i == 2) {
                return "choiceRole";
            }
        }
        return "login";
    }

    //change password
    public void changePassword() {
        if (checkNotNull() && checkPasswordOld() && checkPasswordNew()) {
            if (checkLenghtPassword()) {
                manager.setPassword(passwordNew1);
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
        image.changeAvatar(file, pathManager, manager.getUsername());
    }

    //change name
    public void changeName() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updateNameDao(manager.getUsername(), manager.getFirstname(), manager.getLastname());
    }

    //change address
    public void changeAddress() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updateAddressDao(manager.getUsername(), manager.getCity());
    }

    //change phone
    public void changePhone() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updatePhoneDao(manager.getUsername(), manager.getPhone());
    }

    //change email
    public void changeEmail() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updateEmailDao(manager.getUsername(), manager.getEmail());
    }
    /*------------------------------------------------SET & GET-----------------------------------------------------------------------------*/

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return manager.getUsername();
    }

    public void setUsername(String username) {
        manager.setUsername(username);
    }

    public String getPassword() {
        return manager.getPassword();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPassword(String password) {
        manager.setPassword(password);
    }

    public String getFirstname() {
        return manager.getFirstname();
    }

    public void setFirstname(String firstname) {
        manager.setFirstname(firstname);
    }

    public String getLastname() {
        return manager.getLastname();
    }

    public void setLastname(String lastname) {
        manager.setLastname(lastname);
    }

    public String getCity() {
        return manager.getCity();
    }

    public void setCity(String city) {
        manager.setCity(city);
    }

    public String getAvatar() {
        return manager.getAvatar();
    }

    public void setAvatar(String avatar) {
        manager.setAvatar(avatar);
    }

    public String getGender() {
        return manager.getGender();
    }

    public void setGender(String gender) {
        manager.setGender(gender);
    }

    public String getPhone() {
        return manager.getPhone();
    }

    public void setPhone(String phone) {
        manager.setPhone(phone);
    }

    public String getEmail() {
        return manager.getEmail();
    }

    public void setEmail(String email) {
        manager.setEmail(email);
    }

    public Date getTime_create_acc() {
        return manager.getTime_create_acc();
    }

    public void setTime_create_acc(Date time_create_acc) {
        manager.setTime_create_acc(time_create_acc);
    }

    public String getRole() {
        return roles.getRole();
    }

    public void setRole(String role) {
        roles.setRole(role);
    }

    public String getManager_username() {
        return roles.getManager_username();
    }

    public void setManager_username(String manager_username) {
        roles.setManager_username(manager_username);
    }

    public String getType_topic() {
        return roles.getType_topic();
    }

    public void setType_topic(String type_topic) {
        roles.setType_topic(type_topic);
    }

    public Date getTime_create_topic() {
        return roles.getTime_create_topic();
    }

    public void setTime_create_mission(Date time_create_topic) {
        roles.setTime_create_topic(time_create_topic);
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

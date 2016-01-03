package dn.hommy.controller;

import dn.hommy.dao.ManagerDao;
import dn.hommy.entity.Manager;
import dn.hommy.entity.Roles;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ManagerBeanSub {

    private static final String addressPath = "D:\\ACENTER\\hommy\\web\\resources\\default\\img\\img_managers\\";
    private Manager manager = new Manager();
    private Roles roles = new Roles();
    private String message;

    public ManagerBeanSub() {
    }

    //------------------------------------------------------CREATE----------------------------------------------------------
    //create new admin - enter: username, password
    public void createAdmin() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        if (checkNew()) {
            if (!checkUsername()) {
                ImageBean image = new ImageBean();
                image.copyFileManager(addressPath + manager.getUsername() + ".png");
                manager.setAvatar(manager.getUsername() + ".png");
                Date date = new Date();
                manager.setTime_create_acc(date);
                ManagerDao dao = new ManagerDao();
                dao.createManagerShortDao(manager.getUsername(), manager.getPassword(), manager.getAvatar(), manager.getTime_create_acc());
                dao.createRolesNoJobDao(manager.getUsername(), "Administrator");
                message = "Sucessfully!";
            } else {
                message = "Username has been exist.";
            }
        } else {
            message = "Value is not empty.";
        }
    }

    //create new mod has job - enter: username, password & type_topic
    public void createMod() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        if (checkNew() && !roles.getType_topic().equals("")) {
            if (!checkUsername()) {
                ImageBean image = new ImageBean();
                image.copyFileManager(addressPath + manager.getUsername() + ".png");
                manager.setAvatar(manager.getUsername() + ".png");
                Date date = new Date();
                manager.setTime_create_acc(date);
                ManagerDao dao = new ManagerDao();
                dao.createManagerShortDao(manager.getUsername(), manager.getPassword(), manager.getAvatar(), manager.getTime_create_acc());
                roles.setManager_username(manager.getUsername());
                dao.createRolesJobDao(manager.getUsername(), "Moderator", roles.getType_topic(), manager.getTime_create_acc());
                message = "Sucessfully!";
            } else {
                message = "Username has been exist.";
            }
        } else {
            message = "Value is not empty.";
        }
    }

    //create new mod no job - enter: username, password
    public void createModNoJob() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        if (checkNew()) {
            if (!checkUsername()) {
                ImageBean image = new ImageBean();
                image.copyFileManager(addressPath + manager.getUsername() + ".png");
                manager.setAvatar(manager.getUsername() + ".png");
                Date date = new Date();
                manager.setTime_create_acc(date);
                ManagerDao dao = new ManagerDao();
                dao.createManagerShortDao(manager.getUsername(), manager.getPassword(), manager.getAvatar(), manager.getTime_create_acc());
                dao.createRolesNoJobDao(manager.getUsername(), "Moderator");
                message = "Sucessfully!";
            } else {
                message = "Username has been exist.";
            }
        } else {
            message = "Value is not empty.";
        }
    }

    //--------------------------------------------------------------FIND OTHER ENTITY BY ... --------------------------------------------------------------------------------
    //find manager by username
    public Manager getManagerByUsername(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        return dao.findManagerByUsernameDao(username);
    }

    //find roles by manager_username
    public ArrayList<Roles> getRolesByUsername(String manager_username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        return dao.findRolesByUsername(manager_username);
    }

    //use to search accounts
    //find managers by sam same username
    public ArrayList<Manager> getManagerSearchUsername() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        return dao.findManagerSameUsernameDao(manager.getUsername());
    }

    //--------------------------------------------------------------CHECK--------------------------------------------------------------------------------
    //check username - HAS username - true: exist
    public boolean checkUsername() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Manager item = getManagerByUsername(manager.getUsername());
        if (item.getUsername() != null) {
            return true;
        }
        return false;
    }

    //check username & password not null
    public boolean checkNew() {
        if (!manager.getUsername().equals("") && !manager.getPassword().equals("")) {
            return true;
        }
        return false;
    }

    //-----------------------------------------------------------------UPDATE-----------------------------------------------------------------------------------
    //update role
    public void updateRole(String username, String role) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ManagerDao dao = new ManagerDao();
        dao.updateRoleDao(username, role);
    }

    //update job
    public void updateJob(String username, String type_topic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Date date = new Date();
        ManagerDao dao = new ManagerDao();
        dao.updateJobDao(username, type_topic, date);
    }

    //update role and job
    public void updateRoleJob(String username, String role, String type_topic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Date date = new Date();
        ManagerDao dao = new ManagerDao();
        dao.updateRoleJobDao(username, role, type_topic, date);
    }

    //-----------------------------------------------------------------SET & GET-----------------------------------------------------------------------------------
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public void setTime_create_topic(Date time_create_topic) {
        roles.setTime_create_topic(time_create_topic);
    }

}

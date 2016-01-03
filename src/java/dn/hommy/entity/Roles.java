
package dn.hommy.entity;

import java.util.Date;


public class Roles {

    private String manager_username;
    private String role;
    private String type_topic;
    private Date time_create_topic;

    public Roles() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getManager_username() {
        return manager_username;
    }

    public void setManager_username(String manager_username) {
        this.manager_username = manager_username;
    }

    public String getType_topic() {
        return type_topic;
    }

    public void setType_topic(String type_topic) {
        this.type_topic = type_topic;
    }

    public Date getTime_create_topic() {
        return time_create_topic;
    }

    public void setTime_create_topic(Date time_create_topic) {
        this.time_create_topic = time_create_topic;
    }

}

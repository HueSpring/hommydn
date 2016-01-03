
package dn.hommy.entity;

import java.util.Date;


public class Message {
    
    private int idmessage;
    private String subject;
    private String content;
    private String username_sender;
    private String username_receiver;
    private Date time_send;
    private Date time_receive;
    private int check;

    public int getIdmessage() {
        return idmessage;
    }

    public void setIdmessage(int idmessage) {
        this.idmessage = idmessage;
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

    public String getUsername_sender() {
        return username_sender;
    }

    public void setUsername_sender(String username_sender) {
        this.username_sender = username_sender;
    }

    public String getUsername_receiver() {
        return username_receiver;
    }

    public void setUsername_receiver(String username_receiver) {
        this.username_receiver = username_receiver;
    }

    public Date getTime_send() {
        return time_send;
    }

    public void setTime_send(Date time_send) {
        this.time_send = time_send;
    }

    public Date getTime_receive() {
        return time_receive;
    }

    public void setTime_receive(Date time_receive) {
        this.time_receive = time_receive;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
    
    
}

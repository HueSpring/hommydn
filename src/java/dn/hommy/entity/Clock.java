

package dn.hommy.entity;

import java.util.Date;

public class Clock {
    
    private String member_username;
    private Date time_current;
    private int step;

    public Clock() {
    }

    public String getMember_username() {
        return member_username;
    }

    public void setMember_username(String member_username) {
        this.member_username = member_username;
    }

    public Date getTime_current() {
        return time_current;
    }

    public void setTime_current(Date time_current) {
        this.time_current = time_current;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
    
}


package dn.hommy.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class OtherBean {

    private boolean showP = false;
    private boolean showA = false;
    private boolean showF = false;
    private boolean showE = false;

    public OtherBean() {
    }
    
    //split email
    public String[] splitEmail(String email){
        String[] s1 = email.split("@");
        return s1;
    }
    
    
    
    //-------------------------------------SET & GET------------------------------------------------------------------
    public boolean isShowP() {
        return showP;
    }

    public void setShowP(boolean showP) {
        this.showP = showP;
    }

    public boolean isShowA() {
        return showA;
    }

    public void setShowA(boolean showA) {
        this.showA = showA;
    }

    public boolean isShowF() {
        return showF;
    }

    public void setShowF(boolean showF) {
        this.showF = showF;
    }

    public boolean isShowE() {
        return showE;
    }

    public void setShowE(boolean showE) {
        this.showE = showE;
    }
    
    public void showPMethod(){
        showP = true;
    }
    public void showAMethod(){
        showA = true;
    }
    public void showFMethod(){
        showF = true;
    }
    public void showEMethod(){
        showE = true;
    }
    
}

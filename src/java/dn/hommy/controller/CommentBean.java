
//comment + spam

package dn.hommy.controller;

import dn.hommy.entity.Comment;
import dn.hommy.entity.Spam;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class CommentBean {

    private Comment comment = new Comment();
    private Spam spam = new Spam();
    
    public CommentBean() {
    }
    
}

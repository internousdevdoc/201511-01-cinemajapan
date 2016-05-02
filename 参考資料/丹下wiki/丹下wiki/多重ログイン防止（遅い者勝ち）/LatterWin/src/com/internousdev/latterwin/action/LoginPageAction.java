package com.internousdev.latterwin.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LoginPageAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;

    public String execute() {
        // ログイン済みならリダイレクト
        if(session.containsKey("user_id")) {
            return "redirect";
        }

        return SUCCESS;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}

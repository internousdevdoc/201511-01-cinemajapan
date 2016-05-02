package com.internousdev.latterwin.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.latterwin.dao.UserDAO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ApplicationAware, SessionAware {
    private String email;
    private String password;
    private Map<String, Object> application;
    private Map<String, Object> session;

    public String execute() {
        // ログイン済みならリダイレクト
        if(session.containsKey("user_id")) {
            return "redirect";
        }

        UserDAO dao = new UserDAO();
        int userId = dao.selectUser(email, password);

        if(userId == 0){
            addActionError("ログインに失敗しました");
            return INPUT;
        }

        // sessionに格納 { "user_id" : (int)userId }
        session.put("user_id", userId);
        // applicationに格納 { "user_id1" : (String)sessionId }
        application.put("user_id" + Integer.toString(userId), ServletActionContext.getRequest().getRequestedSessionId());

        return SUCCESS;
    }

    public void validate() {
        if(email.length() > 100) {
            addFieldError("email", "100文字以内で入力してください");
        }

        if(!email.matches("^[0-9a-zA-Z]+[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")) {
            addFieldError("email", "メールアドレスの形式が違います");
        }

        if(password.length() < 8 || 32 < password.length()) {
            addFieldError("password", "8文字以上32文字以下で入力してください");
        }

        if(!password.matches("^[0-9a-zA-Z]+$")) {
            addFieldError("password", "半角英数字で入力してください");
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}

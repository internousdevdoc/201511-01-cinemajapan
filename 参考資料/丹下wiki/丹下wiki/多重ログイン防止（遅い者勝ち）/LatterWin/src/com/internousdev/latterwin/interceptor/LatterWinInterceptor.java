package com.internousdev.latterwin.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LatterWinInterceptor extends AbstractInterceptor {



    public String intercept(ActionInvocation invocation) throws Exception {
        // SessionAware、ApplicationAwareでとれなかったため
        Map<String, Object> application = ServletActionContext.getContext().getApplication();
        Map<String, Object> session = ServletActionContext.getContext().getSession();

        // そもそもログインしてない
        if(!session.containsKey("user_id")) {
            return "error";
        }

        // セッションIDの取得
        String sessionId = ServletActionContext.getRequest().getRequestedSessionId();
        // applicationに格納するMapキー
        String appKey = "user_id" + Integer.toString((int)session.get("user_id"));	// "user_id1","user_id100"...

        // 他からのログインでapplicationのvalueが上書きされていたら
        if(!sessionId.equals((String)application.get(appKey))) {
            session.clear();
            return "error";
        }

        return invocation.invoke();
    }

}

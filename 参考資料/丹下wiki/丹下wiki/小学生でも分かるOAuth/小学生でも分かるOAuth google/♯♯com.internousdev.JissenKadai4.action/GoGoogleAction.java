package com.internousdev.JissenKadai4.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.JissenKadai4.google.GoGoogleOAuth;
import com.opensymphony.xwork2.ActionSupport;


/**
 * GoGoogleAction Googleでログインする為のクラス
 * @author Nagata Shigeru
 * @since 2015/09/17
 * @version 1.0
 */
public class GoGoogleAction extends ActionSupport implements ServletResponseAware,ServletRequestAware,SessionAware {

	/**
	 * 生成されたシリアルナンバー
	 */
	private static final long serialVersionUID = 1524555947613444860L;

	/**
	 * セッション
	 */
	private Map<String,Object> session;

	/**
	 * レスポンス
	 */
	private HttpServletResponse response;

	/**
	 * リクエスト
	 */
	private HttpServletRequest request;

	/**
	 * 実行メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return result 結果
	 * @throws Exception 例外処理
	 */
	public String execute()  throws Exception{
		GoGoogleOAuth googleOauth = new GoGoogleOAuth();
		if(!googleOauth.RequestToken(request,response)) {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * セッションを格納するためのメソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param session セッション
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * セッションを取得するためのメソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return session セッション
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * レスポンスを格納するためのメソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param response レスポンス
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * リクエストを格納するためのメソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param request リクエスト
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
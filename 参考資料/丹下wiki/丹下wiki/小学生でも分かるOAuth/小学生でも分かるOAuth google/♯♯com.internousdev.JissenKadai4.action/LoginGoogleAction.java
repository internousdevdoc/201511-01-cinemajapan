package com.internousdev.JissenKadai4.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.JissenKadai4.dao.LoginOauthDAO;
import com.internousdev.JissenKadai4.dto.LoginOauthDTO;
import com.internousdev.JissenKadai4.google.LoginGoogleOAuth;
import com.opensymphony.xwork2.ActionSupport;


/**
 * LoginGoogleAction Googleでログインする為のクラス
 * @author Nagata Shigeru
 * @since 2015/09/17
 * @version 1.0
 */
public class LoginGoogleAction extends ActionSupport implements
ServletRequestAware, SessionAware {

	/**
	 * 生成されたシリアルナンバー
	 */
	private static final long serialVersionUID = 4405462117636579678L;

	/**
	 * セッション
	 */
	private Map<String, Object> session;

	/**
	 * リクエスト
	 */
	private HttpServletRequest request;

	/**
	 * 結果
	 */
	private String result;

	/**
	 * グーグル認証の実行メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return result
	 */
	public String execute() throws Exception{
		result = ERROR;

			LoginGoogleOAuth googleOauth = new LoginGoogleOAuth();
			Map<String, String> userMap = googleOauth.AccessToken(request);
			if (userMap == null) {
				return result;
			}
			String uniqueId = userMap.get("id");
			session.put("uniqueId", uniqueId);
			String customerName = userMap.get("name");
			session.put("customerName", customerName);
			LoginOauthDAO loginGoogleDao = new LoginOauthDAO();
			if (!loginGoogleDao.existUniqueId(uniqueId)) {
				return result;
			}
			if (loginGoogleDao.selectUniqueId(uniqueId)) {
				LoginOauthDTO dto = loginGoogleDao.getLoginOauthDTO();
				session.put("userId",dto.getUserId());
				session.put("userName",dto.getName());
				session.put("userMailAddress",dto.getMailAddress());
				session.put("userTellNumber",dto.getTellNumber());
				session.put("userPostal",dto.getPostal());
				session.put("userAddress",dto.getAddress());
				session.put("userPassword",dto.getPassword());
				session.put("userUniquId",dto.getUniqueId());
			} else {
				return result;
			}
		result = SUCCESS;
		return result;
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
	 * @return session
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * レスポンスを格納するためのメソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param request リクエスト
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
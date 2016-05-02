package com.internousdev.JissenKadai4.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONValue;

import com.internousdev.JissenKadai4.dao.LoginOauthDAO;
import com.internousdev.JissenKadai4.dto.LoginOauthDTO;
import com.opensymphony.xwork2.ActionSupport;


/**
 * LoginFacebookAction Facebookでログインする為のクラス
 * @author Nagata Shigeru
 * @since 2015/09/17
 * @version 1.0
 */
public class LoginFacebookAction extends ActionSupport implements SessionAware,
ServletResponseAware, ServletRequestAware{

	/**
	 *生成されたシリアルナンバー
	 */
	private static final long serialVersionUID = 2731955946962434760L;

	/**
	 * レスポンス
	 */
	private HttpServletRequest request;

	/**
	 * リクエスト
	 */
	private HttpServletResponse response;

	/**
	 * 名前
	 */
	private String name;

	/**
	 * FacebookのユニークID
	 */
	private String uniqueId;

	/**
	 * ユーザーID
	 */
	private String id;

	/**
	 * 結果
	 */
	private String result;

	/**
	 * セッション
	 */
	public Map<String, Object> session;

	/**
	 * 実行メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return result 結果
	 * @throws Exception 例外処理
	 */
	public String execute() throws Exception {
		result = ERROR;
			getToken(request, response);
			if (id == null) {
				return result;
			}
			uniqueId = id;
			session.put("uniqueId", uniqueId);
			LoginOauthDAO loginFacebookDao = new LoginOauthDAO();
			boolean res = loginFacebookDao.existUniqueId(uniqueId);
			if (!res) {

				return result;
			}
			res = loginFacebookDao.selectUniqueId(uniqueId);
			if (res) {
				LoginOauthDTO dto = loginFacebookDao.getLoginOauthDTO();
				session.put("userId",dto.getUserId());
				session.put("userName",dto.getName());
				session.put("userMailAddress",dto.getMailAddress());
				session.put("userTellNumber",dto.getTellNumber());
				session.put("userPostal",dto.getPostal());
				session.put("userAddress",dto.getAddress());
				session.put("userPassword",dto.getPassword());
				session.put("userUniquId",dto.getUniqueId());
				result = SUCCESS;
			} else {
				return result;
			}
			return result;
		}

	/**
	 * ユーザー情報取得用トークン取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param request リクエスト
	 * @param response レスポンス
	 * @throws ServletException 例外処理
	 * @throws Exception 例外処理
	 */
	public void getToken(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, Exception {
		final String callbackURL = request.getRequestURL().toString();
		final String code = request.getParameter("code");
		if (code == null) {
			response.sendRedirect(request.getContextPath() + "/Login.jsp");
		}
		final String appId = "537629493054879";
		final String appSecret = "8663d0757027170aa79df504ccafdd67";
		final String accessTokenURL = "https://graph.facebook.com/oauth/access_token?client_id="
				+ appId
				+ "&redirect_uri="
				+ URLEncoder.encode(callbackURL, "UTF-8")
				+ "&client_secret="
				+ appSecret + "&code=" + URLEncoder.encode(code, "UTF-8");
		final String accessTokenResult = httpRequest(new URL(accessTokenURL));
		String accessToken = null;
		String[] pairs = accessTokenResult.split("&");
		for (String pair : pairs) {
			String[] kv = pair.split("=");
			if (kv.length != 2) {
				throw new RuntimeException("Unexpected auth response");
			} else {
				if (kv[0].equals("access_token")) {
					accessToken = kv[1];
				}
			}
		}
		final String apiURL = "https://graph.facebook.com/me?access_token="
				+ URLEncoder.encode(accessToken, "UTF-8");
		final String apiResult = httpRequest(new URL(apiURL));
		Map<?, ?> me = (Map<?, ?>) JSONValue.parse(apiResult);
		Map<?, ?> map = new HashMap<Object, Object>();
		name = String.valueOf(me.get("name"));
		id = String.valueOf(me.get("id"));
		session.put("familyname", name);
		session.put("id", id);
		session.put("accessToken", accessToken);
		request.getSession().setAttribute("loginUser", map);
	}

	String httpRequest(URL url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("GET");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String line = null;
		String response = "";
		while ((line = reader.readLine()) != null) {
			response += line;
		}
		reader.close();
		conn.disconnect();
		return response;
	}

	/**
	 * リクエスト格納メソッド
     * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param request リクエスト
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * レスポンス格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param response レスポンス
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * セッション取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return session セッション
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * セッション格納メソッド
     * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param session セッション
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
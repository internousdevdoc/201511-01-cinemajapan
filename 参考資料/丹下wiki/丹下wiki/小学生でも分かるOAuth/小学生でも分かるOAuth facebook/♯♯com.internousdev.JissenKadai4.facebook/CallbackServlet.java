package com.internousdev.JissenKadai4.facebook;
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

import com.internousdev.JissenKadai4.dao.LoginUserDAO;
import com.internousdev.JissenKadai4.dto.LoginUserDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * CallbackServlet
 * ログイン画面からFacebook認証画面へと遷移する為のアクションクラス
 * @author 根井 孝裕
 * @since 2015/8/07
 * @version 1.0
 */
public class CallbackServlet extends ActionSupport implements SessionAware,
		ServletResponseAware, ServletRequestAware {

	/**
	 * シリアルバージョンIDの生成
	 */
	private static final long serialVersionUID = 7463433040601990718L;

	/**
	 * ネットワークネーム
	 */
	static final String NETWORK_NAME = "Facebook";

	/**
	 * レスポンス
	 */
	private HttpServletRequest request;

	/**
	 * リクエスト
	 */
	private HttpServletResponse response;

	/**
	 * セッション
	 */
	public Map<String, Object> sessionMap;

    /**
     * 名前
     */
	public String username;

    /**
     * ユーザーログインID
     */
	public String userUniqueId;


    /**
	 * 実行メソッド
	 * @return result
	 */
	public String execute() throws Exception {
		String result;
		result = doGet(request, response);
		return result;
	}

	/**
	 * ユーザー情報取得用トークン取得メソッド
	 * @param request　リクエスト
	 * @param response　レスポンス
	 * @throws ServletException 例外処理
	 * @throws Exception 例外処理
	 * @return リターン
	 */
	public String doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {
		String result;
		final String callbackURL = request.getRequestURL().toString();
		final String code = request.getParameter("code");
		if(code == null){
			 response.sendRedirect(request.getContextPath()+ "/Main.jsp");
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

		username = String.valueOf(me.get("name"));

		userUniqueId = String.valueOf(me.get("id"));





		LoginUserDAO dao = new LoginUserDAO();
		LoginUserDTO dto;
		if(dao.selectByUserUniqueId(userUniqueId)){
			dto = dao.getLoginUserDTO();

			sessionMap.put("userId", dto.getUserId());
			sessionMap.put("loginId", dto.getLoginId());
			sessionMap.put("password", dto.getPassword());
			sessionMap.put("userName", dto.getUserName());
			sessionMap.put("uniqueId", dto.getUniqueId());
			result = "success";

			if(sessionMap.containsKey("prePage")){

				String prePage = (String)sessionMap.get("prePage");

				if(prePage.equals("selectProductPage")){
					result = "selectProductPage";
				}

				if(prePage.equals("selectDetailPage")){
					result = "selectDetailPage";
				}

				if(prePage.equals("cartPage")){
					result = "cartPage";
				}


			}


		}else {
			sessionMap.put("userUniqueId", userUniqueId);
			result = "firstcontact";
		}


		request.getSession().setAttribute("loginUser", map);
		return result;
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
	 * @param request リクエスト
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * レスポンス格納メソッド
	 * @param response レスポンス
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * セッション取得メソッド
	 * @return sessionMap セッションマップ
	 */
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	/**
	 * セッション格納メソッド
	 * @param sessionMap　セッションマップ
	 */
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	/**
	 * セッション格納メソッド
	 * @param sessionMap セッションマップ
	 */
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
}
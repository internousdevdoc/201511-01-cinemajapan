package com.internousdev.JissenKadai4.google;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

/**
 * LoginGoogleOAuth Googleでログインする為のクラス
 * @author Nagata Shigeru
 * @since 2015/09/17
 * @version 1.0
 */
public class LoginGoogleOAuth extends ActionSupport {

	/**
	 * 生成されたシリアルナンバー
	 */
	private static final long serialVersionUID = -564268116563098912L;

	/**
	 * レスポンスURL
	 */
	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v1/userinfo";

	/**
	 * 空のトークン
	 */
	private static final Token EMPTY_TOKEN = null;

	/**
	 * ユーザー情報のMAPを取得するメソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param request リクエスト
	 * @return map
	 */
	public Map<String,String> AccessToken(HttpServletRequest request){
		Map<String,String> map;
		try {
			String verifier1 = request.getParameter("code");

			Verifier verifier =new Verifier(verifier1);
			Token accessToken = new Token("ACCESS_TOKEN", "REFRESH_TOKEN");

			HttpSession session = request.getSession();
			OAuthService service = (OAuthService) session.getAttribute("SERVICE");

			accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);

			OAuthRequest requestOauth = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
			service.signRequest(accessToken, requestOauth);
			Response response = requestOauth.send();
			map = new LinkedHashMap<>();
			ObjectMapper mapper = new ObjectMapper();

			map = mapper.readValue(response.getBody(), new TypeReference<LinkedHashMap<String,String>>(){});
		} catch (Exception e) {
			return null;
		}
		return map;
	}

}
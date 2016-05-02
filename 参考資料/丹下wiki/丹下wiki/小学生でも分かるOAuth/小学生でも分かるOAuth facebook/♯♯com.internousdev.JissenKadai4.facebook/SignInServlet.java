package com.internousdev.JissenKadai4.facebook;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

/**
 * SignInServlet
 * ログイン画面からFacebook認証画面へと遷移する為のアクションクラス
 * @author 根井 孝裕
 * @since 2015/08/07
 * @version 1.0
 */
public class SignInServlet extends ActionSupport implements ServletResponseAware,ServletRequestAware  {

	/**
	 * シリアルバージョンIDの生成
	 */
	private static final long serialVersionUID = 8452909841605567236L;

	/**
	 * リクエスト
	 */
	private HttpServletRequest request;

	/**
	 * レスポンス
	 */
	private HttpServletResponse response;

	/**
	 * ユーザー情報取得用トークン取得メソッド
	 * @return SUCCESS
	 */
    public String execute() throws Exception {
    	doGet(request, response);
		return SUCCESS;
	}

    /**
     * Facebook認証画面へ遷移する為のメソッド
     * @param request　リクエスト
     * @param response レスポンス
     * @throws ServletException 例外処理
     * @throws IOException 例外処理
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Facebook facebook = new FacebookFactory().getInstance();
        request.getSession().setAttribute("facebook", facebook);
        facebook.setOAuthAppId("537629493054879", "8663d0757027170aa79df504ccafdd67");
        String accessTokenString ="537629493054879 |50add4e225aafa35275a68b405db7d07";
        AccessToken at = new AccessToken(accessTokenString);
        facebook.setOAuthAccessToken(at);
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/CallbackServlet");
        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
    }
	/**
	 * リクエスト格納メソッド
	 * @param request リクエスト
	 */
	public void setServletRequest(HttpServletRequest request) {
				this.request=request;
	}

	/**
	 * レスポンス格納メソッド
	 * @param response レスポンス
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
}

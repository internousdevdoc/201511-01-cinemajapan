package com.internousdev.JissenKadai4.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.internousdev.JissenKadai4.twitter.TwitterRequestToken;
import com.opensymphony.xwork2.ActionSupport;

import twitter4j.TwitterException;

/**
* GoTwitterAction
* ログイン画面からTwitter認証画面へ遷移する為のアクションクラス
* @author 根井 孝裕
* @since 2015/08/07
* @version 1.0
*/
public class GoTwitterAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	/**
	 * シリアルバージョンIDの生成
	 */
	private static final long serialVersionUID = -6534378494906244828L;

	/**
	* 結果
	*/
	private String result= SUCCESS;

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
	* @since 2015/08/07
	* @return result
	*/
	public String execute(){
		TwitterRequestToken a = new TwitterRequestToken();
		try {
			result=a.loginOAuth(response,request);
		} catch (TwitterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * レスポンス格納メソッド
	 * @param response レスポンス
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * リクエスト格納メソッド
	 * @param request リクエスト
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
}

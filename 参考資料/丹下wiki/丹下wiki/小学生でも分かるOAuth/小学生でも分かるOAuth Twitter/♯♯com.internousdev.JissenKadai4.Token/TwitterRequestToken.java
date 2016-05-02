package com.internousdev.JissenKadai4.twitter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 * TwitterRequestToken
 * Twitterのリクエストトークンを取得する為のクラス
 * @author 根井 孝裕
 * @since 2015/08/07
 * @version 1.0
 */
public class TwitterRequestToken {

	/**
	* 結果
	*/
	String result="error";

	/**
	* コンシューマーキー
	*/
	final String CONSUMER_KEY = "EQ3bPD0R9WsMcEFquWOGGT0e1";

	/**
	* コンシューマーシークレット
	*/
	final String CONSUMER_SECRET = "RNfYtYqKcPOTwhHctAmtzjG1JbnljQRAcK86wvZX9Y0vqQyAa9";
	static TwitterFactory twitterFactory = new TwitterFactory();

	/**
	* 認証用トークン取得メソッド
	* @return result
	* @param response レスポンス
	* @param request リクエスト
	* @throws TwitterException 例外処理
	* @throws IOException 例外処理
	*/
	public String loginOAuth(HttpServletResponse response,HttpServletRequest request) throws TwitterException, IOException {
	        Twitter twitter = twitterFactory.getInstance();
	        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
	        try{
	            RequestToken reqToken = twitter.getOAuthRequestToken();
	            HttpSession session = request.getSession();
	            session.setAttribute("RequestToken", reqToken);
	            session.setAttribute("Twitter", twitter);
	            String strUrl = reqToken.getAuthorizationURL();
	            response.sendRedirect(strUrl);
	        }catch (TwitterException e){
	        }
		return result;
}
}
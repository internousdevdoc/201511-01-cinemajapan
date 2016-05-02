package com.internousdev.JissenKadai4.dto;

import java.util.Map;

/**
 * LoginOauthDTO 顧客情報を取得・格納する為のクラス
 * @author Nagata Shigeru
 * @since 2015/09/17
 * @version 1.0
 */
public class LoginOauthDTO {


	/**
	 * ユーザーID
	 */
	private int userId;

	/**
	 * 名前
	 */
	private String name;

	/**
	 * メールアドレス
	 */
	private String mailAddress;

	/**
	 * 電話番号
	 */
	private String tellNumber;

	/**
	 * 郵便番号
	 */
	private String postal;

	/**
	 * 住所
	 */
	private String address;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * 固有ID
	 */
	private int uniqueId;


	/**
	 * セッション
	 */
	private Map<String,Object> session;


	/**
	 * 顧客情報テーブルのuserId取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return userId ユーザーID
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 顧客情報テーブルのuserId格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
     * @param userId ユーザーID
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 顧客情報テーブルのname取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return name 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 顧客情報テーブルのname格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 顧客情報テーブルのmailAddress取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return mailAddress メールアドレス
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * 顧客情報テーブルのmailAddress格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param mailAddress メールアドレス
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * 顧客情報テーブルのtellNumber取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return tellNumber 電話番号
	 */
	public String getTellNumber() {
		return tellNumber;
	}

	/**
	 * 顧客情報テーブルのtellNumber格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param tellNumber 電話番号
	 */
	public void setTellNumber(String tellNumber) {
		this.tellNumber = tellNumber;
	}

	/**
	 * 顧客情報テーブルのpostal取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return postal 郵便番号
	 */
	public String getPostal() {
		return postal;
	}

	/**
	 * 顧客情報テーブルのpostal格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param postal 郵便番号
	 */
	public void setPostal(String postal) {
		this.postal = postal;
	}

	/**
	 * 顧客情報テーブルのaddress取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return address 住所
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 顧客情報テーブルのaddress格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 顧客情報テーブルのpassword取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return password パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 顧客情報テーブルのpassword格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 顧客情報テーブルのuniqueId取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return uniqueId 固有ID
	 */
	public int getUniqueId() {
		return uniqueId;
	}

	/**
	 * 顧客情報テーブルのuniqueId格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param uniqueId 固有ID
	 */
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	/**
	 * 顧客情報テーブルのsession取得メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @return session セッション
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * 顧客情報テーブルのsession格納メソッド
	 * @author Nagata Shigeru
     * @since 2015/09/17
	 * @param session セッション
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}

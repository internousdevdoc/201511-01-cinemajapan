package com.internousdev.JissenKadai4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.JissenKadai4.dto.LoginOauthDTO;
import com.internousdev.JissenKadai4.util.DBConnector;

/**
 * LoginOauthDAO SNSでログインに必要な情報を取得する為のクラス
 * @author Nagata Shigeru
 * @since 2015/09/14
 * @version 1.0
 */
public class LoginOauthDAO {

	/**
	 * コネクションクラス
	 */
	private Connection con;

	/**
	 * sql文1
	 */
	private String sql;

	/**
	 * PrepareStatement
	 */
	private PreparedStatement ps;

	/**
	 * 結果
	 */
	private boolean result;

	/**
	 * sql文実行の結果
	 */
	private ResultSet rs;

	/**
	 * OauthのDTO
	 */
	private LoginOauthDTO oauthDto;

	/**
	 * DBconnector
	 */
	private DBConnector db;


	/**
	 * ユニークIDが一致するユーザーを取得するメソッド
	 * @author Nagata Shigeru
	 * @since 2015/08/17
	 * @param uniqueId ユニークID
	 * @return result 結果
	 * @throws Exception 例外処理
	 */
	public boolean existUniqueId(String uniqueId) throws Exception {

		result = false;

		try {
			db = new DBConnector();
			db.setDB("aquarium");
			con = DBConnector.getConnection();

			sql = "SELECT * from user where unique_id=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, uniqueId);
			rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}finally{
			con.close();
		}
		return result;
	}

	/**
	 * ユニークIDが一致した顧客情報を取得するメソッド
	 * @author Nagata Shigeru
	 * @since 2015/09/14
	 * @param unique ユニークID
	 * @return result 結果
	 * @throws SQLException 例外処理
	 */
	public boolean selectUniqueId(String unique) throws SQLException {

		boolean result = false;

		try {
			db = new DBConnector();
			db.setDB("aquarium");
			con = DBConnector.getConnection();

			sql = "SELECT * from user where unique_id=? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, unique);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				oauthDto = new LoginOauthDTO();
				result = true;
				oauthDto.setUserId(rs.getInt(1));
				oauthDto.setName(rs.getString(2));
				oauthDto.setMailAddress(rs.getString(4));
				oauthDto.setTellNumber(rs.getString(5));
				oauthDto.setPostal(rs.getString(6));
				oauthDto.setAddress(rs.getString(7));
				oauthDto.setPassword(rs.getString(3));
				oauthDto.setUniqueId(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}finally{
			con.close();
		}
		return result;
	}

	/**
	 * LoginOauthDTOを取得するメソッド
	 * @author Nagata Shigeru
	 * @since 2015/09/14
	 * @return oauthDto
	 */
	public LoginOauthDTO getLoginOauthDTO() {
		return oauthDto;
	}

	/**
	 * LoginOauthDTOを格納するメソッド
	 * @author Nagata Shigeru
	 * @since 2015/09/14
	 * @param LoginOauthDto OauthのDTO
	 */
	public void setLoginOauthDto(LoginOauthDTO LoginOauthDto) {
		this.oauthDto = LoginOauthDto;
	}
}

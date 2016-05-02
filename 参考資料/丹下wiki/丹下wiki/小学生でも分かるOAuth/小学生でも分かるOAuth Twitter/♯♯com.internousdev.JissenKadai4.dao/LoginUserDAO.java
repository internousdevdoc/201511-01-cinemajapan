package com.internousdev.JissenKadai4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.JissenKadai4.dto.LoginUserDTO;

/**
 * LoginUserDAO
 * ユーザーがログインする際DBに接続する為のDAOクラス
 * @author 大嶋 里奈
 * @since 2015/08/10
 * @version 1.0
 *
 */
public class LoginUserDAO{

	/**
	 * 取得した情報を格納する為のDTO
	 */
	private LoginUserDTO dto=new LoginUserDTO();

	/**
	 * selectByUserLoginId
	 * 入力されたログインIDとパスワードをDBと照合するメソッド
	 * @param loginId ログインID
	 * @param password パスワード
	 * @return result
	 */
	public boolean selectByUserLoginId(String loginId, String password){
		boolean result=false;
		Connection con=DBconnector.getConnection();
		try{
			String sql="select userName,userId,loginId,password,eMail,telNumber,postal,address,uniqueId from userInfo where loginId=? and password=?";
			PreparedStatement ps =con.prepareStatement(sql);
			ps.setString(1,loginId);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();

			if(rs.next()){

				dto.setUserName(rs.getString(1));
				dto.setUserId(rs.getInt(2));
				dto.setLoginId(rs.getString(3));
				dto.setPassword(rs.getString(4));
				dto.seteMail(rs.getString(5));
				dto.setTelNumber(rs.getString(6));
				dto.setPostal(rs.getString(7));
				dto.setAddress(rs.getString(8));
				dto.setUniqueId(rs.getString(9));


				result=true;
			}

		}catch(SQLException e){
			e.printStackTrace();

		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;

	}

	/**
	 * 取得したユニークIDを照合するためのメソッド
	 * @param userUniqueId ユニークID
	 * @return result
	 */
	public boolean selectByUserUniqueId(String userUniqueId){
		boolean result=false;
		Connection con=DBconnector.getConnection();
		try{
			String sql="select userName,userId,loginId,password,eMail,telNumber,postal,address,uniqueId from userInfo where uniqueId = ?";
			PreparedStatement ps =con.prepareStatement(sql);
			ps.setString(1,userUniqueId);
			ResultSet rs=ps.executeQuery();

			if(rs.next()){

				dto.setUserName(rs.getString(1));
				dto.setUserId(rs.getInt(2));
				dto.setLoginId(rs.getString(3));
				dto.setPassword(rs.getString(4));
				dto.seteMail(rs.getString(5));
				dto.setTelNumber(rs.getString(6));
				dto.setPostal(rs.getString(7));
				dto.setAddress(rs.getString(8));
				dto.setUniqueId(rs.getString(9));


				result=true;
			}

		}catch(SQLException e){
			e.printStackTrace();

		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;

	}

	/**
	 * DTO取得メソッド
	 * @return dto
	 */
	public LoginUserDTO getLoginUserDTO() {
		return dto;
	}

	/**
	 * DTO格納メソッド
	 * @param dto ログインユーザDTO
	 */
	public void setLoginUserDTO(LoginUserDTO dto) {
		this.dto = dto;
	}

}
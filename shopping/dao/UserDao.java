package com.ty.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ty.shopping.dto.User;
import com.ty.shopping.util.AES;
import com.ty.shopping.util.ConnectionObject;
import static com.ty.shopping.util.AppConstants.SECRET_KEY;

public class UserDao {
	
	//private String secret = "chetan";
	
	public int saveUser(User user) {
		String sql = "insert into user values(?,?,?,?,?)";
		Connection connection = ConnectionObject.getConnection();
		try {
			
			String enc = AES.encrypt(user.getPassword(), SECRET_KEY);
			
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, user.getId());
			preparedstatement.setString(2, user.getName());
			preparedstatement.setString(3, user.getEmail());
			preparedstatement.setString(4, enc);
			preparedstatement.setLong(5, user.getMobile());
			
			return preparedstatement.executeUpdate();
			//System.out.println("Data is inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	
//	public User validateUser(String email, String password) {
//		String sql = "Select * from user where email=? and password=?";
//		Connection connection = ConnectionObject.getConnection();
//		
//		try {
//			String enc = AES.encrypt(password, SECRET_KEY);
//			
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, email);
//			preparedStatement.setString(2, enc);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			
//			if(resultSet.next()) {
//				User user = new User();
//				user.setId(resultSet.getInt(1));
//				user.setName(resultSet.getString(2));
//				user.setEmail(resultSet.getString(3));
//				user.setMobile(resultSet.getLong(5));
//				return user;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			if(connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
	
	public int deleteUserById(int id) {
		String sql = "DELETE TABLE user WHERE id = ?";
		Connection connection = ConnectionObject.getConnection();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (connection != null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return 0;
	}
	
	public int updateUser(int id, User user) {
		String query = "UPDATE user SET name = ?, email = ?,password = ?, phone = ? where id = ?";
		Connection connection = ConnectionObject.getConnection();
		String enc = AES.encrypt(user.getPassword(), SECRET_KEY);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, enc);
			preparedStatement.setLong(4, user.getMobile());
			preparedStatement.setInt(5, user.getId());

			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (connection != null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return 0;
	}
	
	public User getUserById(int id) {
		String query = "SELECT * FROM user WHERE id = ?";
		Connection connection = ConnectionObject.getConnection();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setMobile(resultSet.getInt(5));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
	public User validateUser(String email, String password) {
		String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
		String enc = AES.encrypt(password, SECRET_KEY);
		Connection connection = ConnectionObject.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, enc);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setMobile(resultSet.getInt(5));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (connection != null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
}

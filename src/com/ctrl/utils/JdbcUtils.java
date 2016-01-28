/**
 * @author CTRL 
 * Classname : JdbcUtils 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	// 定义私有的数据库连接池对象
	private static DataSource dataSource = null;

	// 静态代码块获取数据库连接池
	static {
		dataSource = new ComboPooledDataSource("app");
	}

	// 获取数据库连接
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	// 释放数据库连接
	public static void release(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 事务提交
	public static void commit(Connection connection) {
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 回滚事务
	public static void rollback(Connection connection) {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 开始事务
	public static void beginTransaction(Connection connection) {
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

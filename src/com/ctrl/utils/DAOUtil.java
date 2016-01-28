/**
 * @author CTRL 
 * Classname : DAOUtil 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ctrl.utils.JdbcUtils;
import com.ctrl.vo.Message;

public class DAOUtil<T> {

	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;

	// 进行反射，得到泛型 T 对象的实例
	@SuppressWarnings("unchecked")
	public DAOUtil() {

		Type superClass = getClass().getGenericSuperclass();
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			Type[] typeArgs = parameterizedType.getActualTypeArguments();
			if (typeArgs != null && typeArgs.length > 0) {
				if (typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}
			}
		}
	}

	// 获取某个字段的值
	@SuppressWarnings("unchecked")
	public <E> E getForValue(String sql, Object... args) {

		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			return (E) queryRunner.query(connection, sql, new ScalarHandler(),
					args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(connection);
		}
		return null;
	}

	// 获取若干调记录，以list 返回
	public List<T> getForList(String sql, Object... args) {

		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<T>(
					clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(connection);
		}
		return null;
	}

	// 获取某对象
	public T get(String sql, Object... args) {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql,
					new BeanHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(connection);
		}
		return null;
	}

	// 更新记录
	public void update(String sql, Object... args) {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			queryRunner.update(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(connection);
		}
	}

	// 批处理及事务插入数据库
	public void updateWithBlog(String sql, Message[] message) {
		Connection connection = null;
		PreparedStatement prst = null;

		try {
			connection = JdbcUtils.getConnection();
			prst = connection.prepareStatement(sql);
			JdbcUtils.beginTransaction(connection);
			for (int i = 0; i < message.length; i++) {
				prst.setString(1, message[i].getContent());
				prst.setInt(2, message[i].getStaff_id());
				prst.setTimestamp(3, new Timestamp(message[i].getCreated_dt()
						.getTime()));
				prst.addBatch();
			}
			prst.executeBatch();
			prst.clearBatch();
			JdbcUtils.commit(connection);
		} catch (Exception e) {
			JdbcUtils.rollback(connection);
			e.printStackTrace();
		} finally {
			JdbcUtils.release(connection);
		}
	}

}

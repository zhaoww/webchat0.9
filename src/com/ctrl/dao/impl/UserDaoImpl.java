/**
 * @author CTRL 
 * Classname : UserDaoImpl 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.dao.impl;

import java.util.List;

import com.ctrl.dao.UserDao;
import com.ctrl.utils.DAOUtil;
import com.ctrl.utils.FuzzyQueryUtils;
import com.ctrl.vo.User;

public class UserDaoImpl extends DAOUtil<User> implements UserDao {

	@Override
	public List<User> getAll() {
		String sql = "SELECT id,name,code, pwd, state,flag from staff";
		return getForList(sql);
	}

	@Override
	public void save(User user) {
		String sql = "INSERT INTO staff(name,code,pwd,state,flag) VALUES(?,?,?,?,?)";
		update(sql, user.getName(), user.getCode(), user.getPwd(),
				user.getState(), user.getFlag());
	}

	@Override
	public User get(Integer id) {
		String sql = "SELECT id,name,code, pwd, state,flag FROM staff WHERE id = ?";
		return get(sql, id);
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM staff WHERE id = ?";
		update(sql, id);
	}

	@Override
	public long getCountWithCode(String code) {
		String sql = "SELECT count(id) FROM staff WHERE code = ?";
		return getForValue(sql, code);
	}

	// ע�⣺����ִ�Сд
	@Override
	public List<User> fuzzyQuery(FuzzyQueryUtils fq) {
		String sql = "SELECT id,name,code, pwd, state,flag FROM staff "
				+ "WHERE (name LIKE ? OR code LIKE ?) and name <> 'root'";
		return getForList(sql, fq.getName(), fq.getCode());
	}

	@Override
	public void update(User user) {

		String sql = "UPDATE staff SET pwd = ?,state = ?,flag = ? WHERE id = ?";
		update(sql, user.getPwd(), user.getState(), user.getFlag(),
				user.getId());
	}

	@Override
	public long getCountWithPwd(String userCode, String pwd) {
		String sql = "SELECT count(*) FROM staff WHERE code=? and pwd = ?";
		return getForValue(sql, userCode, pwd);
	}

	@Override
	public User getByCode(String code) {
		// TODO Auto-generated method stub
		String sql = "select id,name,code, pwd, state,flag from staff where code = ?";
		return get(sql, code);
	}

	@Override
	public Long getUsersCount() {
		String sql = "select count(*) from staff";
		return getForValue(sql);
	}

	@Override
	public List<User> getLimitUsers(int begin, int size) {
		String sql = "SELECT id,name,code, pwd, state,flag FROM staff WHERE name!='root' limit ? , ? ";
		return getForList(sql, begin, size);
	}

	@Override
	public User getByName(String name) {
		String sql = "select * from staff where name=?";
		return get(sql, name);
	}

}

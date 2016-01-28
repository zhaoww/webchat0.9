/**
 * @author CTRL 
 * Classname : UserServiceImpl 
 * Version information : modified 
 * Date : 2016-01-27 
 * Copyright notice : CTRL
 */
package com.ctrl.service.impl;

import java.util.List;

import com.ctrl.dao.UserDao;
import com.ctrl.dao.impl.UserDaoImpl;
import com.ctrl.dao.proxy.UserDaoLoggingProxy;
import com.ctrl.service.UserService;
import com.ctrl.utils.FuzzyQueryUtils;
import com.ctrl.vo.User;

public class UserServiceImpl implements UserService {

	UserDao target = new UserDaoImpl();
	// 日志代理
	UserDao userDao = new UserDaoLoggingProxy(target).getLoggingProxy();

	@Override
	public void update(User user) {

		userDao.update(user);
	}

	@Override
	public List<User> fuzzyQuery(FuzzyQueryUtils fq) {

		return userDao.fuzzyQuery(fq);
	}

	@Override
	public List<User> getAll() {

		return userDao.getAll();
	}

	@Override
	public void save(User user) {

		userDao.save(user);
	}

	@Override
	public User get(Integer id) {

		return userDao.get(id);
	}

	@Override
	public void delete(Integer id) {

		userDao.delete(id);
	}

	@Override
	public User getByCode(String code) {
		
		return userDao.getByCode(code);
	}

	@Override
	public Long getUsersCount() {
		return userDao.getUsersCount();
	}

	@Override
	public List<User> getLimitUsers(int begin, int size) {
		
		return userDao.getLimitUsers(begin, size);
	}

	@Override
	public long getCountWithCode(String userCode) {
		
		return userDao.getCountWithCode(userCode);
	}

	@Override
	public long getCountWithPwd(String userCode, String pwd) {
		return userDao.getCountWithPwd(userCode, pwd);
	}

	@Override
	public User getByName(String name) {
		return userDao.getByName(name);
	}

}

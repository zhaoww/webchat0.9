/**
 * @author CTRL 
 * Classname : UserDao 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.dao;

import java.util.List;

import com.ctrl.utils.FuzzyQueryUtils;
import com.ctrl.vo.User;

public interface UserDao {

	// 根据用户名获取用户对象
	public User getByName(String name);

	// 修改用户信息
	public void update(User user);

	// 模糊查询
	public List<User> fuzzyQuery(FuzzyQueryUtils fq);

	// 获取所有的用户
	public List<User> getAll();

	// 保存用户
	public void save(User user);

	// 根据id 获取用户
	public User get(Integer id);

	// 根据id 删除用户
	public void delete(Integer id);

	// 通过code 获取用户数量
	public long getCountWithCode(String code);

	// 根据code和密码获取用户数量
	public long getCountWithPwd(String userCode, String pwd);

	// 通过code获取用户对象
	public User getByCode(String code);

	// 获取用户的数量
	public Long getUsersCount();

	// 从某一个位置获取指定数量的用户对象
	public List<User> getLimitUsers(int begin, int size);
}

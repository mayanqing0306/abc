package com.itheima.dao;


import java.util.List;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

//持久层的操作接口
public interface UserDao {
	//list<>hhh
	List<User> findUserByQueryVo(QueryVo vo);

	/**
	 * 查询所有用户信息
	 * @return
	 */
	List<User> findAll();
	/**
	 * 根据id查询用户信息
	 * @return
	 */
	User findByid(Integer userid);
	
	/**
	 * 保存用户信息（添加用户信息）
	 */
	void saveUser(User user);
	
	/**
	 * 修改用户信息
	 */
	void updateUser(User user);
	
	/**
	 * 删除用户信息,根据用户id对用户进行删除操作
	 */
	void deleteUser(Integer userid);
	
	/**
	 * 模糊查询（根据用户名查询信息）
	 * @param username
	 * @return
	 */
	List<User> findByname(String username);
	
	/**
	 * 查询总条数
	 * @return
	 */
	int findTotal();
	
}

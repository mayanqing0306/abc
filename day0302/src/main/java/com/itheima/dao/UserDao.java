package com.itheima.dao;


import java.util.List;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

//�־ò�Ĳ����ӿ�
public interface UserDao {
	//list<>hhhxjb
	List<User> findUserByQueryVo(QueryVo vo);

	/**
	 * ��ѯ�����û���Ϣ
	 * @return
	 */
	List<User> findAll();
	/**
	 * ����id��ѯ�û���Ϣ
	 * @return
	 */
	User findByid(Integer userid);
	
	/**
	 * �����û���Ϣ������û���Ϣ��
	 */
	void saveUser(User user);
	
	/**
	 * �޸��û���Ϣ
	 */
	void updateUser(User user);
	
	/**
	 * ɾ���û���Ϣ,�����û�id���û�����ɾ������
	 */
	void deleteUser(Integer userid);
	
	/**
	 * ģ����ѯ�������û�����ѯ��Ϣ��
	 * @param username
	 * @return
	 */
	List<User> findByname(String username);
	
	/**
	 * ��ѯ������
	 * @return
	 */
	int findTotal();
	
}

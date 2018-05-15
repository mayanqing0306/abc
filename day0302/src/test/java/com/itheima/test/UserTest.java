package com.itheima.test;
/**
 * mybatis�����Ű���
 * @author Administrator
 *
 */


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itheima.dao.UserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

public class UserTest {
	
	private InputStream in;
	private SqlSessionFactory factory;
	private SqlSession session;
	private UserDao userdao;
	
	@Before//�����ڲ��Է���֮ǰִ��
	public void init() throws Exception {
		
		//1.��ȡ�������ļ�
		in = Resources.getResourceAsStream("SqlMapConfig.xml");
		//2.����SqlSessionfactory����
		factory=new SqlSessionFactoryBuilder().build(in);
		//3.��ȡsession����
		session=factory.openSession();
		//4.����dao�ӿڵĴ������
		userdao=session.getMapper(UserDao.class);
	}
	
	@After//�ڲ��Է�����ִ��
	public void destory() throws Exception {
		session.close();
		in.close();
	}

	@Test
	public void testFindUserByQueryVo() {
		QueryVo vo=new QueryVo();
		User user=new User();
		user.setUsername("С����");
		vo.setUser(user);
		List<User> users = userdao.findUserByQueryVo(vo);
		for(User u : users) {
			System.out.println(u);
		}
	}
	
	@Test
	/**
	 * ��ѯ�����û���Ϣ
	 * @throws Exception
	 */
	public void testFindAll() throws Exception {
		//ʹ�ô���������findAll������ѯ
		List<User> users=userdao.findAll();
		for (User user : users) {
			System.out.println(user);
		}
		
	}
	@Test
	/**
	 * ����id��ѯ�û���Ϣ
	 */
	public void testfindByid() {
		//ʹ�ô���������findbyid������ѯ��Ϣ
		User user=userdao.findByid(41);
		System.out.println(user);
	
	}
	
	@Test
	/**
	 * ��ӵķ���
	 */
	public void saveUser() {
		
		User user=new User();
		user.setUsername("����");
		user.setSex("n");
		user.setBirthday(new Date());
		user.setAddress("�ӱ�");
		userdao.saveUser(user);
		session.commit();
		
	}
	
	@Test
	/**
	 * �޸ĵķ���,ͨ��id��ѯ���û���Ϣ�����޸�
	 */
	public void testupdate() {
		//ʹ�ô���������findByid������ѯ��Ϣ
		User user = userdao.findByid(55);
		user.setUsername("����");
		userdao.updateUser(user);
		session.commit();
		System.out.println(user);
	}
	
	@Test
	/**
	 * ɾ���ķ�����ͨ��id����ɾ��
	 */
	public void testdelete() {
		//ʹ�ô���������deleteUser����ɾ����Ϣ
		userdao.deleteUser(50);
		session.commit();
	}
	
	@Test
	/**
	 * ģ����ѯ
	 */
	public void testfindByname() {
		//����һ
		//ʹ�ô���������findByname����ɾ����Ϣ
		List<User> list = userdao.findByname("%��%");
		for (User user : list) {
			System.out.println(user);
		}
		/*//������
		List<User> list = userdao.findByname("��");
		for (User user : list) {
			System.out.println(user);
		}*/
		
	}
	
	@Test
	/**
	 * ��ѯ������
	 */
	public void testcount() {
		//ʹ�ô���������findTotal����ɾ����Ϣ
		Integer count = userdao.findTotal();
		System.out.println(count);
	}
	
}

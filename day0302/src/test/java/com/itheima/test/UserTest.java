package com.itheima.test;
/**
 * mybatis的入门案例
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
	
	@Before//用于在测试方法之前执行
	public void init() throws Exception {
		
		//1.读取主配置文件
		in = Resources.getResourceAsStream("SqlMapConfig.xml");
		//2.创建SqlSessionfactory对象
		factory=new SqlSessionFactoryBuilder().build(in);
		//3.获取session对象
		session=factory.openSession();
		//4.创建dao接口的代理对象
		userdao=session.getMapper(UserDao.class);
	}
	
	@After//在测试方法后执行
	public void destory() throws Exception {
		session.close();
		in.close();
	}

	@Test
	public void testFindUserByQueryVo() {
		QueryVo vo=new QueryVo();
		User user=new User();
		user.setUsername("小二王");
		vo.setUser(user);
		List<User> users = userdao.findUserByQueryVo(vo);
		for(User u : users) {
			System.out.println(u);
		}
	}
	
	@Test
	/**
	 * 查询所有用户信息
	 * @throws Exception
	 */
	public void testFindAll() throws Exception {
		//使用代理对象调用findAll方法查询
		List<User> users=userdao.findAll();
		for (User user : users) {
			System.out.println(user);
		}
		
	}
	@Test
	/**
	 * 根据id查询用户信息
	 */
	public void testfindByid() {
		//使用代理对象调用findbyid方法查询信息
		User user=userdao.findByid(41);
		System.out.println(user);
	
	}
	
	@Test
	/**
	 * 添加的方法
	 */
	public void saveUser() {
		
		User user=new User();
		user.setUsername("王五");
		user.setSex("n");
		user.setBirthday(new Date());
		user.setAddress("河北");
		userdao.saveUser(user);
		session.commit();
		
	}
	
	@Test
	/**
	 * 修改的方法,通过id查询到用户信息进行修改
	 */
	public void testupdate() {
		//使用代理对象调用findByid方法查询信息
		User user = userdao.findByid(55);
		user.setUsername("周晓");
		userdao.updateUser(user);
		session.commit();
		System.out.println(user);
	}
	
	@Test
	/**
	 * 删除的方法，通过id进行删除
	 */
	public void testdelete() {
		//使用代理对象调用deleteUser方法删除信息
		userdao.deleteUser(50);
		session.commit();
	}
	
	@Test
	/**
	 * 模糊查询
	 */
	public void testfindByname() {
		//方法一
		//使用代理对象调用findByname方法删除信息
		List<User> list = userdao.findByname("%王%");
		for (User user : list) {
			System.out.println(user);
		}
		/*//方法二
		List<User> list = userdao.findByname("王");
		for (User user : list) {
			System.out.println(user);
		}*/
		
	}
	
	@Test
	/**
	 * 查询总条数
	 */
	public void testcount() {
		//使用代理对象调用findTotal方法删除信息
		Integer count = userdao.findTotal();
		System.out.println(count);
	}
	
}

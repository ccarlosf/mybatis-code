package com.ccarlos.test;

import com.ccarlos.mapper.IOrderMapper;
import com.ccarlos.mapper.IUserMapper;
import com.ccarlos.pojo.Order;
import com.ccarlos.pojo.User;
import com.google.common.base.Splitter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class MybatisTest {

    // 一对一查询
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        List<Order> orderAndUser = mapper.findAll();
        for (Order order : orderAndUser) {
            System.out.println(order);
        }
    }

    // 一对多查询
    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    // 多对多查询
    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> allUserAndRole = mapper.findAllUserAndRole();
        for (User user : allUserAndRole) {
            System.out.println(user);
        }
    }

    private IUserMapper userMapper;
    private IOrderMapper orderMapper;


    @Before
    public void befor() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
        orderMapper = sqlSession.getMapper(IOrderMapper.class);
    }

   /* @Test
    public void addUser(){
        User user = new User();
        user.setId(3);
        user.setUsername("测试数据");

        userMapper.addUser(user);
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId(3);
        user.setUsername("修改了测试数据");

        userMapper.updateUser(user);

    }

    @Test
    public void selectUser(){
        List<User> users = userMapper.selectUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void deleteUser(){
        userMapper.deleteUser(3);
    }*/

    @Test
    public void oneToOne(){
        List<Order> orderAndUser = orderMapper.findOrderAndUser();
        for (Order order : orderAndUser) {
            System.out.println(order);
        }
    }

    @Test
    public void oneToMany(){
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void ManyToMany(){
        List<User> all = userMapper.findAllUserAndRole();
        for (User user : all) {
            System.out.println(user);
        }

    }

    @Test
    public void distinct(){
        String str = "DSJZ315001,DSJZ315002,DSJZ315003,DSJZ315022,DSJZ315023,DSJZ315006,DSJZ315011,DSJZ910007,DSJZ910008,DSJZ910009,DSJZ910010,KF05000410,KF05000410,KF05000410,KF05000420,KF05000420,KF05000420,KF05000430,KF05000430,KF05000430,KF05000230,KF05000230,KF05000230,KF01700230,KF01700230,KF01700230,KF01700230,KF01700240,KF01700240,KF01700240,KF01700240,KF01700250,KF01700250,KF01700250,KF01700250,DSJZ910014,DSJZ910014,DSJZ910014,DSJZ910014,DSJZ910014,DSJZ910014,DSJZ910001,DSJZ910002,DSJZ910003,DSJZ910004,DSJZ910005,DSJZ910006";
        List<String> list = Splitter.on(",").splitToList(str);
        list = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list.size());
    }


}




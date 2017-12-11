package com.test;

import com.dao.commonsdbutils.bean.WorkerInfo;
import com.dao.commonsdbutils.exception.DatabaseException;
import com.dao.commonsdbutils.impl.DBDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * CommonsDBUtils 测试工具类
 *
 * Created by zhangqingrong on 2016/5/8.
 */
public class CommonsDBUtilsTest {

    private DBDaoImpl dbDao;

    @Before
    public void init(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/springConfig.xml");
        dbDao = applicationContext.getBean(DBDaoImpl.class);
    }

    @Test
    public void testQueryInt(){
        String sql = "select salary from workers_info where workername='qingrong';";
        try {
            int salsry = dbDao.query(sql, Integer.TYPE);
            System.out.println(salsry);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryObject(){
        String sql = "select * from workers_info where workername='qingrong';";
        try {
            WorkerInfo workerInfo = dbDao.query(sql, WorkerInfo.class);
            System.out.println(workerInfo);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryObject2(){
        String sql = "select * from workers_info where workername= ? ;";
        try {
            WorkerInfo workerInfo = dbDao.query(sql, WorkerInfo.class,"qingrong");
            System.out.println(workerInfo);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryObject3(){
        String sql = "select * from workers_info where sex = ? and  workername= ? ;";
        try {
            WorkerInfo workerInfo = dbDao.query(sql, WorkerInfo.class,"M", "qingrong");
            System.out.println(workerInfo);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryList(){
        String sql = "select * from workers_info where sex='M';";
        try {
            List<WorkerInfo> workerInfos = dbDao.queryForList(sql,WorkerInfo.class);
            System.out.println(workerInfos);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}

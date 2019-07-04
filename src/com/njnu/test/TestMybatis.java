package com.njnu.test;

import com.njnu.bean.Category;
import com.njnu.mapper.CategoryMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    public static void main(String[] args) {
        try {
            String path = "mybatis-config.xml";
            InputStream ips= Resources.getResourceAsStream(path);
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(ips);
            SqlSession session=sessionFactory.openSession();
            //参数：namespace+id
//            List<Category> list=session.selectList("com.njnu.mapper.CategoryMapper.selectAll");
//            for (Category c:list) {
//                System.out.println(c.getCategory()+"\t"+c.getRemark());
//            }
//            Category category=session.selectOne("com.njnu.bean.CategoryMapper.selectById",2);
            CategoryMapper mapper=session.getMapper(CategoryMapper.class);

            List<Category> list=mapper.selectAll();
            for (Category c:list) {
                System.out.println(c.getCategory()+"\t"+c.getRemark());
            }

            Category category=mapper.selectById(1);
            System.out.println(category.getCategory()+"\t"+category.getRemark());

            int rows=0;
//            rows=mapper.deleteById(3);
//            System.out.println("（删除）受影响的行数："+rows);

            Category category1=new Category();
            category1.setCategory("玄幻");
            category1.setRemark("玄幻remark");
            rows=mapper.save(category1);
            System.out.println("（插入）受影响的行数："+rows);

            category1.setId(4);
            category1.setRemark("history");
            rows=mapper.update(category1);
            System.out.println("（修改）受影响的行数："+rows);
            //锁 事务，回滚
            //session.rollback();
            session.commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();;
        }
    }
}

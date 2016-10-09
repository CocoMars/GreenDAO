package com.itheima.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by lenovo on 2016/10/8.
 * GreenDAO操作数据库相关类的生成程序
 */
public class CustomDAOGenerater {

    public static void main(String[] args) {

        Schema schema = new Schema(1, "com.itheima.greendao.db");
        //创建数据库表,参数为表名
        Entity entity = schema.addEntity("User");
        //为表添加字段
        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addIntProperty("age");
        entity.addStringProperty("tel");

        //生成数据库相关类
        //第二个参数指定生成文件的本次存储路径,AndroidStudio工程指定到当前工程的java路径
        try {
            new DaoGenerator().generateAll(schema, "E:\\StudioWork\\GreenDAO\\app\\src\\main\\java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

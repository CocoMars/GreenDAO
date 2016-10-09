package com.itheima.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.itheima.greendao.db.DaoMaster;
import com.itheima.greendao.db.DaoSession;
import com.itheima.greendao.db.User;
import com.itheima.greendao.db.UserDao;

import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

public class MainActivity extends AppCompatActivity {

    private UserDao mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"student.db", null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        mDao = daoSession.getUserDao();
    }

    public void add(View v){
        User user1 = new User(null, "zhangsan", 12, "13112345678");
        User user2 = new User(null, "lisi", 22, "13222345678");
        User user3 = new User(null, "wangwu", 32, "13332345678");
        User user4 = new User(null, "zhaoqi", 42, "13442345678");

        mDao.insert(user1);
        mDao.insert(user2);
        mDao.insert(user3);
        mDao.insert(user4);
    }

    public void del(View v){
        //删除数据的id
        mDao.deleteByKey(2L);
    }

    public void update(View v){
        User user = new User(4L, "赵琦", 42 ,"13442345678");
        mDao.update(user);
    }

    public void query(View v){
        QueryBuilder<User> userQueryBuilder = mDao.queryBuilder();
        QueryBuilder<User> builder = userQueryBuilder.where(UserDao.Properties.Age.eq("32"));
        Query<User> build = builder.build();

        //查询数据
        List<User> list = build.list();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            Log.e("MainActivity: ",user.getName());
        }
    }
}

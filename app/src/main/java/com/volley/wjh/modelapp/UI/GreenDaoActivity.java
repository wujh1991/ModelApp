package com.volley.wjh.modelapp.UI;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.volley.wjh.bean.Note;
import com.volley.wjh.bean.User;
import com.volley.wjh.dao.NoteDao;
import com.volley.wjh.dao.UserDao;
import com.volley.wjh.modelapp.App.MyApplication;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.R;
import com.volley.wjh.modelapp.Utils.Date.DateUtil;

import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;


/**
 * Created by wujh on 2016/3/29.
 * Email:1049334820@qq.com
 */
public class GreenDaoActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = GreenDaoActivity.class.getSimpleName();
    private Button btnInsert;
    private Button btnDelete;
    private Button btnUpdate;
    private Button btnQuery;
    private TextView tvQueryContent;

    private User mUser;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_green_dao;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnQuery = (Button) findViewById(R.id.btnQuery);

        tvQueryContent = (TextView) findViewById(R.id.tvQueryContent);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsert:
                insert();
                Toast.makeText(this, "insert success", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnDelete:
                delete();
                Toast.makeText(this, "delete success", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnUpdate:
                update();
                Toast.makeText(this, "update success", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnQuery:
                query();
                Toast.makeText(this, "query success", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void query() {
        Query query = getUserDao().queryBuilder()
//                .where(UserDao.Properties.Age.eq(20))
                .build();
        List<User> list = query.list();

        //
        Query query1 = getNoteDao().queryBuilder().build();
        List<Note> list1 = query1.list();

        if(list != null && list.size() != 0){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < list.size();i++){
                sb.append(list.get(i).getId() + "\n"
                        + list.get(i).getName() + "\n"
                        + list.get(i).getAge() + "\n"
//                        + list.get(i).getBirthday() + "\n"
                        +list.get(i).getSex() + "\n"
                );
            }
            if(list1 != null && list1.size() != 0){
                sb.append("Note:" + list1.get(0).getId() + "\n"
                +list1.get(0).getText() + "\n"
                + list1.get(0).getComment() + "\n"
                + list1.get(0).getDate()+ "\n");
            }
            tvQueryContent.setText(sb.toString());

            Log.i(TAG, "total" + list.size() + "\n"
                    + sb.toString());
        }else{
            tvQueryContent.setText("没有数据");
        }
    }

    private void update() {
        if(mUser != null){
            mUser.setAge(100);
//            mUser.setBirthday(new Date());
            getUserDao().update(mUser);
        }
    }

    private void delete() {
        getUserDao().deleteAll();
    }

    private void insert() {
        User user = new User(null, "伍森", 25, null);//,new Date(1998, 01, 14)
        getUserDao().insert(user);

        mUser = user;

        User user1 = new User(null, "a秦", 29,null);//, new Date()
        getUserDao().insert(user1);

        //
        Note note = new Note(null,"note","note test",null);
        getNoteDao().insert(note);
    }

    private UserDao getUserDao() {
        return MyApplication.getDaoSession().getUserDao();
    }

    private NoteDao getNoteDao(){
        return MyApplication.getDaoSession().getNoteDao();
    }


}

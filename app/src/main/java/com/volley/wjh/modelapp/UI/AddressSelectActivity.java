package com.volley.wjh.modelapp.UI;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.volley.wjh.modelapp.Adapter.AddressAdapter;
import com.volley.wjh.modelapp.Base.Activity.BaseActivity;
import com.volley.wjh.modelapp.DataModel.Address;
import com.volley.wjh.modelapp.Db.DbHelper;
import com.volley.wjh.modelapp.R;

import java.util.ArrayList;

/**
 * Created by wujh on 2016/3/9.
 * Email:1049334820@qq.com
 */
public class AddressSelectActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout llProvince;
    private ListView lvProvince;
    private AddressAdapter  mProvinceAdapter;
    private ArrayList<Address> mProvinceList;

    private LinearLayout llCity;
    private ListView lvCity;
    private AddressAdapter mCityAdapter;
    private ArrayList<Address> mCityList;
    private TextView tvCityProvince;

    private LinearLayout llArea;
    private ListView lvArea;
    private AddressAdapter mAreaAdapter;
    private ArrayList<Address> mAreaList;
    private TextView tvAreaProvince;
    private TextView tvAreaCity;

    private Address mCityProvince;
    private Address mAreaProvince;
    private Address mAreaCity;

    @Override
    public int getResourceLayout() {
        return R.layout.activity_address_select;
    }

    @Override
    public void getExtra() {

    }

    @Override
    public void findViews() {
        llProvince = (LinearLayout) findViewById(R.id.llProvince);
        llCity = (LinearLayout) findViewById(R.id.llCity);
        llArea = (LinearLayout) findViewById(R.id.llArea);

        lvProvince = (ListView) findViewById(R.id.lvProvince);
        lvCity = (ListView) findViewById(R.id.lvCity);
        lvArea = (ListView) findViewById(R.id.lvArea);

        tvCityProvince = (TextView) findViewById(R.id.tvCityProvince);

        tvAreaProvince = (TextView) findViewById(R.id.tvAreaProvince);
        tvAreaCity = (TextView) findViewById(R.id.tvAreaCity);
    }

    @Override
    public void init() {
        initProvince();
    }

    @Override
    public void setListener() {
        setProvinceItemClickListener();
        setCityItemClickListener();
        setAreaItemClickListener();

        tvCityProvince.setOnClickListener(this);

        tvAreaProvince.setOnClickListener(this);
        tvAreaCity.setOnClickListener(this);
    }

    public void initProvince(){
        mProvinceList = DbHelper.getInstance(this).queryAddress("province",null,null,true);
        mProvinceAdapter = new AddressAdapter(this,mProvinceList);
        lvProvince.setAdapter(mProvinceAdapter);
    }

    public void initCity(){
        mCityList = DbHelper.getInstance(this).queryAddress("city","pid=?",new String[]{mCityProvince.getId()},false);
        mCityAdapter = new AddressAdapter(this,mCityList);
        lvCity.setAdapter(mCityAdapter);

        tvCityProvince.setText(mCityProvince.getName());
    }

    public void initArea(){
        mAreaAdapter = new AddressAdapter(this,mAreaList);
        lvArea.setAdapter(mAreaAdapter);

        tvAreaProvince.setText(mCityProvince.getName());
        tvAreaCity.setText(mAreaCity.getName());
    }

    public void setProvinceItemClickListener(){
        lvProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCityProvince = mProvinceList.get(position);
                showAndHideView(1);
                initCity();
            }
        });
    }

    public void setCityItemClickListener(){
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAreaCity = mCityList.get(position);
                showAndHideView(2);
                mAreaList = DbHelper.getInstance(AddressSelectActivity.this).queryAddress("area","pid=?",new String[]{mAreaCity.getId()},false);
                if(mAreaList.size() != 0){
                    initArea();
                }else{
                    Toast.makeText(AddressSelectActivity.this,"只有两级" + mAreaCity.getName(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void setAreaItemClickListener(){
        lvArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddressSelectActivity.this,"只有三级" + mAreaList.get(position).getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showAndHideView(int tag){
        switch (tag){
            case 0:
                llCity.setVisibility(View.GONE);
                llArea.setVisibility(View.GONE);
                llProvince.setVisibility(View.VISIBLE);
                break;
            case 1:
                llProvince.setVisibility(View.GONE);
                llArea.setVisibility(View.GONE);
                llCity.setVisibility(View.VISIBLE);
                break;
            case 2:
                llProvince.setVisibility(View.GONE);
                llCity.setVisibility(View.GONE);
                llArea.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCityProvince:
                showAndHideView(0);
                break;
            case R.id.tvAreaProvince:
                showAndHideView(0);
                break;
            case R.id.tvAreaCity:
                showAndHideView(1);
                break;
        }
    }
}

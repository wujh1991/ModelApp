package com.volley.wjh.modelapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.volley.wjh.modelapp.DataModel.Address;
import com.volley.wjh.modelapp.R;

import java.util.List;

/**
 * Created by wujh on 2016/3/9.
 * Email:1049334820@qq.com
 */
public class AddressAdapter  extends BaseAdapter{
    private List<Address> mData;
    private Context mContext;

    public AddressAdapter(Context context,List<Address> mData){
        this.mData = mData;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_address_province,null);
            viewHolder = new ViewHolder();
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tvProvinceContent);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tvContent.setText(mData.get(position).getName());
        return convertView;

    }

    class ViewHolder{
        public TextView tvContent;
    }
}

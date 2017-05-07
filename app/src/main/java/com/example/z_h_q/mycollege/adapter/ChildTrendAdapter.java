package com.example.z_h_q.mycollege.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.bean.ChildTrendListBean;
import com.example.z_h_q.mycollege.bean.NameListBean;
import com.example.z_h_q.mycollege.bean.TrendListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Z-H-Q on 2017/5/4.
 */
public class ChildTrendAdapter extends BaseAdapter {
    private List<ChildTrendListBean.ChildtrendsBean> childtrendsBeanList;
    private String user_name;
    Context mContext;
    public ChildTrendAdapter(List<ChildTrendListBean.ChildtrendsBean> childtrendsBeanList){
        this.childtrendsBeanList = childtrendsBeanList;
    }


    private ArrayList<HashMap<String, Object>> getChildDate(){
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
        //为动态数组添加数据
        for(int i=0;i<5;i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tvCommentName", "低调、平凡");
            map.put("tvCommentContent", "dsdsds");
            listItem.add(map);
        }
        return listItem;
    }
    @Override
    public int getCount() {
        return childtrendsBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return childtrendsBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parentp) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext,R.layout.comment_item,null);
            holder = new ViewHolder();
            //得到各个控件的对象
            holder.tvCommentName = (TextView) convertView.findViewById(R.id.tv_comment_name);
            holder.tvCommentContent = (TextView) convertView.findViewById(R.id.tv_comment_content);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        holder.tvCommentName.setText("wodeguo");
        holder.tvCommentContent.setText("hoaf");
        return convertView;
    }
    class ViewHolder{
        public TextView tvCommentName;
        public TextView tvCommentContent;
    }
    /*@Override
    public int getCount() {
        return getChildDate().size();
    }

    @Override
    public Object getItem(int position) {
        return getChildDate().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
            holder.tvCommentName = (TextView) convertView.findViewById(R.id.tv_comment_name);
            holder.tvCommentContent = (TextView) convertView.findViewById(R.id.tv_comment_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ///holder.tvTrendsItemName.setText(trendsBeanList.get(position).get);
        //holder.ivTrendsItemIcon.setImageResource(R.drawable.icon);
        holder.tvCommentName.setText("666");
        holder.tvCommentContent.setText("777");
        *//*String user_id = trendsBeanList.get(position).getUser_id();
        System.out.println(user_id);
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id", user_id);

        utils.send(HttpRequest.HttpMethod.GET, GlobalConstants.SERVER_URL+"get_name.php", params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                Gson gson = new Gson();
                nameListBean = gson.fromJson(result.replace("\uFEFF", ""), NameListBean.class);
                user_name = nameListBean.getUser().get(0).getUser_name();
                user_icon = nameListBean.getUser().get(0).getUser_icon();
                System.out.println(user_icon+"uuuuuuuuuuuuuuuuuuu");
                holder.tvTrendsItemName.setText(user_name);
                mBitmapUtils.display(holder.ivTrendsItemIcon, GlobalConstants.SERVER_IMG_URL+ user_icon);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");

            }
        });*//*

        return convertView;
    }

    *//*public String getUser_name(String mUser_id){

        return user_name;
    }*//*



    private class ViewHolder {
        public TextView tvCommentName;
        public TextView tvCommentContent;

    }*/
}

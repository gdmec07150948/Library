package com.example.z_h_q.mycollege.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.MainActivity;
import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.bean.ChildTrendListBean;
import com.example.z_h_q.mycollege.bean.NameListBean;
import com.example.z_h_q.mycollege.bean.NoticesListBean;
import com.example.z_h_q.mycollege.bean.TrendListBean;
import com.example.z_h_q.mycollege.bean.UserListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
 * Created by Z-H-Q on 2017/4/30.
 */
public class TrendAdapter extends BaseAdapter {
    private BitmapUtils mBitmapUtils;
    private List<TrendListBean.TrendsBean> trendsBeanList;
    private List<ChildTrendListBean.ChildtrendsBean> childtrendsBeanList;
    private NameListBean nameListBean;
    private String user_name;
    private String user_icon;
    private Context mContext;
    Gson gson;
    ChildTrendAdapter childTrendAdapter = new ChildTrendAdapter(childtrendsBeanList);
    private String trend_id;
    private ChildTrendListBean childTrendListBean;
    private ViewHolder holder;


    //String mUrl = GlobalConstants.SERVER_URL+"get_all_childtrends.php";
    public TrendAdapter(List<TrendListBean.TrendsBean> trendsBeanList,Context context){
        this.trendsBeanList = trendsBeanList;
        this.mContext = context;
        mBitmapUtils = new BitmapUtils(mContext);
        initData();
        gson = new Gson();
        childtrendsBeanList = new ArrayList<>();

    }

    /*private void initData() {
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("trend_id",trendsBeanList.get(0).getTrend_id());
        System.out.println(trendsBeanList.get(0).getTrend_id()+"555555555");
        utils.send(HttpRequest.HttpMethod.GET, mUrl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                ChildTrendListBean childTrendListBean = gson.fromJson(result.replace("\uFEFF", ""), new TypeToken<ChildTrendListBean>() {
                }.getType());
                childtrendsBeanList.clear();
                childtrendsBeanList.addAll(childTrendListBean.getChildtrends());
                *//*grunOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        childTrendAdapter.notifyDataSetChanged();//在主线程中刷新listView

                    }
                });*//*
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");
                //Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

            }
        });
    }*/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public int getCount() {
        return trendsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return trendsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trends_item, parent, false);
            holder = new ViewHolder();
            holder.ivTrendsItemIcon = (ImageView) convertView.findViewById(R.id.iv_trends_item_icon);
            holder.tvTrendsItemName = (TextView) convertView.findViewById(R.id.tv_trends_item_name);
            holder.tvTrendsItemContent = (TextView) convertView.findViewById(R.id.tv_trends_item_content);
            holder.tvTrendsItemData = (TextView) convertView.findViewById(R.id.tv_trends_item_data);
            holder.lvComment = (ListView) convertView.findViewById(R.id.lv_comment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTrendsItemContent.setText(trendsBeanList.get(position).getTrend_content());
        holder.tvTrendsItemData.setText(trendsBeanList.get(position).getTrend_date());



        trend_id = trendsBeanList.get(position).getTrend_id();
        System.out.println(trend_id);

        String user_id = trendsBeanList.get(position).getUser_id();
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
                holder.tvTrendsItemName.setText(user_name);
                mBitmapUtils.display(holder.ivTrendsItemIcon, GlobalConstants.SERVER_IMG_URL+ user_icon);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");

            }
        });

        return convertView;
    }




    private class ViewHolder {
        public ImageView ivTrendsItemIcon;
        public TextView tvTrendsItemName;
        public TextView tvTrendsItemContent;
        public TextView tvTrendsItemData;
        public ListView lvComment;

    }

    public void initData(){
        System.out.println("竟来了");
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("trend_id", "15");
        utils.send(HttpRequest.HttpMethod.GET, GlobalConstants.SERVER_URL+"get_all_childtrends.php", params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("iiii");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                childTrendListBean = gson.fromJson(result.replace("\uFEFF", ""), new TypeToken<ChildTrendListBean>() {
                }.getType());
                if (childTrendListBean.getSuccess()==1){
                    System.out.println("qqqqqqqqqqqqqqqqqq");
                    System.out.println("评论内容："+childTrendListBean.getChildtrends().get(0).getChildtrend_content());
                    childtrendsBeanList.clear();
                    childtrendsBeanList.addAll(childTrendListBean.getChildtrends());
                   /* holder.lvComment.setAdapter(childTrendAdapter);
                    holder.lvComment.setDividerHeight(0);
                    setListViewHeightBasedOnChildren(holder.lvComment);*/
                /*runOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        childTrendAdapter.notifyDataSetChanged();//在主线程中刷新listView
                    }
                });*/
                }else{
                    System.out.println("wwwwwwwwwwwwwwwwwwwww");
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");

            }
        });
    }

    /*private ArrayList<HashMap<String, Object>> getChildDate(){
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
        //为动态数组添加数据
        for(int i=0;i<10;i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tvCommentItemName", "低调、平凡");
            map.put("tvCommentItemContent", "dsdsdsdsdsds");
            listItem.add(map);
        }
        return listItem;
    }
    private class ChildAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return childTrendListBean.getChildtrends().size();
        }

        @Override
        public Object getItem(int i) {
            return childTrendListBean.getChildtrends().get(i);
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
                holder.tvCommentItemName = (TextView) convertView.findViewById(R.id.tv_comment_name);
                holder.tvCommentItemContent = (TextView) convertView.findViewById(R.id.tv_comment_content);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
            if (childTrendListBean.getSuccess()==1){
                System.out.println("评论内容"+ childTrendListBean.getChildtrends().get(position).getChildtrend_content());
                holder.tvCommentItemName.setText("我的锅");
                holder.tvCommentItemContent.setText(childTrendListBean.getChildtrends().get(position).getChildtrend_content());
            }else{
                System.out.println("gggggggggggggggggggggggggggggg");
            }

            return convertView;
        }
        class ViewHolder{
            public TextView tvCommentItemName;
            public TextView tvCommentItemContent;
        }
    }*/

}

package com.example.z_h_q.mycollege.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.bean.BookListBean;
import com.example.z_h_q.mycollege.bean.CommentListBean;
import com.example.z_h_q.mycollege.bean.NameListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/5/7.
 */
public class CommentAdapter extends BaseAdapter {
    private BitmapUtils mBitmapUtils;
    private NameListBean nameListBean;
    private String user_name;
    private String user_icon;
    List<CommentListBean.CommentsBean> commentsBeanList;
    Context mContext;
    private ViewHolder holder;
    private String user_id;

    public CommentAdapter(List<CommentListBean.CommentsBean> commentsBeanList,Context context){
        this.commentsBeanList = commentsBeanList;
        this.mContext = context;
        mBitmapUtils = new BitmapUtils(mContext);
    }
    @Override
    public int getCount() {
        return commentsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_detail_comment_item, parent, false);
            //得到各个控件的对象
            holder.ivTrendsItemIcon = (ImageView) convertView.findViewById(R.id.iv_trends_item_icon);
            holder.tvTrendsItemName = (TextView) convertView.findViewById(R.id.tv_trends_item_name);
            holder.tvTrendsItemContent = (TextView) convertView.findViewById(R.id.tv_trends_item_content);
            holder.tvTrendsItemData = (TextView) convertView.findViewById(R.id.tv_trends_item_data);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTrendsItemContent.setText(commentsBeanList.get(position).getComment_content());
        holder.tvTrendsItemData.setText(commentsBeanList.get(position).getComment_date());
        user_id = commentsBeanList.get(position).getUser_id();
        System.out.println(user_id +"-------------------------------");
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
                System.out.println(user_name+"========="+user_icon);
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
    }
}

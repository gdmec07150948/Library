package com.example.z_h_q.mycollege.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.bean.ProductsListBean;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;



/**
 * Created by Z-H-Q on 2017/4/22.
 */
public class MyAdapter extends BaseAdapter {
    private List<ProductsListBean.ProductsBean> productsBeenList;
    public MyAdapter(List<ProductsListBean.ProductsBean> productsBeenList) {
        this.productsBeenList = productsBeenList;

    }
    @Override
    public int getCount() {
        return productsBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return productsBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_list_item, parent, false);
            holder.tvNoticeDetailTitle = (TextView) convertView.findViewById(R.id.tv_notice_detail_title);
            holder.tvNoticeDetailContent = (TextView) convertView.findViewById(R.id.tv_notice_detail_content);
            holder.tvNoticeDetailDate = (TextView) convertView.findViewById(R.id.tv_notice_detail_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /*holder.tvNoticeDetailTitle.setText(productsBeenList.get(position).getPrice());
        holder.tvNoticeDetailContent.setText(productsBeenList.get(position).getName());
        holder.tvNoticeDetailDate.setText(productsBeenList.get(position).getDescription());*/
        return convertView;
    }
    private class ViewHolder {
        public TextView tvNoticeDetailTitle;
        public TextView tvNoticeDetailContent;
        public TextView tvNoticeDetailDate;

    }
}

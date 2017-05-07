package com.example.z_h_q.mycollege.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.bean.NoticesListBean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/24.
 */
public class NoticeAdapter extends BaseAdapter{
    private List<NoticesListBean.NoticesBean> noticesBeanList;
    public NoticeAdapter(List<NoticesListBean.NoticesBean> noticesBeanList){
        this.noticesBeanList = noticesBeanList;
    }
    @Override
    public int getCount() {
        return noticesBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticesBeanList.get(position);
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
        holder.tvNoticeDetailTitle.setText(noticesBeanList.get(position).getNotice_title());
        holder.tvNoticeDetailContent.setText(noticesBeanList.get(position).getNotice_content());
        holder.tvNoticeDetailDate.setText(noticesBeanList.get(position).getNotice_date());
        return convertView;
    }
    private class ViewHolder {
        public TextView tvNoticeDetailTitle;
        public TextView tvNoticeDetailContent;
        public TextView tvNoticeDetailDate;

    }
}

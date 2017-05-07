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
import com.example.z_h_q.mycollege.bean.NoticesListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/5/3.
 */
public class BookAdapter extends BaseAdapter {
    private List<BookListBean.BookBean> bookBeanList;
    private Context mContext;
    private BitmapUtils mBitmapUtils;
    public BookAdapter(List<BookListBean.BookBean> bookBeanList,Context context){
        this.bookBeanList = bookBeanList;
        this.mContext = context;
        mBitmapUtils = new BitmapUtils(mContext);
    }
    @Override
    public int getCount() {
        return bookBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookBeanList.get(position);
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
            //得到各个控件的对象
            holder.ivBookPic = (ImageView) convertView.findViewById(R.id.iv_book_pic);
            holder.tvBookName = (TextView) convertView.findViewById(R.id.tv_book_name);
            holder.tvBookContact = (TextView) convertView.findViewById(R.id.tv_book_contact);
            holder.tvBookAuther = (TextView) convertView.findViewById(R.id.tv_book_author);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        mBitmapUtils.display(holder.ivBookPic, GlobalConstants.SERVER_IMG_URL+bookBeanList.get(position).book_img);
        holder.tvBookName.setText(bookBeanList.get(position).book_name);
        holder.tvBookContact.setText(bookBeanList.get(position).book_about);
        holder.tvBookAuther.setText(bookBeanList.get(position).book_author);
        return convertView;
    }
    private class ViewHolder {
        public ImageView ivBookPic;
        public TextView tvBookName;
        public TextView tvBookContact;
        public TextView tvBookAuther;
    }
}

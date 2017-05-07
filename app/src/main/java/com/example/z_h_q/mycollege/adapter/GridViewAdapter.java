package com.example.z_h_q.mycollege.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.bean.BookCategoryBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/24.
 */
public class GridViewAdapter extends BaseAdapter {
    private BitmapUtils mBitmapUtils;
    private Context mContext;
    private List<BookCategoryBean.CategorysBean> categorysBeanList;
    public GridViewAdapter(List<BookCategoryBean.CategorysBean> categorysBeen,Context context){
        this.categorysBeanList = categorysBeen;
        this.mContext = context;
        mBitmapUtils = new BitmapUtils(mContext);
    }
    @Override
    public int getCount() {
        return categorysBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return categorysBeanList.get(position);
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_category_item, parent, false);
            holder.ivBookCategoryIcon = (ImageView) convertView.findViewById(R.id.iv_book_category_icon);
            holder.tvBookCategoryName = (TextView) convertView.findViewById(R.id.tv_book_category_name);
            holder.tvBookCategoryNumber = (TextView) convertView.findViewById(R.id.tv_book_category_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivBookCategoryIcon.setImageResource(R.drawable.book);
        holder.tvBookCategoryName.setText(categorysBeanList.get(position).getCategory_name());
        holder.tvBookCategoryNumber.setText("500");
        mBitmapUtils.display(holder.ivBookCategoryIcon, GlobalConstants.SERVER_IMG_URL+categorysBeanList.get(position).getCategory_img());
        //holder.ivImage.setImageResource(R.drawable.book);
        return convertView;
    }
    private class ViewHolder {
        public ImageView ivBookCategoryIcon;
        public TextView tvBookCategoryName;
        public TextView tvBookCategoryNumber;

    }
}

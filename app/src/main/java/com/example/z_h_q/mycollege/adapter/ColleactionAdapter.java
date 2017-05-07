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
import com.example.z_h_q.mycollege.bean.CollectionListBean;
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
public class ColleactionAdapter extends BaseAdapter {
    List<CollectionListBean.CollectionsBean> collectionsBeanList;
    private Context mContext;
    private BitmapUtils mBitmapUtils;
    private BookListBean bookListBean;
    private ViewHolder holder;

    public ColleactionAdapter(List<CollectionListBean.CollectionsBean> collectionsBeanList, Context context){
        this.collectionsBeanList = collectionsBeanList;
        this.mContext = context;
        mBitmapUtils = new BitmapUtils(mContext);
    }
    @Override
    public int getCount() {
        return collectionsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return collectionsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        String book_id =  collectionsBeanList.get(position).getBook_id();
        System.out.println(book_id);


        HttpUtils utils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("book_id", book_id);
        utils.send(HttpRequest.HttpMethod.GET, GlobalConstants.SERVER_URL+"get_book_detail.php", params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                Gson gson = new Gson();
                bookListBean = gson.fromJson(result.replace("\uFEFF", ""), BookListBean.class);
                String book_img = bookListBean.getBook().get(0).getBook_img();
                String book_name = bookListBean.getBook().get(0).getBook_name();
                String book_author = bookListBean.getBook().get(0).getBook_author();
                String book_category = bookListBean.getBook().get(0).getBook_category();
                String book_about = bookListBean.getBook().get(0).getBook_about();
                String book_house = bookListBean.getBook().get(0).getBook_house();
                holder.tvBookName.setText(book_name);
                holder.tvBookContact.setText(book_about);
                holder.tvBookAuther.setText(book_author);
                mBitmapUtils.display(holder.ivBookPic,GlobalConstants.SERVER_IMG_URL+book_img);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");

            }
        });

        return convertView;
    }
    private class ViewHolder {
        public ImageView ivBookPic;
        public TextView tvBookName;
        public TextView tvBookContact;
        public TextView tvBookAuther;
    }
}

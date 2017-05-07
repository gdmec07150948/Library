package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.MainActivity;
import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.adapter.BookAdapter;
import com.example.z_h_q.mycollege.bean.BookListBean;
import com.example.z_h_q.mycollege.bean.NoticesListBean;
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
 * Created by Z-H-Q on 2017/4/9.
 */
public class BookListActivity extends Activity {
    ListView lvBookList;
    LinearLayout llBack;
    private List<BookListBean.BookBean> bookBeanList;
    BookAdapter bookAdapter;
    Gson gson;
    String mUrl = GlobalConstants.SERVER_URL+"get_book_list.php";
    private BookListBean bookListBean;
    private String book_category;
    private Intent intent;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        TextView title = (TextView) findViewById(R.id.title);
        book_category = getIntent().getStringExtra("category_name");
        user_id = getIntent().getStringExtra("user_id");
        title.setText(book_category);
        initDate();
        gson = new Gson();
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setVisibility(View.VISIBLE);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bookBeanList = new ArrayList<>();
        bookAdapter = new BookAdapter(bookBeanList,getApplicationContext());
        lvBookList = (ListView)findViewById(R.id.lv_book_list);
        lvBookList.setDivider(new ColorDrawable(Color.GRAY));
        lvBookList.setDividerHeight(1);
        lvBookList.setAdapter(bookAdapter);
        lvBookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(getApplicationContext(),BookDetailActivity.class);
                String book_id = bookListBean.book.get(i).book_id;
                String book_name = bookListBean.book.get(i).book_name;
                String book_img = bookListBean.book.get(i).book_img;
                String book_author = bookListBean.book.get(i).book_author;
                String book_about = bookListBean.book.get(i).book_about;
                String book_category = bookListBean.book.get(i).book_category;
                String book_house = bookListBean.book.get(i).book_house;
                System.out.println(bookListBean.book.get(i).book_name);
                intent.putExtra("user_id",user_id);
                intent.putExtra("book_id",book_id);
                intent.putExtra("book_name",book_name);
                intent.putExtra("book_img",book_img);
                intent.putExtra("book_author",book_author);
                intent.putExtra("book_about",book_about);
                intent.putExtra("book_category",book_category);
                intent.putExtra("book_house",book_house);
                startActivity(intent);
            }
        });

    }

    public void initDate(){
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("book_category",book_category);
        System.out.println(book_category+"555555555");
        utils.send(HttpRequest.HttpMethod.GET, mUrl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                bookListBean = gson.fromJson(result.replace("\uFEFF", ""), new TypeToken<BookListBean>() {
                }.getType());
                bookBeanList.clear();
                bookBeanList.addAll(bookListBean.getBook());
                runOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        bookAdapter.notifyDataSetChanged();//在主线程中刷新listView

                    }
                });
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

            }
        });
    }


}

package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.adapter.MyAdapter;
import com.example.z_h_q.mycollege.adapter.NoticeAdapter;
import com.example.z_h_q.mycollege.bean.NoticesListBean;
import com.example.z_h_q.mycollege.bean.ProductsListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.example.z_h_q.mycollege.view.PullToRefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/9.
 */
public class NoticeListActivity extends Activity {
    PullToRefreshListView lvNoticeList;
    LinearLayout llBack;
    private NoticeAdapter noticeAdapter;
    List<NoticesListBean.NoticesBean> noticesBeanList;
    String mUrl = GlobalConstants.SERVER_URL+"get_all_notice.php";
    //List<ProductsListBean.ProductsBean> productsBeanList;
    //private MyAdapter myAdapter;
    //String mUrl = "http://10.0.2.2/android_connect/get_all_products.php";
    Gson gson;
    private Intent intent;
    private NoticesListBean noticeListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("图书馆公告");
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setVisibility(View.VISIBLE);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
        noticesBeanList = new ArrayList<>();
        //productsBeanList = new ArrayList<>();
        gson = new Gson();
        intent = new Intent(getApplication(),NoticeDetailActivity.class);
        noticeAdapter = new NoticeAdapter(noticesBeanList);
        //myAdapter = new MyAdapter(productsBeanList);
        lvNoticeList = (PullToRefreshListView) findViewById(R.id.lv_notice_list);
        lvNoticeList.setRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("下拉刷新前");
                initData();
                System.out.println("下拉刷新后");
            }
            @Override
            public void onLoadMore() {
                System.out.println("上拉加载前");
                initData();
                System.out.println("上拉加载后");

            }
        });
        lvNoticeList.setAdapter(noticeAdapter);
        lvNoticeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String notice_title = noticeListBean.getNotices().get(i-1).getNotice_title();
                String notice_content = noticeListBean.getNotices().get(i-1).getNotice_content();
                String notice_date = noticeListBean.getNotices().get(i-1).getNotice_date();
                intent.putExtra("notice_title",notice_title);
                intent.putExtra("notice_content",notice_content);
                intent.putExtra("notice_date",notice_date);
                startActivity(intent);
            }
        });
    }
    public void initData(){
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                noticeListBean = gson.fromJson(result.replace("\uFEFF",""), new TypeToken<NoticesListBean>() {}.getType());
                //先清空原有的list
                noticesBeanList.clear();
                noticesBeanList.addAll(noticeListBean.getNotices());//再将获取到的数据完全add进productsBeanList.
                runOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        noticeAdapter.notifyDataSetChanged();//在主线程中刷新listView
                        lvNoticeList.onRefreshComplete();

                    }
                });
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffffffffffffffffff");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }
        });

    }
}

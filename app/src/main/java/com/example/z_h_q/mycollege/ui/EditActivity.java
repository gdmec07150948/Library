package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.bean.TrendListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Z-H-Q on 2017/4/9.
 */
public class EditActivity extends Activity {

    private String user_id;
    private String book_id;
    private String mUrl = GlobalConstants.SERVER_URL+"create_comment.php";
    private EditText etCommentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("写评论");
        LinearLayout llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etCommentContent = (EditText) findViewById(R.id.et_comment_content);
        user_id = getIntent().getStringExtra("user_id");
        book_id = getIntent().getStringExtra("book_id");
        TextView tvSend = (TextView) findViewById(R.id.tv_send);
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });
    }
    public void initData(){
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id", user_id);
        params.addQueryStringParameter("book_id", book_id);
        params.addQueryStringParameter("comment_content", etCommentContent.getText().toString().trim());

        utils.send(HttpRequest.HttpMethod.GET, mUrl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                finish();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

            }
        });
    }
}

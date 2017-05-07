package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.MainActivity;
import com.example.z_h_q.mycollege.R;
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

/**
 * Created by Z-H-Q on 2017/5/2.
 */
public class SendTreadsActivity extends Activity {
    LinearLayout llBack;
    LinearLayout llSend;
    EditText etContent;
    private String trendContent;
    String mUrl = GlobalConstants.SERVER_URL+"create_trend.php";
    Gson gson;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_trends);
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llSend = (LinearLayout) findViewById(R.id.ll_send);
        etContent = (EditText) findViewById(R.id.et_content);
        gson = new Gson();
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        llSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });

    }

    private void send() {
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id", getIntent().getStringExtra("user_id"));
        params.addQueryStringParameter("trend_content", etContent.getText().toString());

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

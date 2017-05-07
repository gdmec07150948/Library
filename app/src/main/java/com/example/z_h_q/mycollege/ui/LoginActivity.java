package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.z_h_q.mycollege.MainActivity;
import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.bean.NoticesListBean;
import com.example.z_h_q.mycollege.bean.UserListBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/28.
 */
public class LoginActivity extends Activity {
    EditText etTel;
    EditText etPwd;
    Button btn_login;
    private String tel;
    private String pwd;
    String mUrl = "http://10.0.2.2/library/get_user_detail.php";
    Gson gson;
    private int success;
    private UserListBean userListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gson = new Gson();
        etTel = (EditText) findViewById(R.id.et_tel);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        //tel = etTel.getText().toString().trim();
        //pwd = etPwd.getText().toString().trim();
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etTel.getText().toString())){
                    Toast.makeText(getApplication(),"学号不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(etPwd.getText().toString())){
                    Toast.makeText(getApplication(),"密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(),"登录中",Toast.LENGTH_SHORT).show();
                    login(etTel.getText().toString(),etPwd.getText().toString());



                }
            }
        });
    }
    public void login(String mTel,String mPwd){
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id", mTel);
        params.addQueryStringParameter("user_password", mPwd);
        utils.send(HttpRequest.HttpMethod.GET, mUrl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                userListBean = gson.fromJson(result.replace("\uFEFF", ""), new TypeToken<UserListBean>() {
                }.getType());
                success = userListBean.getSuccess();
                System.out.println(success+"========================");
                if (success ==1){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("user_id", userListBean.getUser().get(0).getUser_id());
                    intent.putExtra("user_name", userListBean.getUser().get(0).getUser_name());
                    intent.putExtra("user_icon",userListBean.getUser().get(0).getUser_icon());
                    intent.putExtra("user_trueName", userListBean.getUser().get(0).getUser_trueName());
                    intent.putExtra("user_email", userListBean.getUser().get(0).getUser_email());
                    intent.putExtra("user_tel", userListBean.getUser().get(0).getUser_tel());
                    intent.putExtra("user_sex", userListBean.getUser().get(0).getUser_sex());
                    startActivity(intent);
                }else if (success ==0){
                    Toast.makeText(getApplication(),"密码错误",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

            }
        });
    }


}

package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by Z-H-Q on 2017/4/16.
 */
public class InformationActivity extends Activity {
    LinearLayout llBack;
    private BitmapUtils mBitmapUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("个人信息");
        mBitmapUtils = new BitmapUtils(this);
        String user_id = getIntent().getStringExtra("user_id");
        String user_icon = getIntent().getStringExtra("user_icon");
        String user_name = getIntent().getStringExtra("user_name");
        String user_trueName = getIntent().getStringExtra("user_trueName");
        String user_email = getIntent().getStringExtra("user_email");
        String user_tel = getIntent().getStringExtra("user_tel");
        String user_sex = getIntent().getStringExtra("user_sex");
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setVisibility(View.VISIBLE);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageView ivIcon = (ImageView) findViewById(R.id.iv_icon);
        mBitmapUtils.display(ivIcon, GlobalConstants.SERVER_IMG_URL+user_icon);
        TextView tvName = (TextView) findViewById(R.id.tv_name);
        TextView tvTel = (TextView) findViewById(R.id.tv_tel);
        TextView tvEmail = (TextView) findViewById(R.id.tv_email);
        TextView tvSex = (TextView) findViewById(R.id.tv_sex);
        Button btnLoginout = (Button) findViewById(R.id.btn_loginout);
        tvName.setText(user_name);
        tvTel.setText(user_tel);
        tvEmail.setText(user_email);
        tvSex.setText(user_sex);
        btnLoginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}

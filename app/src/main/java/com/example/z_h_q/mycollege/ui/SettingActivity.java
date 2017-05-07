package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;

/**
 * Created by Z-H-Q on 2017/4/11.
 */
public class SettingActivity extends Activity {
    LinearLayout llBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("设置");
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setVisibility(View.VISIBLE);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

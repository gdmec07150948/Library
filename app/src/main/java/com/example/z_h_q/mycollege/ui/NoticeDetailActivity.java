package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;

/**
 * Created by Z-H-Q on 2017/4/9.
 */
public class NoticeDetailActivity extends Activity {
    LinearLayout llBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("公告详情");
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setVisibility(View.VISIBLE);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getApplication(),NoticeListActivity.class);
                startActivity(intent);*/
                finish();
            }
        });
        String mNotice_title = getIntent().getStringExtra("notice_title");
        String mNotice_content = getIntent().getStringExtra("notice_content");
        String mNotice_date = getIntent().getStringExtra("notice_date");

        TextView tvNoticeDetailTitle = (TextView) findViewById(R.id.tv_notice_detail_title);
        TextView tvNoticeDetailContent = (TextView) findViewById(R.id.tv_notice_detail_content);
        TextView tvNoticeDetailDate = (TextView) findViewById(R.id.tv_notice_detail_date);
        tvNoticeDetailTitle.setText(mNotice_title);
        tvNoticeDetailContent.setText(mNotice_content);
        tvNoticeDetailDate.setText(mNotice_date);
    }
}

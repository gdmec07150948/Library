package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;

/**
 * Created by Z-H-Q on 2017/4/13.
 */
public class SearchActivity extends Activity {
    private EditText mEditText;
    private ImageView mImageView;
    private ListView mListView;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }
    private void initView() {
        mTextView = (TextView) findViewById(R.id.textview);
        mEditText = (EditText) findViewById(R.id.edittext);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mListView = (ListView) findViewById(R.id.listview);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_commend);

        //设置删除图片的点击事件
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把EditText内容设置为空
                mEditText.setText("");
                //把ListView隐藏
                mListView.setVisibility(View.GONE);
                mLinearLayout.setVisibility(View.VISIBLE);
            }
        });

        //EditText添加监听
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            //文本改变之前执行
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            //文本改变的时候执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果长度为0
                if (s.length() == 0) {
                    //隐藏“删除”图片
                    mImageView.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示“删除图片”
                    mImageView.setVisibility(View.VISIBLE);
                    mLinearLayout.setVisibility(View.GONE);

                    //显示ListView
                }
            }

            //文本改变之后执行
            public void afterTextChanged(Editable s) {
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果输入框内容为空，提示请输入搜索内容


            }
        });
    }
}

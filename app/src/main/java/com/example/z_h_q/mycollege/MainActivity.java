package com.example.z_h_q.mycollege;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.z_h_q.mycollege.fragment.IndexFragment;
import com.example.z_h_q.mycollege.fragment.MeFragment;
import com.example.z_h_q.mycollege.fragment.StackFragment;
import com.example.z_h_q.mycollege.fragment.TrendsFragment;

/**
 * Created by Z-H-Q on 2017/3/25.
 */
public class MainActivity extends FragmentActivity {
    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    //定义一个布局
    private LayoutInflater layoutInflater;
    //定义数组存放fragment对象
    private Class fragmentArray[] = {IndexFragment.class,StackFragment.class,TrendsFragment.class,MeFragment.class};
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.icon_article_selector,R.drawable.icon_book_selector,R.drawable.icon_sale_selector,
            R.drawable.icon_my_selector};
    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "书库", "动态", "我"};
    private Bundle bundle;
    private String user_name;
    private String user_id;
    private String user_icon;
    private String user_trueName;
    private String user_email;
    private String user_tel;
    private String user_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_id = getIntent().getStringExtra("user_id");
        user_name = getIntent().getStringExtra("user_name");
        user_icon = getIntent().getStringExtra("user_icon");
        user_trueName = getIntent().getStringExtra("user_trueName");
        user_email = getIntent().getStringExtra("user_email");
        user_tel = getIntent().getStringExtra("user_tel");
        user_sex = getIntent().getStringExtra("user_sex");

        System.out.println("昵称"+ user_name);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        View paddingView = findViewById(R.id.paddingView);
        ViewGroup.LayoutParams params = paddingView.getLayoutParams();
        params.height = getStatusBarHeight();

        initView();
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    /**
     * 初始化组件
     */
    private void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);
        //得到fragment的个数
        int count = fragmentArray.length;
        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            bundle = new Bundle();
            bundle.putString("user_id",user_id);
            bundle.putString("user_icon",user_icon);
            bundle.putString("user_name", user_name);
            bundle.putString("user_trueName", user_trueName);
            bundle.putString("user_email", user_email);
            bundle.putString("user_tel", user_tel);
            bundle.putString("user_sex", user_sex);
            mTabHost.addTab(tabSpec, fragmentArray[i],bundle);
            mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        }
    }
    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        return view;
    }
}

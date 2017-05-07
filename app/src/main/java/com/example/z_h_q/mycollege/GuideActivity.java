package com.example.z_h_q.mycollege;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.z_h_q.mycollege.ui.LoginActivity;
import com.example.z_h_q.mycollege.utils.PrefUtils;

import java.util.ArrayList;

/**
 * 新手引导页
 */
public class GuideActivity extends Activity{
    private ViewPager vpGuide;
    private Button btnStart;
    //引导页图片数组
    private int[] mImageIds = new int[]{R.drawable.guide_0,R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3,R.drawable.guide_4};
    private ArrayList<ImageView> mImageViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtils.setBoolean(getApplicationContext(),"is_first_enter",false);
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                //System.out.println("跳转成功");
                //finish();
            }
        });
        //初始化数据
        initData();
        //设置数据
        vpGuide.setAdapter(new GuideAdapter());
        //设置监听
        vpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //某个页面被选中
            @Override
            public void onPageSelected(int position) {
                if (position == mImageViewList.size()-1){
                    //最后一个页面是显示按钮
                    btnStart.setVisibility(View.VISIBLE);
                }else{
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        mImageViewList = new ArrayList<ImageView>();
        for(int i=0;i<mImageIds.length;i++){
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);//设置背景
            mImageViewList.add(view);
        }
    }

    class GuideAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        //初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            return view;
        }
        //销毁item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

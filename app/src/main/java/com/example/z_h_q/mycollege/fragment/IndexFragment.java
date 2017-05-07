package com.example.z_h_q.mycollege.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.bean.NoticesListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.example.z_h_q.mycollege.ui.BookDetailActivity;
import com.example.z_h_q.mycollege.ui.CommendListActivity;
import com.example.z_h_q.mycollege.ui.NoticeDetailActivity;
import com.example.z_h_q.mycollege.ui.NoticeListActivity;
import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.ui.SearchActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Z-H-Q on 2017/3/25.
 */
public class IndexFragment extends Fragment {

    ImageView ivSearch;
    private ViewPager vpTop;
    private CirclePageIndicator mIndicator;
    private Handler mHandler;
    private int[] mImageIds = new int[]{R.drawable.guide_0,R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3,R.drawable.guide_4};
    private ArrayList<ImageView> mImageViewList;

    //公告
    private LinearLayout llNoticeContent;
    private TextView tvNoticeContent;
    private TextView tvNoticeMore;
    String mUrl = GlobalConstants.SERVER_URL+"get_all_notice.php";
    Gson gson;
    private NoticesListBean noticeListBean;

    //推荐
    private ImageView ivCommendIcon;
    private TextView tvCommendName;
    private TextView tvCommendAuthor;
    private ImageView ivCommendIcon2;
    private TextView tvCommendName2;
    private TextView tvCommendAuthor2;
    private ImageView ivCommendIcon3;
    private TextView tvCommendName3;
    private TextView tvCommendAuthor3;
    private RelativeLayout rlCommend;
    private RelativeLayout rlCommend2;
    private RelativeLayout rlCommend3;

    private LinearLayout llCommendRefresh;
    private Button btnAll;
    private List<Map<String, Object>> data_list;
    private int[] icon={R.drawable.category1,R.drawable.category2,R.drawable.category3,
            R.drawable.category4,R.drawable.category5,R.drawable.book1,R.drawable.book,
            R.drawable.book1,R.drawable.book,R.drawable.book1};
    private String[] name = {"纪委书记","说话要有心眼办事要有手腕","我的苦难，我的大学","7天养好你自己","1分钟看透对方心理","孩子6，把你的手给我","孩子7，把你的手给我","孩子8，把你的手给我","孩子9，把你的手给我","孩子10，把你的手给我"};
    private String[] author = {"罗晓","马银春","赵美萍","罗芬芬","田由申","海姆6·G·吉诺特","海姆7·G·吉诺特","海姆8·G·吉诺特","海姆9·G·吉诺特","海姆10·G·吉诺特"};

    private View head;
    private int i;
    private int k;
    private int z;
    private Intent intent;
    private String notice_title;
    private String notice_content;
    private String notice_date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        gson = new Gson();
        initNoticeData();
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText("首页");
        ivSearch = (ImageView) view.findViewById(R.id.iv_search);
        ivSearch.setVisibility(View.VISIBLE);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
        head = view.findViewById(R.id.head);
        head.setFocusable(true);
        head.setFocusableInTouchMode(true);
        head.requestFocus();


        mIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        vpTop = (ViewPager) view.findViewById(R.id.vp_top);
        //初始化数据
        initData();
        //设置数据
        vpTop.setAdapter(new TopAdapter());
        mIndicator.setViewPager(vpTop);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int currentItem = vpTop.getCurrentItem();
                currentItem++;
                if (currentItem > mImageViewList.size() - 1){
                    currentItem = 0;
                }
                vpTop.setCurrentItem(currentItem);
                mHandler.sendEmptyMessageDelayed(0,3000);
            }
        };

        tvNoticeContent = (TextView) view.findViewById(R.id.tv_notice_content);
        llNoticeContent = (LinearLayout) view.findViewById(R.id.ll_notice_content);
        tvNoticeMore = (TextView) view.findViewById(R.id.tv_notice_more);


        tvNoticeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(), NoticeListActivity.class);
                startActivity(intent);
            }
        });


        ivCommendIcon = (ImageView) view.findViewById(R.id.iv_commend_icon);
        tvCommendName = (TextView) view.findViewById(R.id.tv_commend_name);
        tvCommendAuthor = (TextView) view.findViewById(R.id.tv_commend_author);
        //ivCommendIcon.setImageResource();
        /*for(int i = 0;i<name.length;i++){
            ivCommendIcon.setImageResource(R.drawable.book);
            tvCommendName.setText(name[i]);
            tvCommendAuthor.setText("kkk");
        }*/
        i = (int) (Math.random()*name.length);
        System.out.println("i="+ i);
        ivCommendIcon.setImageResource(icon[i]);
        tvCommendName.setText(name[i]);
        tvCommendAuthor.setText(author[i]);
        ivCommendIcon2 = (ImageView) view.findViewById(R.id.iv_commend_icon2);
        tvCommendName2 = (TextView) view.findViewById(R.id.tv_commend_name2);
        tvCommendAuthor2 = (TextView) view.findViewById(R.id.tv_commend_author2);
        k = (int) (Math.random()*name.length);
        System.out.println("k="+ k);
        if (k == i){
            k = (int) (Math.random()*name.length);
        }else{
            ivCommendIcon2.setImageResource(icon[k]);
            tvCommendName2.setText(name[k]);
            tvCommendAuthor2.setText(author[k]);
        }
        ivCommendIcon3 = (ImageView) view.findViewById(R.id.iv_commend_icon3);
        tvCommendName3 = (TextView) view.findViewById(R.id.tv_commend_name3);
        tvCommendAuthor3 = (TextView) view.findViewById(R.id.tv_commend_author3);
        z = (int) (Math.random()*name.length);
        System.out.println("z="+ z);
        if (z == i || z == k){
            z = (int) (Math.random()*name.length);
        }else{
            ivCommendIcon3.setImageResource(icon[z]);
            tvCommendName3.setText(name[z]);
            tvCommendAuthor3.setText(author[z]);

        }

        llCommendRefresh = (LinearLayout) view.findViewById(R.id.ll_commend_refresh);
        llCommendRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = (int) (Math.random()*name.length);
                ivCommendIcon.setImageResource(icon[i]);
                tvCommendName.setText(name[i]);
                tvCommendAuthor.setText(author[i]);
                k = (int) (Math.random()*name.length);
                if (k == i){
                    k = (int) (Math.random()*5);
                }else{
                    ivCommendIcon2.setImageResource(icon[k]);
                    tvCommendName2.setText(name[k]);
                    tvCommendAuthor2.setText(author[k]);
                }
                z = (int) (Math.random()*name.length);
                if (z == i || z == k){
                    z = (int) (Math.random()*name.length);
                }else{
                    ivCommendIcon3.setImageResource(icon[z]);
                    tvCommendName3.setText(name[z]);
                    tvCommendAuthor3.setText(author[z]);

                }
            }
        });

        rlCommend = (RelativeLayout) view.findViewById(R.id.rl_commend);
        rlCommend2 = (RelativeLayout) view.findViewById(R.id.rl_commend2);
        rlCommend3 = (RelativeLayout) view.findViewById(R.id.rl_commend3);
        rlCommend.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), BookDetailActivity.class);
                intent.putExtra("name",name[i]);
                intent.putExtra("author",author[i]);
                startActivity(intent);
            }
        });
        rlCommend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), BookDetailActivity.class);
                intent.putExtra("name",name[k]);
                intent.putExtra("author",author[k]);
                startActivity(intent);
            }
        });
        rlCommend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), BookDetailActivity.class);
                intent.putExtra("name",name[z]);
                intent.putExtra("author",author[z]);
                startActivity(intent);
            }
        });

        btnAll = (Button) view.findViewById(R.id.btn_all);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(), CommendListActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void initNoticeData(){
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            private String notice_content;

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                noticeListBean = gson.fromJson(result.replace("\uFEFF",""), new TypeToken<NoticesListBean>() {}.getType());
                notice_title = noticeListBean.getNotices().get(0).getNotice_title();
                notice_content = noticeListBean.getNotices().get(0).getNotice_content();
                notice_date = noticeListBean.getNotices().get(0).getNotice_date();
                tvNoticeContent.setText(notice_title);

                llNoticeContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), NoticeDetailActivity.class);
                        intent.putExtra("notice_title", notice_title);
                        intent.putExtra("notice_content", notice_content);
                        intent.putExtra("notice_date", notice_date);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffffffffffffffffff");
                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<name.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            //map.put("icon", icon[i]);
            map.put("name", name[i]);
            map.put("author",author[i]);
            data_list.add(map);
        }
        return data_list;
    }
    private void initData() {
        mImageViewList = new ArrayList<ImageView>();

        for(int i=0;i<mImageIds.length;i++){
            ImageView view = new ImageView(getContext());
            view.setBackgroundResource(mImageIds[i]);//设置背景
            mImageViewList.add(view);
        }

    }

    private class TopAdapter extends PagerAdapter {
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

package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.adapter.CommentAdapter;
import com.example.z_h_q.mycollege.bean.BookListBean;
import com.example.z_h_q.mycollege.bean.CommentListBean;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/9.
 */
public class BookDetailActivity extends Activity {
    LinearLayout llDescriptionLayout;
    TextView tvDescriptionView;
    ImageView ivExpandView;
    int maxDescripLine = 2; //TextView默认最大展示行数
    ListView lvBookDetailComment;
    ImageView ivBookDetailIcon;
    TextView tvBookDetailName;
    TextView tvBookDetailAuthor;
    TextView tvBookDetailCategory;
    RelativeLayout rlBookDetailComment;
    TextView title;
    LinearLayout llBack;
    private TextView tvBookDetailHouse;
    BitmapUtils bitmapUtils;
    private LinearLayout llSendBookCommend;
    String mUrl = GlobalConstants.SERVER_URL+"get_book_comment.php";
    Gson gson;
    List<CommentListBean.CommentsBean> commentsBeanList;
    CommentAdapter commentAdapter;
    private String book_id;
    private String book_name;
    private TextView noItem;
    private String user_id;
    private ImageView ivCollection;
    private int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        book_id = intent.getStringExtra("book_id");
        String book_name =  intent.getStringExtra("book_name");
        String book_img = intent.getStringExtra("book_img");
        String book_author = intent.getStringExtra("book_author");
        String book_about = intent.getStringExtra("book_about");
        String book_category = intent.getStringExtra("book_category");
        String book_house = intent.getStringExtra("book_house");

        llDescriptionLayout = (LinearLayout) findViewById(R.id.ll_description_layout);
        tvDescriptionView = (TextView)findViewById(R.id.tv_description_view);
        ivExpandView = (ImageView) findViewById(R.id.iv_expand_view);

        lvBookDetailComment = (ListView) findViewById(R.id.lv_book_detail_comment);
        View mHeaderView = View.inflate(this,R.layout.book_detail_header,null);
        ivBookDetailIcon = (ImageView) mHeaderView.findViewById(R.id.iv_book_detail_icon);
        bitmapUtils = new BitmapUtils(this);
        bitmapUtils.display(ivBookDetailIcon, GlobalConstants.SERVER_IMG_URL+book_img);
        tvBookDetailName = (TextView) mHeaderView.findViewById(R.id.tv_book_detail_name);
        tvBookDetailName.setText(book_name);
        tvBookDetailAuthor = (TextView) mHeaderView.findViewById(R.id.tv_book_detail_author);
        tvBookDetailAuthor.setText(book_author);
        tvBookDetailHouse = (TextView) mHeaderView.findViewById(R.id.tv_book_detail_house);
        tvBookDetailHouse.setText(book_house);
        tvBookDetailCategory = (TextView) mHeaderView.findViewById(R.id.tv_book_detail_category);
        tvBookDetailCategory.setText(book_category);

        llDescriptionLayout = (LinearLayout) mHeaderView.findViewById(R.id.ll_description_layout);
        tvDescriptionView = (TextView) mHeaderView.findViewById(R.id.tv_description_view);
        ivExpandView = (ImageView) mHeaderView.findViewById(R.id.iv_expand_view);
        rlBookDetailComment = (RelativeLayout) mHeaderView.findViewById(R.id.rl_book_detail_comment);
        title = (TextView)findViewById(R.id.title);
        title.setText("书籍详情");
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setVisibility(View.VISIBLE);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //设置文本
        tvDescriptionView.setText(book_about);
        //descriptionView设置默认显示高度
        tvDescriptionView.setHeight(tvDescriptionView.getLineHeight() * maxDescripLine);
        //根据高度来判断是否需要再点击展开
        tvDescriptionView.post(new Runnable() {

            @Override
            public void run() {
                ivExpandView.setVisibility(tvDescriptionView.getLineCount() > maxDescripLine ? View.VISIBLE : View.GONE);
            }
        });
        llDescriptionLayout.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;//是否已展开的状态

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                tvDescriptionView.clearAnimation();//清楚动画效果
                final int deltaValue;//默认高度，即前边由maxLine确定的高度
                final int startValue = tvDescriptionView.getHeight();//起始高度
                int durationMillis = 350;//动画持续时间
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = tvDescriptionView.getLineHeight() * tvDescriptionView.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    ivExpandView.startAnimation(animation);
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = tvDescriptionView.getLineHeight() * maxDescripLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    ivExpandView.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        tvDescriptionView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                tvDescriptionView.startAnimation(animation);
            }

        });
        llSendBookCommend = (LinearLayout) mHeaderView.findViewById(R.id.ll_send_book_commend);
        llSendBookCommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditActivity.class);
                intent.putExtra("book_id",book_id);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });

        lvBookDetailComment.addHeaderView(mHeaderView);
        commentsBeanList = new ArrayList<>();
        gson = new Gson();
        initData();
        commentAdapter = new CommentAdapter(commentsBeanList,getApplicationContext());
        lvBookDetailComment.setAdapter(commentAdapter);
       /* LinearLayout noItem = (LinearLayout) findViewById(R.id.no_item);
        lvBookDetailComment.setEmptyView(noItem);*/
        noItem = (TextView) findViewById(R.id.no_item);
        LinearLayout llCollection = (LinearLayout) findViewById(R.id.ll_collection);
        ivCollection = (ImageView) findViewById(R.id.iv_collection);
        if (flag==0){
            ivCollection.setImageResource(R.drawable.book_list_star_empty);
        }else{
            ivCollection.setImageResource(R.drawable.book_list_star_full);
        }
        /*if (isCollection = true){
            ivCollection.setImageResource(R.drawable.book_list_star_full);
        }else {
            ivCollection.setImageResource(R.drawable.book_list_star_empty);
        }*/

        llCollection.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                System.out.println("111111111111111");
                if (flag==0){
                    ivCollection.setImageResource(R.drawable.book_list_star_empty);
                    flag=1;
                }else{
                    ivCollection.setImageResource(R.drawable.book_list_star_full);
                    flag=0;
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    public void initData(){
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("book_id",book_id);
        System.out.println(book_id+"555555555");
        utils.send(HttpRequest.HttpMethod.GET, mUrl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                CommentListBean commentListBean = gson.fromJson(result.replace("\uFEFF", ""), new TypeToken<CommentListBean>() {
                }.getType());
                if(commentListBean.getSuccess()==1){
                    commentsBeanList.clear();
                    commentsBeanList.addAll(commentListBean.getComments());
                }else{
                    System.out.println("值为空");
                    noItem.setVisibility(View.VISIBLE);
                }

                runOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        commentAdapter.notifyDataSetChanged();//在主线程中刷新listView

                    }
                });
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffff");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

            }
        });
    }
}

package com.example.z_h_q.mycollege.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.bean.UserListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.example.z_h_q.mycollege.ui.BorrowActivity;
import com.example.z_h_q.mycollege.ui.CollectionActivity;
import com.example.z_h_q.mycollege.ui.InformationActivity;
import com.example.z_h_q.mycollege.ui.OrderActicity;
import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.ui.SettingActivity;
import com.example.z_h_q.mycollege.ui.SuggestActivity;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Z-H-Q on 2017/3/25.
 */
public class MeFragment extends Fragment{
    private BitmapUtils mBitmapUtils;
    private LinearLayout llOrder;
    private LinearLayout llCollection;
    private LinearLayout llBorrow;
    private LinearLayout llSetting;
    private LinearLayout llSuggest;
    private LinearLayout llInformation;
    String mUrl = "http://10.0.2.2/library/get_all_user.php";
    TextView tvName;
    Gson gson;
    private TextView tvPhone;
    private String user_id;
    private String user_name;
    private String user_trueName;
    private String user_email;
    private String user_tel;
    private String user_sex;
    private String user_icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText("我");
        Bundle bundle = this.getArguments();
        user_id = bundle.getString("user_id");
        user_name = bundle.getString("user_name");
        user_icon = bundle.getString("user_icon");
        user_trueName = bundle.getString("user_trueName");
        user_email = bundle.getString("user_email");
        user_tel = bundle.getString("user_tel");
        user_sex = bundle.getString("user_sex");
        System.out.println(user_id +"--"+ user_name +"--"+ user_icon +"--"+ user_trueName +"--"+ user_email +"--"+ user_tel +"--"+ user_sex);
        mBitmapUtils = new BitmapUtils(getContext());
        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvPhone = (TextView) view.findViewById(R.id.tv_phone);
        mBitmapUtils.display(ivIcon, GlobalConstants.SERVER_IMG_URL+user_icon);
        tvName.setText(user_name);
        tvPhone.setText(user_tel);

        llInformation = (LinearLayout) view.findViewById(R.id.ll_information);
        llInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),InformationActivity.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("user_icon",user_icon);
                intent.putExtra("user_name", user_name);
                intent.putExtra("user_trueName", user_trueName);
                intent.putExtra("user_email", user_email);
                intent.putExtra("user_tel", user_tel);
                intent.putExtra("user_sex", user_sex);
                startActivity(intent);
            }
        });

        llOrder = (LinearLayout) view.findViewById(R.id.ll_order);
        llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),OrderActicity.class);
                startActivity(intent);
            }
        });
        llCollection = (LinearLayout) view.findViewById(R.id.ll_collection);
        llCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CollectionActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        llBorrow = (LinearLayout) view.findViewById(R.id.ll_borrow);
        llBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BorrowActivity.class);
                startActivity(intent);
            }
        });
        llSetting = (LinearLayout) view.findViewById(R.id.ll_setting);
        llSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });
        llSuggest = (LinearLayout) view.findViewById(R.id.ll_suggest);
        llSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SuggestActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private View initView(){
        return null;
    }

}

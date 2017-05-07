package com.example.z_h_q.mycollege.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.adapter.TrendAdapter;
import com.example.z_h_q.mycollege.bean.NoticesListBean;
import com.example.z_h_q.mycollege.bean.TrendListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.example.z_h_q.mycollege.ui.SendTreadsActivity;
import com.example.z_h_q.mycollege.view.PullToRefreshListView;
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

/**
 * Created by Z-H-Q on 2017/3/25.
 */
public class TrendsFragment extends Fragment {
    private RelativeLayout rlBoard;
    private ImageView ivClear;
    private PullToRefreshListView lvTrends;
    private ImageView ivSend;
    private List<TrendListBean.TrendsBean> trendsBeanList;
    TrendAdapter trendAdapter;
    Gson gson;
    String mUrl = GlobalConstants.SERVER_URL+"get_all_trends.php";
    private LinearLayout llTreads;
    private EditText etCommentContent;
    private TextView tvSend;
    private TrendListBean trendListBean;
    private String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trends, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText("动态");
        Bundle bundle = this.getArguments();
        user_id = bundle.getString("user_id");
        System.out.println("id"+user_id);
        initData();
        lvTrends = (PullToRefreshListView) view.findViewById(R.id.lv_trends);
        lvTrends.setRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }

            @Override
            public void onLoadMore() {

            }
        });
        lvTrends.setDividerHeight(0);
        //lvTrends.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);

        View mHeaderView = View.inflate(getActivity(),R.layout.trends_header,null);
        rlBoard = (RelativeLayout) mHeaderView.findViewById(R.id.rl_board);
        ivClear = (ImageView) mHeaderView.findViewById(R.id.iv_clear);
        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlBoard.setVisibility(View.GONE);
            }
        });
        lvTrends.addHeaderView(mHeaderView);
        ivSend = (ImageView) view.findViewById(R.id.iv_send);
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SendTreadsActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        trendsBeanList = new ArrayList<>();
        gson = new Gson();
        trendAdapter = new TrendAdapter(trendsBeanList,getContext());

        lvTrends.setAdapter(trendAdapter);

        /*llTreads = (LinearLayout) view.findViewById(R.id.ll_trends);
        etCommentContent = (EditText) view.findViewById(R.id.et_comment_content);*/
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initData(){
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                trendListBean = gson.fromJson(result.replace("\uFEFF", ""), new TypeToken<TrendListBean>() {}.getType());
                System.out.println(trendListBean.getTrends().get(0).getUser_id());
                //先清空原有的list
                trendsBeanList.clear();
                trendsBeanList.addAll(trendListBean.getTrends());//再将获取到的数据完全add进productsBeanList.
                getActivity().runOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        trendAdapter.notifyDataSetChanged();//在主线程中刷新listView
                        lvTrends.onRefreshComplete();
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

    /*public void initChildData(){
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                trendListBean = gson.fromJson(result.replace("\uFEFF", ""), new TypeToken<TrendListBean>() {}.getType());
                System.out.println(trendListBean.getTrends().get(0).getTrend_content()+"===================");
                //先清空原有的list
                trendsBeanList.clear();
                trendsBeanList.addAll(trendListBean.getTrends());//再将获取到的数据完全add进productsBeanList.
                getActivity().runOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        trendAdapter.notifyDataSetChanged();//在主线程中刷新listView
                    }
                });
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("fffffffffffffffffffff");
                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
            }
        });

    }*/


    private ArrayList<HashMap<String, Object>> getChildDate(){
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
        //为动态数组添加数据
        for(int i=0;i<10;i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tvCommentItemName", "低调、平凡");
            map.put("tvCommentItemContent", "dsdsds");
            listItem.add(map);
        }
        return listItem;
    }

    /*private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return getDate().size();
        }

        @Override
        public Object getItem(int i) {
            return getDate().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parentp) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getActivity(),R.layout.trends_item,null);
                holder = new ViewHolder();
                //得到各个控件的对象
                holder.ivTrendsItemIcon = (ImageView) convertView.findViewById(R.id.iv_trends_item_icon);
                holder.tvTrendsItemName = (TextView) convertView.findViewById(R.id.tv_trends_item_name);
                holder.tvTrendsItemContent = (TextView) convertView.findViewById(R.id.tv_trends_item_content);
                holder.tvTrendsItemData = (TextView) convertView.findViewById(R.id.tv_trends_item_data);
                holder.ivRecover = (ImageView) convertView.findViewById(R.id.iv_recover);
                holder.lvComment = (ListView) convertView.findViewById(R.id.lv_comment);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
           //设置TextView显示的内容，即我们存放在动态数组中的数据
            //mBitmapUtils.display(holder.ivCommentIcon, String.valueOf(R.drawable.lk_icon));
            holder.ivTrendsItemIcon.setImageResource(R.drawable.lk_icon);
            holder.tvTrendsItemName.setText(getDate().get(position).get("tvTrendsItemName").toString());
            holder.tvTrendsItemContent.setText(getDate().get(position).get("tvTrendsItemContent").toString());
            holder.tvTrendsItemData.setText(getDate().get(position).get("tvTrendsItemData").toString());
            holder.ivRecover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    etCommentContent.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etCommentContent, InputMethodManager.SHOW_IMPLICIT);
                    // 显示评论框
                    llTreads.setVisibility(View.VISIBLE);
                }
            });
            holder.lvComment.setAdapter(new ChildAdapter());
            holder.lvComment.setDividerHeight(0);
            //setListViewHeightBasedOnChildren(holder.lvComment);

            return convertView;
        }
        class ViewHolder{
            public ImageView ivTrendsItemIcon;
            public TextView tvTrendsItemName;
            public TextView tvTrendsItemContent;
            public TextView tvTrendsItemData;
            public ImageView ivRecover;
            public ListView lvComment;
        }
    }*/

    /*public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
*/
    private class ChildAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return getChildDate().size();
        }

        @Override
        public Object getItem(int i) {
            return getChildDate().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parentp) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getActivity(),R.layout.comment_item,null);
                holder = new ViewHolder();
                //得到各个控件的对象
                holder.tvCommentItemName = (TextView) convertView.findViewById(R.id.tv_comment_name);
                holder.tvCommentItemContent = (TextView) convertView.findViewById(R.id.tv_comment_content);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
            holder.tvCommentItemName.setText(getChildDate().get(position).get("tvCommentItemName").toString());
            holder.tvCommentItemContent.setText(getChildDate().get(position).get("tvCommentItemContent").toString());
            return convertView;
        }
        class ViewHolder{
            public TextView tvCommentItemName;
            public TextView tvCommentItemContent;
        }
    }
}

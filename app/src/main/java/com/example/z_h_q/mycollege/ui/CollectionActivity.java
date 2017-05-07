package com.example.z_h_q.mycollege.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.adapter.ColleactionAdapter;
import com.example.z_h_q.mycollege.bean.CollectionListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/11.
 */
public class CollectionActivity extends ListActivity {
    List<CollectionListBean.CollectionsBean> collectionsBeanList;
    ColleactionAdapter colleactionAdapter;
    Gson gson;
    String mUrl = GlobalConstants.SERVER_URL+"get_collection.php";
    ListView listView;
    LinearLayout llBack;
    private String user_id;
    private CollectionListBean collectionListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("我的收藏");
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setVisibility(View.VISIBLE);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gson = new Gson();
        collectionsBeanList = new ArrayList<>();
        colleactionAdapter = new ColleactionAdapter(collectionsBeanList,getApplicationContext());
        initDate();
        listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(colleactionAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),BookDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initDate(){
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id",user_id);
        System.out.println(user_id+"555555555");
        utils.send(HttpRequest.HttpMethod.GET, mUrl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                System.out.println("ttttttt");
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                collectionListBean = gson.fromJson(result.replace("\uFEFF", ""), new TypeToken<CollectionListBean>() {
                }.getType());
                collectionsBeanList.clear();
                collectionsBeanList.addAll(collectionListBean.getCollections());
                runOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        colleactionAdapter.notifyDataSetChanged();//在主线程中刷新listView
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


    /*private ArrayList<HashMap<String, Object>> getDate(){

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
        //为动态数组添加数据
        for(int i=0;i<3;i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tvBookName", "孩子，把你的手给我");
            map.put("tvBookAuther", "海姆·G·吉诺特");
            listItem.add(map);
        }
        return listItem;

    }

    private class CollectionListAdaper extends BaseAdapter {
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
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplication(),R.layout.collection_book_item,
                        null);
                holder = new ViewHolder();
                //得到各个控件的对象
                holder.ivBookPic = (ImageView) convertView.findViewById(R.id.iv_book_pic);
                holder.tvBookName = (TextView) convertView.findViewById(R.id.tv_book_name);
                holder.tvBookAuther = (TextView) convertView.findViewById(R.id.tv_book_author);
                convertView.setTag(holder);//绑定ViewHolder对象                   }
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象                  }
            }
            //设置TextView显示的内容，即我们存放在动态数组中的数据
            holder.ivBookPic.setImageResource(R.drawable.book);
            holder.tvBookName.setText(getDate().get(position).get("tvBookName").toString());
            holder.tvBookAuther.setText(getDate().get(position).get("tvBookAuther").toString());
            return convertView;
        }
    }
    static class ViewHolder{
        public ImageView ivBookPic;
        public TextView tvBookName;
        public TextView tvBookAuther;
    }*/
}

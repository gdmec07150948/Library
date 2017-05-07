package com.example.z_h_q.mycollege.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.z_h_q.mycollege.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Z-H-Q on 2017/4/11.
 */
public class BorrowActivity extends Activity {
    ListView listView;
    LinearLayout llBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("我的借阅");
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setVisibility(View.VISIBLE);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(new BorrowListAdaper());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),BookDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<HashMap<String, Object>> getDate(){

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
        //为动态数组添加数据
        for(int i=0;i<3;i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tvBookName", "孩子，把你的手给我");
            map.put("tvBookAuther", "海姆·G·吉诺特");
            map.put("tvBorrowData","2016-1-1");
            map.put("tvReturnData","2016-3-1");
            listItem.add(map);
        }
        return listItem;

    }

    private class BorrowListAdaper extends BaseAdapter {
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
                convertView = View.inflate(getApplication(),R.layout.borrow_book_item,
                        null);
                holder = new ViewHolder();
                //得到各个控件的对象
                holder.ivBookPic = (ImageView) convertView.findViewById(R.id.iv_book_pic);
                holder.tvBookName = (TextView) convertView.findViewById(R.id.tv_book_name);
                holder.tvBookAuther = (TextView) convertView.findViewById(R.id.tv_book_author);
                holder.tvBorrowData = (TextView) convertView.findViewById(R.id.tv_borrow_data);
                holder.tvReturnData = (TextView) convertView.findViewById(R.id.tv_return_data);
                convertView.setTag(holder);//绑定ViewHolder对象                   }
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象                  }
            }
            //设置TextView显示的内容，即我们存放在动态数组中的数据
            holder.ivBookPic.setImageResource(R.drawable.book);
            holder.tvBookName.setText(getDate().get(position).get("tvBookName").toString());
            holder.tvBookAuther.setText(getDate().get(position).get("tvBookAuther").toString());
            holder.tvBorrowData.setText(getDate().get(position).get("tvBorrowData").toString());
            holder.tvReturnData.setText(getDate().get(position).get("tvReturnData").toString());
            return convertView;
        }
    }
    static class ViewHolder{
        public ImageView ivBookPic;
        public TextView tvBookName;
        public TextView tvBookAuther;
        public TextView tvBorrowData;
        public TextView tvReturnData;
    }

}

package com.example.z_h_q.mycollege.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z_h_q.mycollege.adapter.GridViewAdapter;
import com.example.z_h_q.mycollege.bean.BookCategoryBean;
import com.example.z_h_q.mycollege.bean.NoticesListBean;
import com.example.z_h_q.mycollege.global.GlobalConstants;
import com.example.z_h_q.mycollege.ui.BookListActivity;
import com.example.z_h_q.mycollege.R;
import com.example.z_h_q.mycollege.ui.SearchActivity;
import com.example.z_h_q.mycollege.view.GridViewWithHeaderAndFooter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Z-H-Q on 2017/3/25.
 */
public class StackFragment extends Fragment {
    private GridViewAdapter gridViewAdapter;
    private List<BookCategoryBean.CategorysBean> categorysBeanList;
    private GridViewWithHeaderAndFooter gvBookCategory;
    Gson gson;
    private List<Map<String, Object>> data_list;
    String mUrl = GlobalConstants.SERVER_URL+"get_all_category.php";
    private EditText editText;
    private Intent intent;
    private BookCategoryBean bookCategoryBean;
    private String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stack, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText("书库");
        Bundle bundle = this.getArguments();
        user_id = bundle.getString("user_id");
        System.out.println("id"+user_id);
        initData();
        gson = new Gson();
        categorysBeanList = new ArrayList<>();
        gvBookCategory = (GridViewWithHeaderAndFooter) view.findViewById(R.id.gv_book_category);
        View mHeaderView = View.inflate(getContext(),R.layout.fragment_stack_header,null);
        editText = (EditText) mHeaderView.findViewById(R.id.edittext);
        gvBookCategory.addHeaderView(mHeaderView);

        /*editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("fff");
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });*/

        gridViewAdapter = new GridViewAdapter(categorysBeanList,getContext());
        gvBookCategory.setAdapter(gridViewAdapter);
        gvBookCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category_name = bookCategoryBean.getCategorys().get(position).getCategory_name();
                intent = new Intent(getActivity(),BookListActivity.class);
                intent.putExtra("category_name",category_name);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });

        /*data_list = new ArrayList<Map<String, Object>>();
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), //没什么解释
                getData(),//数据来源
                R.layout.book_category_item,//night_item的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"icon","name","number"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.iv_book_category_icon,R.id.tv_book_category_name,R.id.tv_book_category_number});
        gvBookCategory.setAdapter(adapter);
        gvBookCategory.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println("Ttt");
                System.out.print(position+"------"+id);
                Intent intent = new Intent(getActivity(),BookListActivity.class);
                intent.putExtra("title",name[position]);
                startActivity(intent);
            }
        });*/
        return view;
    }

    private void initData() {
        HttpUtils utils = new HttpUtils(10000);
        utils.configCurrentHttpCacheExpiry(0);
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                System.out.println("返回的结果："+result);
                bookCategoryBean = gson.fromJson(result.replace("\uFEFF", ""), new TypeToken<BookCategoryBean>() {}.getType());
                categorysBeanList.clear();
                categorysBeanList.addAll(bookCategoryBean.getCategorys());
                getActivity().runOnUiThread(new Runnable() {//这个方法可以在子线程中跑到主线程中
                    @Override
                    public void run() {
                        gridViewAdapter.notifyDataSetChanged();//在主线程中刷新listView
                    }
                });
            }

            @Override
            public void onFailure(HttpException e, String s) {
                System.out.println("22222222222222222222");
                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
            }
        });

    }


    /*public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("icon", icon[i]);
            map.put("name", name[i]);
            map.put("number",number[i]);
            data_list.add(map);
        }
        return data_list;
    }*/
}

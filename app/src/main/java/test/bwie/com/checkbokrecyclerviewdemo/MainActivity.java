package test.bwie.com.checkbokrecyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Button mButton;
    private List<String> list = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private MyRecyclerViewAdapter mMyRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initView();
        //设置默认线性
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initData();

        mMyRecyclerViewAdapter = new MyRecyclerViewAdapter(list, this);

        mRecyclerView.setAdapter(mMyRecyclerViewAdapter);
        mMyRecyclerViewAdapter.setRecyclerViewOnItemClickListener(new MyRecyclerViewAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                //点击事件
                //设置选中的项
                mMyRecyclerViewAdapter.setSelectItem(position);
            }

            @Override
            public boolean onItemLongClickListener(View view, int position) {
                //长按事件
                mMyRecyclerViewAdapter.setShowBox();
                //设置选中的项
                mMyRecyclerViewAdapter.setSelectItem(position);
                mMyRecyclerViewAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            list.add("天道酬勤，汗水凝金" + i);

        }

    }

    private void initView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mButton = (Button) findViewById(R.id.but);
        mButton.setOnClickListener(this);

    }

    //toolbar获取条目
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //toolbar监听
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(MainActivity.this, "全选", Toast.LENGTH_SHORT).show();
                Map<Integer, Boolean> map = mMyRecyclerViewAdapter.getMap();
                for (int i = 0; i < map.size(); i++) {
                    map.put(i, true);
                    mMyRecyclerViewAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.action_add:
                Map<Integer, Boolean> m = mMyRecyclerViewAdapter.getMap();
                for (int i = 0; i < m.size(); i++) {
                    m.put(i, false);
                    mMyRecyclerViewAdapter.notifyDataSetChanged();
                }
                Toast.makeText(MainActivity.this, "全不选", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //获取你选中的item
        Map<Integer, Boolean> map = mMyRecyclerViewAdapter.getMap();
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i)) {
                Log.d("TAG", "你选了第：" + i + "项");
            }
        }
    }
}

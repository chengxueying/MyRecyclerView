package recyclerview.hy.com.myrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import recyclerview.hy.com.myrecyclerview.adapter.GridAdapter;
import recyclerview.hy.com.myrecyclerview.decoration.GridItemDecoration;

/**
 * Created by Ying on 2016/2/16.
 */
public class GridRecyclerActivity extends Activity {
    private List<String> mData;
    private RecyclerView mGridRecycler;
    private GridAdapter mGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        initData();
        mGridRecycler = (RecyclerView) findViewById(R.id.recycler_grid);
        //设置布局管理
        mGridRecycler.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        //设置分割线
        mGridRecycler.addItemDecoration(new GridItemDecoration(this));
        //设置动画
        mGridRecycler.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mGridAdapter = new GridAdapter(mData, this);
        mGridRecycler.setAdapter(mGridAdapter);
        mGridAdapter.setmOnItemClickListener(new GridAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                mGridAdapter.addRecycler(0, "70");
                Toast.makeText(GridRecyclerActivity.this, "Click" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                mGridAdapter.removeRecycler(position);
                Toast.makeText(GridRecyclerActivity.this, "LongClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            mData.add(String.valueOf(i));
        }

    }
}

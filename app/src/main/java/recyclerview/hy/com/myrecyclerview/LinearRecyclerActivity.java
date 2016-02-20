package recyclerview.hy.com.myrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import recyclerview.hy.com.myrecyclerview.adapter.FootViewAdapter;
import recyclerview.hy.com.myrecyclerview.adapter.LinearAdapter;
import recyclerview.hy.com.myrecyclerview.decoration.ListItemDecoration;

/**
 * Created by Ying on 2016/2/16.
 */
public class LinearRecyclerActivity extends Activity {
    //    private RecyclerView mLinearRecycler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<String> mData;
    //    private LinearAdapter mLinearAdapter;
    private FootViewAdapter mFootViewAdapter;
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        initData();
        final RecyclerView mLinearRecycler = (RecyclerView) findViewById(R.id.recycler_linear);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.demo_swipeRefreshLayout);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置刷新时动画的颜色，可以设置4个
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_orange_light);
        //这句是为了解决第一次进入不显示进步条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        //设置布局管理器
//      mLinearRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mLinearRecycler.setLayoutManager(linearLayoutManager);
        //设置分割线
        mLinearRecycler.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
        //设置动画
        mLinearRecycler.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mFootViewAdapter = new FootViewAdapter(mData, this);
//        mLinearAdapter = new LinearAdapter(this, mData);
//        mLinearRecycler.setAdapter(mLinearAdapter);
        mLinearRecycler.setAdapter(mFootViewAdapter);
//        mLinearAdapter.setmOnItemClickListener(new LinearAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                mLinearAdapter.addRecycler(0, "战国大美妞");
//                Toast.makeText(LinearRecyclerActivity.this, "Click" + position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void OnItemLongClick(View view, int position) {
//                mLinearAdapter.removeRecycler(position);
//                Toast.makeText(LinearRecyclerActivity.this, "LongClick" + position, Toast.LENGTH_SHORT).show();
//
//            }
//        });
        mLinearRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mFootViewAdapter.getItemCount()) {
                    mFootViewAdapter.changeMoreStatus(FootViewAdapter.LOADING_MORE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> moreData = new ArrayList<String>();
                            for (int i = 0; i < 5; i++) {
                                int index = i + 1;
                                moreData.add("new data" + index);

                            }
                            mFootViewAdapter.addMoreItem(moreData);
                            mFootViewAdapter.changeMoreStatus(FootViewAdapter.PULL_UP_LOAD_MORE);

                        }
                    }, 2500);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> mNewData = new ArrayList<>();
                        for (int i = 0; i < 5; i++) {
                            int index = i + 1;
                            mNewData.add("新数据" + index);
                        }
                        mFootViewAdapter.addItem(mNewData);
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(LinearRecyclerActivity.this, "添加了新数据", Toast.LENGTH_SHORT).show();

                    }
                }, 2500);
            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add(String.valueOf(i));
        }

    }
}

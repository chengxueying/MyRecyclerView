package recyclerview.hy.com.myrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import recyclerview.hy.com.myrecyclerview.adapter.StaggerAdapter;

/**
 * Created by Ying on 2016/2/16.
 */
public class StaggerRecyclerActivity extends Activity {
    private List<String> mData;
    private StaggerAdapter mStaggerAdapter;
    private RecyclerView mStaggerRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger);
        initData();
        mStaggerRecycler= (RecyclerView) findViewById(R.id.recycler_stagger);
        //设置布局管理
        mStaggerRecycler.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        //设置动画
        mStaggerRecycler.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mStaggerAdapter=new StaggerAdapter(mData,this);
        mStaggerRecycler.setAdapter(mStaggerAdapter);
        mStaggerAdapter.setmOnItemClickListener(new StaggerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                mStaggerAdapter.addRecycler(0, "70");
                Toast.makeText(StaggerRecyclerActivity.this, "Click" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                mStaggerAdapter.removeRecycler(position);
                Toast.makeText(StaggerRecyclerActivity.this, "LongClick" + position, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initData() {
        mData=new ArrayList<>();
        for (int i=0;i<60;i++){
            mData.add(String.valueOf(i));
        }

    }
}

package recyclerview.hy.com.myrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import recyclerview.hy.com.myrecyclerview.R;

/**
 * Created by Administrator on 2016/2/18.
 */
public class FootViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //布局类型
    private static final int ITEM_VIEW = 0;
    private static final int FOOT_VIEW = 1;
    //上拉加载更多
    public static final int PULL_UP_LOAD_MORE = 0;
    //正在加载
    public static final int LOADING_MORE = 1;
    //上拉加载更多状态-默认为0
    private int load_more_status=0;
    private List<String> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    public FootViewAdapter(List<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOT_VIEW) {
            View view = mInflater.inflate(R.layout.activity_footview, parent, false);
            FootViewHolder footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        } else if (viewType == ITEM_VIEW) {
            View view = mInflater.inflate(R.layout.linear_item, parent, false);
            ItemViewHolder itemViewHolder = new ItemViewHolder(view);
            return itemViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
          ((ItemViewHolder) holder).itemTextView.setText(mData.get(position));
        }else if (holder instanceof FootViewHolder){
            FootViewHolder footViewHolder= (FootViewHolder) holder;
            switch (load_more_status){
                case PULL_UP_LOAD_MORE:
                    footViewHolder.footTextView.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footViewHolder.footTextView.setText("正在加载更多数据...");
                    break;

            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return FOOT_VIEW;
        } else {
            return ITEM_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTextView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            itemTextView= (TextView) itemView.findViewById(R.id.linear_textView);
        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {

        public TextView footTextView;
        public FootViewHolder(View itemView) {
            super(itemView);
            footTextView= (TextView) itemView.findViewById(R.id.footView_TextView);
        }
    }

    /***
     * 下拉刷新添加数据
     * */
    public void addItem(List<String> mNewData){
        mNewData.addAll(mData);
        mData.removeAll(mData);
        mData.addAll(mNewData);
        notifyDataSetChanged();

    }
    /**
     * 上拉加载数据
     * */
    public void addMoreItem(List<String> mNewMoreData){
        mData.addAll(mNewMoreData);
        notifyDataSetChanged();

    }

    public void changeMoreStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();
    }
}

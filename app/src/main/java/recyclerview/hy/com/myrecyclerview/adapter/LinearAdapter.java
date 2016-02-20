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
 * Created by Administrator on 2016/2/16.
 */
public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.ViewHolder> {
    private List<String> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;


    public LinearAdapter(Context mContext, List<String> mData) {
        this.mData = mData;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.linear_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.linearTextView.setText(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //回调
    public interface OnItemClickListener {
        //点击回调
        void OnItemClick(View view, int position);

        //长按回调
        void OnItemLongClick(View view, int position);
    }

    //设置回调
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView linearTextView;
        private View linearItem;

        public ViewHolder(View itemView) {
            super(itemView);
            linearTextView = (TextView) itemView.findViewById(R.id.linear_textView);
            linearItem=itemView.findViewById(R.id.linear_Item);
            linearItem.setOnLongClickListener(this);
            linearItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.OnItemClick(v, getPosition());
            }

        }

        @Override
        public boolean onLongClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.OnItemLongClick(v, getPosition());
            }
            return true;
        }
    }

    /**
     * 添加指定位置的元素
     */
    public void addRecycler(int position, String value) {
        if (position > mData.size()) {
            position = mData.size();
        }
        if (position < 0) {
            position = 0;
        }
        //添加数据
        mData.add(position, value);
        //使用notifyItemInserted/notifyItemRemoved会有动画效果
        //而使用notifyDataSetChanged()则没有

//      notifyDataSetChanged();
        notifyItemInserted(position);

    }

    /**
     * 移除指定位置元素
     */
    public String removeRecycler(int position) {
        if (position > mData.size() - 1) {
            return null;
        }
        String value = mData.remove(position);
        notifyItemRemoved(position);
//      notifyDataSetChanged();
        return value;
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
}

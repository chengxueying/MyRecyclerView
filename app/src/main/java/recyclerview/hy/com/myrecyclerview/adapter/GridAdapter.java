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
 * Created by Ying on 2016/2/16.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private List<String> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public GridAdapter(List<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.grid_TextView.setText(mData.get(position));

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


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView grid_TextView;
        public ViewHolder(View itemView) {
            super(itemView);
            grid_TextView= (TextView) itemView.findViewById(R.id.grid_textView);
            grid_TextView.setOnClickListener(this);
            grid_TextView.setOnLongClickListener(this);
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
}

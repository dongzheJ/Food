package com.example.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<User> mDatas;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, String name, String description);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public MyAdapter(Context context, List<User> datas){
        this. mContext = context;
        this. mDatas = datas;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv.setText(mDatas.get(position).getName());
        holder.bind(onItemClickListener);
        holder.response(onItemLongClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(
                        R.layout.item_recyclerview,parent,false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv =(TextView)itemView.findViewById(R.id.item_text);
        }

        public void bind(final OnItemClickListener onItemClickListener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getAdapterPosition(),mDatas.get(getAdapterPosition()).getName(),
                                mDatas.get(getAdapterPosition()).getPhone());
                    }
                }
            });
        }

        public void response(final OnItemLongClickListener onItemLongClickListener){
            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}

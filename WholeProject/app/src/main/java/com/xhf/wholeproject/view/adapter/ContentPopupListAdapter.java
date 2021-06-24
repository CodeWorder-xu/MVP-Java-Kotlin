package com.xhf.wholeproject.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.xhf.wholeproject.R;

/***
 *Date：21-6-16
 *
 *author:Xu.Mr
 *
 *content:点击电子书的适配器
 */
public class ContentPopupListAdapter extends RecyclerView.Adapter<ContentPopupListAdapter.ViewHolder> {
    private Context context;


    public ContentPopupListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ContentPopupListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_popuplist, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContentPopupListAdapter.ViewHolder holder, int position) {
        holder.cl_view.setOnClickListener(v -> {
            onClickListener.setOnClickListener(position);
        });
    }

    public OnClickListener onClickListener;

    public interface OnClickListener {
        void setOnClickListener(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout cl_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cl_view = itemView.findViewById(R.id.cl_view);
        }
    }
}
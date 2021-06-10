package com.xhf.wholeproject.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.xhf.wholeproject.R;

/***
 *Date：6/8/21
 *
 *author:Xu.Mr
 *
 *content:
 */
public class HomeFocusListAdapter extends RecyclerView.Adapter<HomeFocusListAdapter.ViewHolder> {

    private Context context;
    private String[] list;

    public HomeFocusListAdapter(Context context, String[] list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_focuslist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_moudle.setText(list[position]);
        holder.tv_moudle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    holder.tv_moudle.setScaleX(1.02f);
                } else {
                    holder.tv_moudle.setScaleX(1.0f);
                }
            }
        });
        setItemFocus(holder.itemView);

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_moudle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_moudle = itemView.findViewById(R.id.tv_moudle);

        }
    }

    public interface OnSetFocusListener {
        void addFocusListener();
    }

    public void setOnSetFocusListener(OnSetFocusListener onSetFocusListener) {
        this.onSetFocusListener = onSetFocusListener;
    }

    public OnSetFocusListener onSetFocusListener;

    public void setItemFocus(View view) {
view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        onSetFocusListener.addFocusListener();

    }
});

    }
}
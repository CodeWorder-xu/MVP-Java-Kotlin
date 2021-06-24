package com.xhf.wholeproject.myview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BasePopupWindow;
import com.xhf.wholeproject.model.entity.bean.LabelBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/1/7.
 */

public class LabelPopupView extends BasePopupWindow {
    private int mBookId;
    private RecyclerView mRecyclerView;
    private LinearLayout mLinearLayout;
    private FloatingActionButton mClearFab;
    private OnLabelSelectedListener mListener;


    @Override
    protected View createConvertView() {
        return LayoutInflater.from(mContext)
                .inflate(R.layout.popup_bookmark, null);

    }

    public interface OnLabelSelectedListener {
        void OnLabelClicked(LabelBean label);
    }

    public void setOnLabelClicked(OnLabelSelectedListener listener) {
        mListener = listener;
    }


    public LabelPopupView(Context context, int bookId) {
        super(context);
        mBookId = bookId;

        mLinearLayout = (LinearLayout) mConvertView.findViewById(R.id.pop_label_linear_layout);
        mClearFab = (FloatingActionButton) mConvertView.findViewById(R.id.pop_label_clear);
        mRecyclerView = (RecyclerView) mConvertView.findViewById(R.id.pop_label_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        updateUI();

        mClearFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(LabelBean.class, "mBookId=?", mBookId + "");
                updateUI();
            }
        });

    }

    //刷新列表
    public void updateUI() {

        List<LabelBean> labelList = new ArrayList<>();

        List<LabelBean> labels = DataSupport.where("mBookId=?", mBookId + "").find(LabelBean.class);
        if (labels.size() > 0) {
            for (int i = labels.size() - 1; i >= 0; i--) {
                labelList.add(labels.get(i));
            }
        }
        mRecyclerView.setAdapter(new LabelAdapter(labelList));

    }


    private class LabelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mDetails;
        private TextView mProgress;
        private TextView mTime;
        private LabelBean mLabel;

        public LabelHolder(View itemView) {
            super(itemView);
            mDetails = (TextView) itemView.findViewById(R.id.label_popup_details);
            mProgress = (TextView) itemView.findViewById(R.id.label_popup_progress);
            mTime = (TextView) itemView.findViewById(R.id.label_popup_time);
            itemView.setOnClickListener(this);
        }

        public void bind(LabelBean label) {
            mLabel = label;
            mDetails.setText(label.getDetails());
            mProgress.setText(label.getProgress());
            mTime.setText(label.getTime());
        }

        @Override
        public void onClick(View v) {
            if (mListener != null)
                mListener.OnLabelClicked(mLabel);

        }


    }

    private class LabelAdapter extends RecyclerView.Adapter<LabelHolder> {
        private List<LabelBean> labelList;

        public LabelAdapter(List<LabelBean> labelList) {
            this.labelList = labelList;
        }


        @Override
        public LabelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.item_bookmark, parent, false);
            return new LabelHolder(view);
        }

        @Override
        public void onBindViewHolder(LabelHolder holder, int position) {
            holder.bind(labelList.get(position));

        }

        @Override
        public int getItemCount() {
            return labelList.size();
        }
    }


    public void setBackgroundColor(int color) {
        mLinearLayout.setBackgroundColor(color);

    }


}

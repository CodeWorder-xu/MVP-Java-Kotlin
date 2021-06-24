package com.xhf.wholeproject.myview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BasePopupWindow;
import com.xhf.wholeproject.model.entity.bean.BookBean;
import com.xhf.wholeproject.utils.ManagerUtil;
import com.xhf.wholeproject.view.adapter.ContentPopupListAdapter;

import java.util.List;


/***
 *Date：21-6-15
 *
 *author:Xu.Mr
 *
 *content:点击的电子书的弹出页面 对电子书的想关设置
 */
public class ContentPopupView extends BasePopupWindow {
    private RecyclerView mRecyclerView;
    private ConstraintLayout mLinearLayout;
    private BookBean mBook;

    private OnContentSelectedListener mListener;

    @Override
    protected View createConvertView() {
        return LayoutInflater.from(mContext)
                .inflate(R.layout.popup_layout, null);
    }


    public interface OnContentSelectedListener {
        void OnContentClicked(int paraIndex);
    }

    public void setOnContentClicked(OnContentSelectedListener listener) {
        mListener = listener;
    }

    public ContentPopupView(Context context, BookBean book) {
        super(context);
        mBook = book;
        mLinearLayout = mConvertView.findViewById(R.id.cl_mypop);
        mRecyclerView = (RecyclerView) mConvertView.findViewById(R.id.rlv_mybooklist);
        mRecyclerView.setLayoutManager(ManagerUtil.getHorizonalManager(context, false));
        mRecyclerView.setAdapter(new ContentPopupListAdapter(context));

    }

    /**
     * private class ContentsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
     * private TextView mTextView;
     * private int mPosition;
     * <p>
     * public ContentsHolder(View itemView) {
     * super(itemView);
     * mTextView = (TextView) itemView;
     * itemView.setOnClickListener(this);
     * }
     * <p>
     * public void bind(String content, int position) {
     * mPosition = position;
     * mTextView.setText(content);
     * }
     *
     * @Override public void onClick(View v) {
     * <p>
     * if (mListener != null)
     * mListener.OnContentClicked(mBook.getContentParaIndexs().get(mPosition));
     * <p>
     * }
     * <p>
     * }
     * <p>
     * private class ContentsAdapter extends RecyclerView.Adapter<ContentsHolder> {
     * private List<String> mBookContents;
     * <p>
     * public ContentsAdapter(List<String> bookContents) {
     * mBookContents = bookContents;
     * }
     * @Override public ContentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     * LayoutInflater inflater = LayoutInflater.from(mContext);
     * View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
     * return new ContentsHolder(view);
     * }
     * @Override public void onBindViewHolder(ContentsHolder holder, int position) {
     * holder.bind(mBookContents.get(position), position);
     * <p>
     * }
     * @Override public int getItemCount() {
     * return mBookContents.size();
     * }
     * }
     **/

    public void setBackgroundColor(int color) {
        mLinearLayout.setBackgroundColor(color);

    }


}

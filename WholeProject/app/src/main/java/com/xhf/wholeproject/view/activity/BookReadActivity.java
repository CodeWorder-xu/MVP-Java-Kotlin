package com.xhf.wholeproject.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseActivity;
import com.xhf.wholeproject.view.fragment.ReadingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 *Date：21-6-15
 *
 *author:Xu.Mr
 *
 *content:电子书阅读
 */
public class BookReadActivity extends BaseActivity {
    public static final String EXTRA_BOOK_ID = "EXTRA_BOOK_ID";
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    public void onSetFragment(int view) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(view);
        if (fragment == null) {
            fragment = onCreatFragment();
            fm.beginTransaction().add(view, fragment).commit();
        }
    }


    public static Intent newIntent(Context context, int bookId) {
        Toast.makeText(context,"bookid******="+bookId,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, BookReadActivity.class);
        intent.putExtra(EXTRA_BOOK_ID, bookId);
        return intent;
    }


    public Fragment onCreatFragment() {

        int bookId = getIntent().getIntExtra(EXTRA_BOOK_ID, 0);
        Toast.makeText(this,"bookid===="+bookId,Toast.LENGTH_SHORT).show();
        return ReadingFragment.newInstance(bookId);
    }


    @Override
    protected void initViewsAndEvents() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,    //全屏
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        onSetFragment(R.id.fragment_container);
        onCreatFragment();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_bookread;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
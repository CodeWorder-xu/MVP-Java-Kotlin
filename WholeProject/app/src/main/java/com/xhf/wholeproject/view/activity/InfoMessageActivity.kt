package com.xhf.wholeproject.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.xhf.wholeproject.R
import com.xhf.wholeproject.base.BaseActivity
import com.xhf.wholeproject.model.entity.res.InfoMessageEntity
import com.xhf.wholeproject.utils.CommonUtils
import com.xhf.wholeproject.viewInterface.InfoMessageView
import kotlinx.android.synthetic.main.activity_infomessage.*

/***
 * Date：21-7-5
 *
 * author:Xu.Mr
 *
 * content:
 */
class InfoMessageActivity : BaseActivity(), InfoMessageView {
    @BindView(R.id.ll_back)
    lateinit var llBack: LinearLayout

    @BindView(R.id.tv_title)
    lateinit var tvTitle: TextView

    @BindView(R.id.tv_title_right)
    lateinit var tvTitleRight: TextView

    @BindView(R.id.title_layout)
    lateinit var titleLayout: RelativeLayout

    @BindView(R.id.iv_portrait)
    lateinit var ivPortrait: ImageView

    @BindView(R.id.tv_nickname)
    lateinit var tvNickname: EditText

    @BindView(R.id.et_name)
    lateinit var etName: EditText

    @BindView(R.id.tv_age)
    lateinit var tvAge: TextView

    @BindView(R.id.tv_gender)
    lateinit var tvGender: TextView

    @BindView(R.id.et_idnum)
    lateinit var etIdnum: EditText

    @BindView(R.id.tv_birthday)
    lateinit var tvBirthday: TextView

    @BindView(R.id.tv_confirm)
    lateinit var tvConfirm: TextView
    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_infomessage
    }

    override fun initViewsAndEvents() {
        super.initViewsAndEvents()
        CommonUtils.onStringWatcher(et_idnum, 18);
        tvTitle?.setText("个人信息")


    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
    }

    override fun onGetValue(queryData: List<InfoMessageEntity>) {
        tvNickname?.setText(queryData.get(0).nickName)

    }

    override fun onSetValue(isSuccess: Boolean, message: String) {
        if (isSuccess) {
            showToast("保存成功," + message)
            finish()
        } else {
            showToast("保存失败," + message)
        }
    }

    @OnClick(R.id.tv_confirm)
    fun onClick(view: View) {

    }

}
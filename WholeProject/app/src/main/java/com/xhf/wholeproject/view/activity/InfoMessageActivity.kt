package com.xhf.wholeproject.view.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.xhf.wholeproject.R
import com.xhf.wholeproject.base.BaseActivity
import com.xhf.wholeproject.model.entity.res.InfoMessageEntity
import com.xhf.wholeproject.presenter.impl.InfoMessagePresenterImpl
import com.xhf.wholeproject.utils.CommonUtils
import com.xhf.wholeproject.viewInterface.InfoMessageView
import kotlinx.android.synthetic.main.activity_infomessage.*
import java.util.*
import java.util.Date;

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

    @BindView(R.id.et_nickname)
    lateinit var etNickname: EditText

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
    lateinit var infoMessagePresenterImpl: InfoMessagePresenterImpl

    //选择器
    lateinit var pvTime: TimePickerView

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_infomessage
    }

    override fun initViewsAndEvents() {
        super.initViewsAndEvents()
        CommonUtils.onStringWatcher(et_idnum, 18);
        tvTitle?.setText("个人信息")
        infoMessagePresenterImpl = InfoMessagePresenterImpl(this, this)
        infoMessagePresenterImpl.onGetInFoMessage()


    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
    }

    override fun onGetValue(queryData: List<InfoMessageEntity>) {
        if (queryData.size != 0) {
            etNickname?.setText(queryData.get(0).nickName)
        }
    }

    override fun onSetValue(isSuccess: Boolean, message: String) {
        if (isSuccess) {
            showToast("修改成功")
        } else {
            showToast("保存失败," + message)
        }
    }

    @OnClick(R.id.tv_confirm, R.id.tv_birthday, R.id.ll_back, R.id.tv_age)
    fun onClick(view: View) {
        when (view.id) {
            R.id.tv_confirm -> {
                var nickName = etNickname.text.toString().trim()
                if (TextUtils.isEmpty(nickName)) {
                    showToast("请输入昵称")
                    return
                }
                var name = etName.text.toString().toString()
                if (TextUtils.isEmpty(name)) {
                    showToast("请输入您的真实姓名")
                    return
                }
                var idNum = etIdnum.text.toString().toString()
                if (TextUtils.isEmpty(idNum) || (idNum.length == 15 || idNum.length == 18)) {
                    showToast("请输入正确的身份证号")
                    return
                }


                lateinit var infomessage: InfoMessageEntity
                infomessage.nickName = nickName



                infoMessagePresenterImpl.onSetInfoMessage(infomessage)

            }

            R.id.ll_back -> {
//                finish()
            }
            R.id.tv_birthday -> {
                //时间选择器
                pvTime = CommonUtils.onTimePickView(this, tv_birthday)
                pvTime.show()

            }
            R.id.tv_age -> {
                // 日期选择器
                val ca = Calendar.getInstance()
                var mYear = ca[Calendar.YEAR]
                var mMonth = ca[Calendar.MONTH]
                var mDay = ca[Calendar.DAY_OF_MONTH]

                val datePickerDialog = DatePickerDialog(
                        this,
                        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                            mYear = year
                            mMonth = month
                            mDay = dayOfMonth
                            val mDate = "${year}/${month + 1}/${dayOfMonth}"
                            // 将选择的日期赋值给TextView
                            tvBirthday.text = mDate
                        },
                        mYear, mMonth, mDay
                )
                datePickerDialog.show()

            }

        }
    }


}
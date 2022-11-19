package com.cniao5.mine

import android.graphics.Color
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.ToastUtils
import com.cniao5.common.base.BaseActivity
import com.cniao5.mine.databinding.ActivityMineBinding
import com.cniao5.mine.widget.ItemSettingsBean

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:13
 * @Description:   调试用的 mine 模块入口Activity
 */
class MineActivity : BaseActivity<ActivityMineBinding>() {

    override fun getLayoutRes() = R.layout.activity_mine

    override fun initView() {
        super.initView()
        mBinding.apply {
            val ib = ItemSettingsBean(iconRes = R.drawable.ic_shoping, title = "学习卡")

            val obBean = ObservableField(ib)
            bean = obBean

            ib.title = "你的学习卡"
            ib.titleColor = Color.RED

            ib.arrowColor = R.color.colorPrimary

            ib.iconRes = "https://www.easyicon.net/api/resizeApi.php?id=1283371&size=96"
//            isvCard.setIcon("https://www.easyicon.net/api/resizeApi.php?id=1283371&size=96")

            isvCard.onClickArrow {
                ToastUtils.showShort("点击了Arrow箭头")
            }
            isvCard.setOnClickListener {
                ToastUtils.showShort("点击整个Item")
            }
        }
    }

}
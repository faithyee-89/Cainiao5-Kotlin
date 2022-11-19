package com.cniao5.mine.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Keep
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableField
import com.cniao5.mine.R
import com.cniao5.mine.databinding.VItemSettingsBinding

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:18
  * @Description:   自定义的设置item的组合控件
 */
class ItemSettingsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var itemBean = ItemSettingsBean()
    private val obItemInfo = ObservableField<ItemSettingsBean>(itemBean)

    init {
        //1、管理布局layout
        VItemSettingsBinding.inflate(LayoutInflater.from(context), this, true).apply {
            info = obItemInfo
        }
        setBackgroundColor(Color.WHITE)

        // region  2、读取配置属性
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemSettingsView).apply {
            //icon 设置
            itemBean.iconRes =
                getResourceId(R.styleable.ItemSettingsView_icon, R.drawable.ic_gift_card)
            val iconRGB = getColor(R.styleable.ItemSettingsView_iconColor, 0)
            itemBean.iconColor = iconRGB
            //title设置
            itemBean.title = getString(R.styleable.ItemSettingsView_title) ?: ""
            val titleRGB = getColor(
                R.styleable.ItemSettingsView_titleColor,
                resources.getColor(R.color.colorPrimaryText)
            )
            itemBean.titleColor = titleRGB
            //desc设置
            itemBean.desc = getString(R.styleable.ItemSettingsView_desc) ?: ""
            val descRGB = getColor(R.styleable.ItemSettingsView_descColor, 0)
            itemBean.descColor = descRGB
            //arrow设置
            itemBean.arrowRes =
                getResourceId(R.styleable.ItemSettingsView_arrow, R.drawable.ic_right)
            val arrowRGB = getColor(
                R.styleable.ItemSettingsView_arrowColor,
                resources.getColor(R.color.colorSecondaryText)
            )
            itemBean.arrowColor = arrowRGB
        }

        // 回收 recycle
        attributes.recycle()
        // endregion

    }

    //region 设置资源

    /**
     * 设置整个item的对象info
     */
    fun setInfo(info: ItemSettingsBean) {
        itemBean = info
        obItemInfo.set(info)
    }

    /**
     * 设置title
     */
    fun setTitle(title: String) {
        itemBean.title = title
    }

    /**
     * 设置内容描述
     */
    fun setDesc(desc: String) {
        itemBean.desc = desc
    }

    /**
     * 设置icon图标
     */
    fun setIcon(iconRes: Any) {
        itemBean.iconRes = iconRes
    }

    /**
     * 设置icon图标
     */
    fun setArrow(arrowRes: Any) {
        itemBean.arrowRes = arrowRes
    }


    //endregion

    //region 点击事件

    /**
     * 单独点击图标
     */
    fun onClickIcon(listener: OnClickListener) {
        itemBean.iconListener = listener
    }

    /**
     * 单独点击title
     */
    fun onClickTitle(listener: OnClickListener) {
        itemBean.titleListener = listener
    }

    /**
     * 单独点击desc
     */
    fun onClickDesc(listener: OnClickListener) {
        itemBean.descListener = listener
    }

    /**
     * 单独点击箭头
     */
    fun onClickArrow(listener: OnClickListener) {
        itemBean.arrowListener = listener
    }

    //endregion

    //region 设置颜色

    /**
     * 设置标题title颜色
     */
    fun setIconColor(colorRes: Int) {
        itemBean.iconColor = colorRes
    }

    /**
     * 设置标题title颜色
     */
    fun setTitleColor(colorRes: Int) {
        itemBean.titleColor = colorRes
    }

    /**
     * 设置标题title颜色
     */
    fun setDescColor(colorRes: Int) {
        itemBean.descColor = colorRes
    }

    /**
     * 设置标题title颜色
     */
    fun setArrowColor(colorRes: Int) {
        itemBean.arrowColor = colorRes
    }

    //endregion

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return hasOnClickListeners()
    }

}

@Keep
data class ItemSettingsBean(
    var iconRes: Any = R.drawable.ic_gift_card,
    var title: String = "",
    var desc: String = "",
    var titleColor: Int = R.color.colorPrimaryText,
    var descColor: Int = R.color.colorSecondaryText,
    var iconColor: Int = 0,
    var arrowColor: Int = 0,
    var arrowRes: Any = R.drawable.ic_right
) {
    //item的子View的点击listener
    var iconListener: View.OnClickListener? = null
    var titleListener: View.OnClickListener? = null
    var descListener: View.OnClickListener? = null
    var arrowListener: View.OnClickListener? = null
}
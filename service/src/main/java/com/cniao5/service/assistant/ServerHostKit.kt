package com.cniao5.service.assistant

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.ToastUtils
import com.cniao5.common.utils.*
import com.cniao5.service.R
import com.didichuxing.doraemonkit.kit.AbstractKit

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:18
  * @Description:   用于配置切换不同的接口host，调试工具
 */
class ServerHostKit : AbstractKit() {

    override val icon = R.drawable.icon_server_host

    override val name = R.string.str_server_host_dokit


    override fun onAppInit(context: Context?) {
        //
    }

    private val hostMap = mapOf<String, String>(
        "开发环境Host" to HOST_DEV,
        "QA测试Host" to HOST_QA,
        "线上正式Host" to HOST_PRODUCT,
    )

    private val hosts = hostMap.values.toTypedArray()
    private val names = hostMap.keys.toTypedArray()

    override fun onClick(context: Context?) {
        val pos = hostMap.values.indexOf(getBaseHost()) % hostMap.size
        //弹窗，用于显示很选择不同的host配置
        context ?: return ToastUtils.showShort("~~ context null ~~")
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.str_server_host_dokit))
            .setSingleChoiceItems(
                names, pos
            ) { dialog, which ->
                setBaseHost(hosts[which])
                dialog.dismiss()
            }.show()
    }
}
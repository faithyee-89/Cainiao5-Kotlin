package com.cniao5.home.ui

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cniao5.common.webview.WebActivity
import com.cniao5.home.net.BannerItem
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:12
  * @Description:
 */
class BannerAdapter(private val list: List<BannerItem>) : BannerImageAdapter<BannerItem>(list) {
    override fun onBindView(
        holder: BannerImageHolder?,
        data: BannerItem?,
        position: Int,
        size: Int
    ) {
        //图片加载自己实现
        holder ?: return
        Glide.with(holder.itemView)
            .load(data?.img_url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .into(holder.imageView)
        holder.imageView.setOnClickListener { v ->
            WebActivity.openUrl(v.context, data?.redirect_url ?: "https://m.cniao5.com/")
        }
    }
}
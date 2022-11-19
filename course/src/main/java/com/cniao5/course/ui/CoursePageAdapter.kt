package com.cniao5.course.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cniao5.common.webview.WebActivity
import com.cniao5.course.databinding.ItemCourseBinding
import com.cniao5.course.net.CourseListRsp

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:10
  * @Description:
 */
class CoursePageAdapter : PagingDataAdapter<CourseListRsp.Data, CourseVH>(differCallback) {

    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CourseVH.createVH(parent)

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<CourseListRsp.Data>() {
            override fun areItemsTheSame(
                oldItem: CourseListRsp.Data,
                newItem: CourseListRsp.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CourseListRsp.Data,
                newItem: CourseListRsp.Data
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class CourseVH(private val binding: ItemCourseBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createVH(parent: ViewGroup): CourseVH {
            return CourseVH(
                ItemCourseBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(info: CourseListRsp.Data) {
        binding.info = info
        binding.tvOldPriceItemCourse.paint.flags += Paint.STRIKE_THRU_TEXT_FLAG

        itemView.setOnClickListener { v ->
            WebActivity.openUrl(v.context, "https://m.cniao5.com/course/${info.id}")
        }

        binding.executePendingBindings()
    }
}
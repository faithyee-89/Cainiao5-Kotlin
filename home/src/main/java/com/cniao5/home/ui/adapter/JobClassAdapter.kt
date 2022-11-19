package com.cniao5.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cniao5.common.webview.WebActivity
import com.cniao5.home.databinding.ItemJobClassBinding
import com.cniao5.home.net.JobClassList

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:12
  * @Description:
 */
class JobClassAdapter(private val mList: JobClassList) :
    RecyclerView.Adapter<JobClassAdapter.JobClassVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JobClassVH.create(parent)

    override fun onBindViewHolder(holder: JobClassVH, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size


    class JobClassVH(private val binding: ItemJobClassBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): JobClassVH {
                return JobClassVH(
                    ItemJobClassBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(info: JobClassList.JobClassListItem) {
            binding.url = info.course?.img_url
            itemView.setOnClickListener {
                WebActivity.openUrl(it.context, info.course?.h5site ?: "https://m.cniao5.com")
            }
            binding.executePendingBindings()
        }
    }

}
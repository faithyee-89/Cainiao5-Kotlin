package com.cniao5.study.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cniao5.study.databinding.ItemCourseStudyBinding
import com.cniao5.study.net.StudiedRsp

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:20
 * @Description:
 */
class StudyPageAdapter(private val callback: (StudiedRsp.Data) -> Unit) :
    PagingDataAdapter<StudiedRsp.Data, StudiedVH>(differCallback) {

    override fun onBindViewHolder(holder: StudiedVH, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                callback.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StudiedVH.createVH(parent)

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<StudiedRsp.Data>() {
            override fun areItemsTheSame(
                oldItem: StudiedRsp.Data,
                newItem: StudiedRsp.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StudiedRsp.Data,
                newItem: StudiedRsp.Data
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class StudiedVH(private val binding: ItemCourseStudyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createVH(parent: ViewGroup): StudiedVH {
            return StudiedVH(
                ItemCourseStudyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(info: StudiedRsp.Data) {
        binding.info = info
        binding.npbProgressItemStudy.progress = info.progress.toInt()
        binding.executePendingBindings()
    }
}
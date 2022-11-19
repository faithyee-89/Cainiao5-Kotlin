package com.cniao5.study.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.cniao5.study.databinding.FooterLoadStateBinding

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:20
 * @Description:
 */
//paging3 的 footer的viewHolder
class StudyFootLoadVH(
    private val binding: FooterLoadStateBinding,
    private val loadState: LoadState
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        LogUtils.i("footer中 loadState $loadState")
        binding.executePendingBindings()
    }
}

class StudyLoadStateAdapter : LoadStateAdapter<StudyFootLoadVH>() {
    override fun onBindViewHolder(holder: StudyFootLoadVH, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StudyFootLoadVH {
        return StudyFootLoadVH(
            FooterLoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), loadState
        )
    }

}

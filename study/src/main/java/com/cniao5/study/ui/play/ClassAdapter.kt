package com.cniao5.study.ui.play

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.RecyclerView
import com.cniao5.study.databinding.ItemPlayStudyBinding
import com.cniao5.study.databinding.ItemTitleBinding

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:20
  * @Description:
 */
class ClassAdapter(private val callback: (LessonSection) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mList = mutableListOf<LessonSection>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LessonSection.ITEM_TYPE_TITLE) TitleVH.create(parent) else ClassVH.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val info = mList[position]
        //不同的holder
        when {
            holder is ClassVH -> {
                holder.bind(info)
                holder.itemView.setOnClickListener {
                    callback.invoke(info)
                }
            }
            holder is TitleVH -> {
                holder.bind(info.title ?: "Null")
            }
            else -> error("error holder type")
        }
    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int) = mList[position].viewType

    fun updateList(list: List<LessonSection>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }


    class ClassVH(private val binding: ItemPlayStudyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {

            fun create(parent: ViewGroup) = ClassVH(
                ItemPlayStudyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        fun bind(info: LessonSection) {
            binding.info = info
            binding.executePendingBindings()
        }
    }

    class TitleVH(private val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {

            fun create(parent: ViewGroup) = TitleVH(
                ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        fun bind(title: String) {
            binding.title = title
            binding.executePendingBindings()
        }
    }

}

data class LessonSection(
    val viewType: Int,//类型，分组的标记，0 title 1 content item
    val title: String?,//章节的标题title
    val key: String? = null,//用于lesson 播放信息的key
    val tryIt: Boolean = false,//是否可试看
) {
    val isPlaying = ObservableBoolean(false)

    companion object {
        const val ITEM_TYPE_TITLE = 0
        const val ITEM_TYPE_CONTENT = 1
    }
}
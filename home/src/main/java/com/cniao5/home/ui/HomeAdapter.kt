package com.cniao5.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cniao5.home.HomeFragment
import com.cniao5.home.databinding.ItemHomeBinding
import com.cniao5.home.net.*
import com.cniao5.home.ui.adapter.HomeCourseAdapter
import com.cniao5.home.ui.adapter.JobClassAdapter
import com.cniao5.home.ui.adapter.TeacherAdapter

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:12
  * @Description:
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeVH>() {

    private val mList = mutableListOf<HomeItem>()

    fun updateList(list: List<HomeItem>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeVH.create(parent)

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size

    class HomeVH(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): HomeVH {
                return HomeVH(
                    ItemHomeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(item: HomeItem) {
            binding.title = item.title
            setAdapter(item)
            binding.executePendingBindings()
        }


        private fun setAdapter(item: HomeItem) {
            binding.rvItemHome.adapter = when (item.type) {
                HomeFragment.TYPE_JOB_CLASS -> {
                    binding.rvItemHome.layoutManager = GridLayoutManager(itemView.context, 2)
                    JobClassAdapter(item.obj as JobClassList)
                }
                HomeFragment.TYPE_NEW_CLASS -> {
                    binding.rvItemHome.layoutManager =
                        LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
                    HomeCourseAdapter(item.obj as NewClassList)
                }
                HomeFragment.TYPE_LIMIT_FREE -> {
                    binding.rvItemHome.layoutManager =
                        LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
                    HomeCourseAdapter(item.obj as LimitFreeList)
                }
                HomeFragment.TYPE_ANDROID -> {
                    binding.rvItemHome.layoutManager =
                        LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
                    HomeCourseAdapter(item.obj as AndroidSelection)
                }
                HomeFragment.TYPE_AI -> {
                    binding.rvItemHome.layoutManager =
                        LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
                    HomeCourseAdapter(item.obj as AISelection)
                }
                HomeFragment.TYPE_BD -> {
                    binding.rvItemHome.layoutManager =
                        LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
                    HomeCourseAdapter(item.obj as BDList)
                }
                HomeFragment.TYPE_10W -> {
                    binding.rvItemHome.layoutManager =
                        LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
                    HomeCourseAdapter(item.obj as Suggest10w)
                }
                HomeFragment.TYPE_TEACHER -> {
                    binding.rvItemHome.layoutManager =
                        LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
                    TeacherAdapter(item.obj as PopTeacherList)
                }
                else -> error("error type ${item.type}")
            }
            binding.rvItemHome.adapter
        }
    }

}

data class HomeItem(
    val type: Int,
    val title: String?,
    val obj: Any?
)
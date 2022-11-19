package com.cniao5.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.blankj.utilcode.util.LogUtils
import com.cniao5.app.databinding.ActivityMainBinding
import com.cniao5.common.base.BaseActivity
import com.cniao5.common.widget.BnvMediator
import com.cniao5.course.CourseFragment
import com.cniao5.home.HomeFragment
import com.cniao5.mine.MineContainerFragment
import com.cniao5.study.StudyFragment
import com.mcxiaoke.packer.helper.PackerNg

/**
 * @Description:    App主工程的入口界面
 * @Author:         faithyee
 * @CreateDate:     2022-11-19 17:59
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val fragments = mapOf<Int, ReFragment>(
        INDEX_HOME to { HomeFragment() },
        INDEX_COURSE to { CourseFragment() },
        INDEX_STUDY to { StudyFragment() },
        INDEX_MINE to { MineContainerFragment() }
    )


    override fun getLayoutRes() = R.layout.activity_main

    override fun initConfig() {
        super.initConfig()
        mBinding.run {
            vp2Main.adapter = MainViewPagerAdapter(this@MainActivity, fragments)
            //关联viewPager2和BottomNavigationView
            BnvMediator(bnvMain, vp2Main) { bnv, vp2 ->
                vp2.isUserInputEnabled = false
            }.attach()
        }
        //渠道号获取
        val channelStr = PackerNg.getChannel(this)
        LogUtils.i("当前渠道号$channelStr")
    }

    companion object {
        private const val INDEX_HOME = 0//对应bottomNavigationView的tab的index
        private const val INDEX_COURSE = 1//课程
        private const val INDEX_STUDY = 2//学习中心
        private const val INDEX_MINE = 3//我的
    }
}


/**
 * 首页的viewPager2的适配器
 */
private class MainViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val fragments: Map<Int, ReFragment>
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) =
        fragments[position]?.invoke() ?: throw IndexOutOfBoundsException("ViewPager接收参数index越界啦!")

}
//类型别名定义
typealias ReFragment = () -> Fragment
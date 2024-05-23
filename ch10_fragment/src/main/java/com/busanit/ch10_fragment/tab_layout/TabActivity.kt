package com.busanit.ch10_fragment.tab_layout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.busanit.ch10_fragment.R
import com.busanit.ch10_fragment.databinding.ActivityTabBinding
import com.busanit.ch10_fragment.pager.FragmentOne
import com.busanit.ch10_fragment.pager.FragmentThree
import com.busanit.ch10_fragment.pager.FragmentTwo
import com.google.android.material.tabs.TabLayoutMediator

class TabActivity : AppCompatActivity() {
    lateinit var binding: ActivityTabBinding

    // 표시할 프레그먼트를 담을 리스트
    val fragmentList = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 사용할 프래그먼트
        fragmentList.add(FragmentOne())
        fragmentList.add(FragmentTwo())
        fragmentList.add(FragmentThree())

        // ViewPager에 어댑터 설정
        binding.pager2.adapter = TabAdapter(this, fragmentList)

        // 탭 이름 설정
        val tabName = arrayOf("첫번째 탭", "두번째 탭", "세번째 탭")

        binding.run {
            // TabLayout과 ViewPager를 연동하는 역할
            TabLayoutMediator(tabLayout, pager2 ) { tab, position ->
                tab.text = tabName[position]    // 탭의 이름 설정
            }.attach()
        }

    }

    class TabAdapter(fragmentActivity: FragmentActivity, val fragmentList: MutableList<Fragment>) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }
}
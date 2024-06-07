
package com.busanit.ch13_login.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busanit.ch13_login.R
import com.busanit.ch13_login.activity.TitleActivity
import com.busanit.ch13_login.databinding.FragmentHomeBinding
import com.busanit.ch13_login.databinding.FragmentSettingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
        lateinit var binding: FragmentSettingBinding
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentSettingBinding.inflate(inflater, container, false)
            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 로그아웃 버튼 클릭 -> 로그아웃
        binding.buttonLogout.setOnClickListener { logout() }
    }

    // 로그아웃 함수
    private fun logout() {
        // 1. SharedPreferences 에서 토큰, 사용자 정보 삭제
        val sharedPreferences = activity?.
            getSharedPreferences("app_pref", Context.MODE_PRIVATE)
        sharedPreferences?.edit()
            ?.remove("token")
            ?.remove("username")
            ?.apply()

        // 2, TitleActivity 로 돌려 보내고 메인 화면 종료
        startActivity(Intent(activity, TitleActivity::class.java))
        activity?.finish()
    }

    // Activity의 기능을 가져온 경우 앞에 activity를 붙여주고, null 처리 해주기
}
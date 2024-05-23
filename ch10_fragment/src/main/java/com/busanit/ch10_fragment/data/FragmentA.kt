package com.busanit.ch10_fragment.data

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.busanit.ch10_fragment.R
import com.busanit.ch10_fragment.databinding.FragmentFirstBinding

class FragmentA : Fragment() {
    lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 뷰 바인딩 사용하여 레이아웃 인플레이트
        // XML 연결
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    // View가 생성되고 나서 데이터를 수신하는 생명주기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1) 번들 객체를 통해 전달 받음

        // 메시지를 프래그먼트 인자에서 수신
        val message = arguments?.getString("Key")     // nullable
        // 프래그먼트 뷰 객체 텍스트 변경
        binding.textView.text = message

        // 2) 액티비티를 통한 데이터 수신
        val activityData = (activity as DataActivity).data
        Log.d("mylog", "onViewCreated: $activityData")

        // 3) 프래그먼트 -> 다른 프래그먼트
        binding.textView.setOnClickListener {

            val fragment = FragmentB()
            fragment.arguments = Bundle().apply {
                putString("fragA", "프래그먼트A에서 온 데이터")
            }

            // 부모 프래그먼트 매니저에서 다른 프래그먼트 교체
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_data_container,fragment)
                .commit()
        }
    }



}
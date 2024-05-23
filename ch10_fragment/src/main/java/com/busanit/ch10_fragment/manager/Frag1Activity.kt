package com.busanit.ch10_fragment.manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch10_fragment.R
import com.busanit.ch10_fragment.databinding.ActivityFrag1Binding

class Frag1Activity : AppCompatActivity() {

    lateinit var binding: ActivityFrag1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrag1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프래그먼트 추가
        binding.addButton.setOnClickListener {
            // 관리자를 통해 트랜잭션 시작
            val transaction = supportFragmentManager.beginTransaction()

            transaction.run {
                add(R.id.fragmentContainerView, FirstFragment())        // 트랜잭션 추가
                addToBackStack(null)        // 트랜잭션을 백스택에 추가
                commit()        // 트랜잭션 완료
            }
        }
        binding.replaceButton.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()

            // 컨테이너 리소스 ID를 기준으로 현재 사용하고 있는 프래그먼트를 찾음
            val fragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

            transaction.run {
                // 찾아낸 프래그먼트 제거 -> nullable 타입이므로 null을 해결해야 함.
                if (fragment != null) {
                    remove(fragment)
                }
                commit()        // 트랜잭션 완료
            }
        }

        binding.deleteButton.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.run {
                replace(R.id.fragmentContainerView, SecondFragment())        // 트랜잭션 교체
                addToBackStack(null)        // 트랜잭션을 백스택에 추가
                commit()        // 트랜잭션 완료
            }
        }
    }
}
package com.busanit.ch05_dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch05_dialog.databinding.ActivityToastBinding
import com.busanit.ch05_dialog.databinding.CustomSnackbarBinding
import com.google.android.material.snackbar.Snackbar

class ToastActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    @RequiresApi(30)        // 하위호환성 애너테이션의 경우 오류를 무시하기만 함 30버전 이후부터는 아예 작동 X
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityToastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 기본 토스트
        binding.button1.setOnClickListener {
            // 토스트 메시지 띄우기
            // context, 메시지, 출력되는 시간
            // (Toast.LENGTH_SHORT : 약 3초)
            // (Toast.LENGTH_LONG : 약 5초)
            val toast = Toast.makeText(this, "일반 토스트 메시지입니다. ", Toast.LENGTH_SHORT)
            toast.show()
        }

        // 토스트 객체가 나타나고 사라질 때 콜백 이벤트 추가
        binding.button2.setOnClickListener {
            // API 하위 호환성 고려하기
            // 1. 애너테이션 지정 -> 2. if문을 통해서 실행하기
            // 안드로이드 11버전부터 토스트 콜백 기능 추가
            if (Build.VERSION.SDK_INT >= 30) {    // Build.VERSION_CODES.R = 30
                val toast = Toast.makeText(this, "조금 긴 토스트 메시지입니다. ", Toast.LENGTH_LONG)

                toast.addCallback(object : Toast.Callback() {
                    override fun onToastShown() {
                        Log.d("mylog", "토스트가 등장할 때 작동하는 콜백")
                    }
                    override fun onToastHidden() {
                        Log.d("mylog", "토스트가 사라질 때 작동하는 콜백")
                    }
                })
                toast.show()
                // show()를 해주지 않으면 토스트 메시지가 나타나지 않음
            }
        }

        // 기본 스낵바
        binding.button3.setOnClickListener {
            Snackbar.make(it, "스낵바 메시지입니다.", Snackbar.LENGTH_SHORT).show()
        }

        // 액션 스낵바
        binding.button4.setOnClickListener {
            val snackBar = Snackbar.make(it, "액션을 취합니다. ", Snackbar.LENGTH_LONG)
            // 스낵바 텍스트 색상 지정
            snackBar.setTextColor(Color.YELLOW)
            // 스낵바 배경 색상 지정
            snackBar.setBackgroundTint(Color.RED)
            // 스낵바 애니메이션 지정
            snackBar.animationMode = Snackbar.ANIMATION_MODE_SLIDE

            // 스낵바 Action 설정 - 하나만 설정 가능
            snackBar.setAction("OK") {
                binding.textView.text = "스낵바 ok를 눌렀습니다. "
            }
            snackBar.show()
        }

        // 커스텀 스낵바
        binding.button5.setOnClickListener {
            val snackBar = Snackbar.make(it, "", Snackbar.LENGTH_LONG)

            // 커스텀 스낵바 레이아웃 설정
            val snackBarBinding = CustomSnackbarBinding.inflate(layoutInflater)

            val snackbarLayout = snackBar.view as Snackbar.SnackbarLayout
            snackbarLayout.addView(snackBarBinding.root)

            snackBarBinding.actionBtn1.setOnClickListener {
                binding.textView.text = "액션1 클릭"
            }

            snackBarBinding.actionBtn2.setOnClickListener {
                binding.textView.text = "액션2 클릭"
            }
            snackBar.show()
        }
    }
}
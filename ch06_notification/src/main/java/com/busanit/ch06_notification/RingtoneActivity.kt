package com.busanit.ch06_notification

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch06_notification.databinding.ActivityRingtonBinding

class RingtoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRingtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 알림 소리 재생 버튼
        binding.button1.setOnClickListener {
            // 기본 알림 소리의 URI를 가져옴
            val uri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            // applicationContext: 애플리케이션의 전역 인스턴스
            // 알람 소리를 가져오는 Ringtone 객체를 생성
            val ringtone = RingtoneManager.getRingtone(applicationContext, uri)

            ringtone.play()     // 알람소리 재생
        }

        //  진동 재생
        binding.button2.setOnClickListener {
            // Vibrator 객체 얻기
            val vibrator = if(Build.VERSION.SDK_INT >= 31) {
                // API 버전 31버전 이상일 때 Vibrator 객체 가져오기
                val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
                        as VibratorManager
                vibratorManager.defaultVibrator     // 기본 진동기 반환
            } else {
                // 31 버전 미만일 때 Vibrator 가져오기
                getSystemService(VIBRATOR_SERVICE) as Vibrator
            }

            if(Build.VERSION.SDK_INT >= 26) {
                // 26버전 이상에서는 진동 울리는 시간 패턴을 다양하게 조정할 수 있음
                // 매개변수 1: 진동 시간 패턴
                // 매개변수 2: 진동 세기 패턴
                // 매개변수 3: 진동 여부(횟수)
                vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(500,
                    1000, 500, 2000), intArrayOf(0, 50, 0, 200), -1))
            } else {
                vibrator.vibrate(500)   // 500 밀리초 동안 진동
            }
        }


    }
}
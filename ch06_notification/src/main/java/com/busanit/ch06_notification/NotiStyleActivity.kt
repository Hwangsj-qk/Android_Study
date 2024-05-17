package com.busanit.ch06_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch06_notification.databinding.ActivityNotiStyleBinding
import kotlin.concurrent.thread

class NotiStyleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityNotiStyleBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noti_style)

        // 채널 생성
        createNotificationChannel("ch3", "채널3")

        binding.button1.setOnClickListener {
            // 빌더 설정
            val builder = getNotificationBuilder("ch3")

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as
                    NotificationManager

            builder.setSmallIcon(android.R.drawable.ic_notification_overlay).setContentTitle("프로그레스 바")
            // 진행상태 설정(최대진행값, 현재진행상태, 진행률의 불확정상태 여부)
                .setProgress(100, 0, false)

            // 새로운 스레드에서 100 밀리초마다 진행상태 업데이트
            thread {
                for(i in 1..100) {
                    builder.setProgress(100, i, false)
                    notificationManager.notify(44, builder.build())

                    SystemClock.sleep(100)
                }
            }

            notificationManager.notify(44, builder.build())
        }

    }

    // 알림 Builder 객체를 생성하는 메소드
    fun createNotificationChannel(id:String, name:String) {
        // 26버전 이상일 때만 채널 생성
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // id: 채널 ID - 코드에서 채널을 관리하기 위한 이름
            // name : 채널 이름 - 사용자에게 노출시킬 이름
            val importance = NotificationManager.IMPORTANCE_LOW     // 중요도
            val desc = "채널에 대한 설명"

            val channel =
                NotificationChannel(id, name, importance).apply {
                    description = desc
                    enableLights(true)      // 단말기 LED 램프가 있다면 사용
                    lightColor = Color.RED          // LED 램프의 색상 설정
                    enableVibration(true)   // 진동 사용 여부 설정
                    vibrationPattern = longArrayOf(100,200,300,400)     // 진동ㅇ 패턴
                }

            // 시스템 서비스에서 알림 관리자 객체 가져오기
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            // 아림 관리자에서 채널 등록
            manager.createNotificationChannel(channel)
        } else {
            // 26버전 이하에는 채널 필요 X
        }
    }

    fun getNotificationBuilder(id:String): NotificationCompat.Builder {
        // 26버전 이상일 때는 Builder에 채널 ID 추가
        lateinit var builder: NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = NotificationCompat.Builder(this, id)
        } else {
            builder = NotificationCompat.Builder(this)
        }
        return builder
    }
}
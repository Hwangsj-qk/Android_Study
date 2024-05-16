package com.busanit.ch06_notification

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch06_notification.databinding.ActivityNotiBinding

class NotiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNotiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 권한 요청을 위한 Launcher 등록
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {isGranted ->
            if(isGranted) {
                // 권한이 허용되었으면 알람
                notification()
            }else {
                // 권한이 거부되었으면 토스트 메시지 출력
                Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 버트 클릭 리스너
        binding.button.setOnClickListener {
            // 알림 권한이 있는지 체크
            if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용된 경우  알림 표시
                notification()
            } else {
                // 권한이 허용되지 않은 경우 권한 허용을 요청
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    fun notification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder : NotificationCompat.Builder

        if(Build.VERSION.SDK_INT >= 26) {
            //API 26 이상에서는 Channel을 설정해야 함.
            val channel =
                NotificationChannel("channel 1", "1번 채널", NotificationManager.IMPORTANCE_LOW)
            channel.description = "1번 채널입니다. "      // 채널 설명 설정
            // 알림 관리자에서 생성한 객체 채널 등록(생성)
            notificationManager.createNotificationChannel(channel)
            // 채널 ID를 사용해서 빌더 생성
            builder = NotificationCompat.Builder(this, "channel 1")
        } else {
            // 26버전 이하에서는 채널 없이 빌더 생성
            builder = NotificationCompat.Builder(this)
        }
        builder.apply {
            setSmallIcon(android.R.drawable.ic_notification_overlay)    // 알림에는 아이콘이 있어야 함
            setContentTitle("알림 제목")
            setContentText("알림 내용")
            setWhen(System.currentTimeMillis()) // 알림 발생 시간 설정
        }
        // 알림을 표시
        notificationManager.notify(11, builder.build())
    }
}
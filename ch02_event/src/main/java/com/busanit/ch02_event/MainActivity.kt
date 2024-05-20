package com.busanit.ch02_event

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val TAG = "mylog"
    // 설정한 로그만 보기 위해서 TAG 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 터치 이벤트
    // ACTION_DOWN(터치를 누른 순간), ACTION_UP(터치를 뗀 순간), ACTION_MOVE(누리고 이동하는 수간)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "(${event.x}, ${event.y}) 터치 다운 이벤트가 발생했습니다. ")
                // 이벤트(클리된) 위치를 x좌표, y좌표로 표현하여 출력
                // Log.d -> 디버그 -> 중간 과정 확인 가능
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "터치 업 이벤트가 발생했습니다. ")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "터치 이동 이벤트가 발생했습니다. ")
            }
        }
        return super.onTouchEvent(event)
    }

    // 키 이벤트
    // 누르는 순간 이벤트
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode) {
            KeyEvent.KEYCODE_0 -> Log.d(TAG, "0 키가 눌렸습니다. ")
            KeyEvent.KEYCODE_A -> Log.d(TAG, "A 키가 눌렸습니다. ")
            KeyEvent.KEYCODE_BACK -> Log.d(TAG, "뒤로가기 버튼 눌렸습니다. ")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d(TAG, "볼륨 업 버튼 눌렸습니다. ")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d(TAG, "볼륨 다운 버튼 눌렸습니다. ")
        }
        return super.onKeyDown(keyCode, event)
    }

    // 떼는 순간 이벤트
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }

    // 길게 누르는 순간 이벤트
    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode) {
            KeyEvent.KEYCODE_A -> Log.d(TAG, "볼륨업버튼을 길게 눌렸습니다. ")
        }
        return super.onKeyLongPress(keyCode, event)
    }
}
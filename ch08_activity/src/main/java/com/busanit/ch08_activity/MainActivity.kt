package com.busanit.ch08_activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch08_activity.databinding.ActivityMainBinding
import com.busanit.ch08_activity.intent.Intent1Activity
import com.busanit.ch08_activity.intent.Intent2Activity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding       // 지연 초기화 바인딩 객체 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 명시적 인텐트 사용
        binding.run {
            button1.setOnClickListener {
                // 첫번째 매개변수: Context, 두번째 매개변수: Activity 클래스 리플렉션
                val intent = Intent(this@MainActivity, Intent1Activity::class.java)

                // Context에서 액티비티를 시작
                startActivity(intent)
            }

            // 암시적 인텐트 사용1
            button2.setOnClickListener {
                // 첫번째 매개변수: Action, 두번째 매개변수 URI
                val uri = Uri.parse("http://naver.com")
                val intent = Intent(Intent.ACTION_VIEW, uri)

                startActivity(intent)
                // URI 브라우저 열기
            }

            // 암시적 인텐트 사용2 (특정 전화버로 전화를 거는 암시적 인텐트 사용)
            button3.setOnClickListener {
                val phoneNumber = "tel:01012345678"
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
                startActivity(intent)
            }

            button4.setOnClickListener {
                // 암시적 인텐트 사용3 (문자 메시지를 보내는 암시적 인텐트)
                val phoneNumber = "sms:01012345678"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(phoneNumber))
                intent.putExtra("sms_body", "hello! Message~")
                startActivity(intent)
            }

            button5.setOnClickListener {
                // 인텐트로 공유 기능 사용
                // 인텐트 객체 생성하고 apply 스콥 함수 속성 설정
                val intent = Intent().apply {
                    // Action 설정 : 데이터를 다른 앱으로 보내는 액션
                    action = Intent.ACTION_SEND
                    // 인텐트에 데이터를 추가
                    putExtra(Intent.EXTRA_TEXT, "공유 메시지 텍스트")
                    type = "text/plain"         // 데이터 타입 설정
                }
                //createChooser 메서드 : 사용자에게 앱 선택 창을 표시하는 새로운 인텐트
                // 첫번째 매개변수 공유할 인텐트, 두번째 인자는 제목
                val shareIntent = Intent.createChooser(intent, null)
                startActivity(shareIntent)
            }

            // 명시적 인텐트에 데이터를 담아 보내기
            button6.setOnClickListener {
                val intent = Intent(this@MainActivity, Intent1Activity::class.java)

                intent.putExtra("Extra1", "보내는 문자열")
                intent.putExtra("Extra2", 100)

                startActivity(intent)
            }

            button7.setOnClickListener {
                val intent = Intent(this@MainActivity, Intent2Activity::class.java).apply {
                    putExtra("extra_msg", "Hello")
                }

                // 해당 액티비티를 시작하고 결과를 요청
                // 두번재 매개변수 : 요청코드
                startActivityForResult(intent, 10)
            }

            // Contract 객체 생성: 다른 Activity에 갔다 올 경우
            val contract = ActivityResultContracts.StartActivityForResult()

            // Launcher 객체를 생성(contract, callback 추가)
            val launcher = registerForActivityResult(contract) {
                // Callback 함수 : 돌아왔을 때 코드 구현
                Toast.makeText(this@MainActivity, "다른 액티비티 갔다가 돌아왔습니다. ", Toast.LENGTH_SHORT).show()

                // it : ActivityResult 객체
                // it.resultCode : 결과 코드
                // it.data : 결과 데이터
                if (it.resultCode == RESULT_OK) {
                    val result = it.data?.getStringExtra("result_msg")

                    textView.text = result
                }
            }

            // Result API 사용
            button8.setOnClickListener {
                // 인텐트 객체 생성
                val intent = Intent(this@MainActivity, Intent2Activity::class.java)
                intent.putExtra("extra_msg", "Result API에서 보낸 메시지")

                // ActivityResultLauncher 시작
                launcher.launch(intent)
            }
        }
    }

    // 액티비티의 결과가 돌아왔을 때 수행되는 메소드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // requestCode : 요청한 요청코드
        // resultCode : 결과 코드
        // data :결과로 설정된 Intent 객체

        // 요청코드와 결과코드가 맞을 때
        if(requestCode == 10 && resultCode == RESULT_OK) {
            // 인텐트 객체에서 데이터를 가져옴
            val result = data?.getStringExtra("result_msg")         // null 허용(null을 허용하지 않으면 컴파일 에러)
            binding.textView.text = result
        }
    }
}
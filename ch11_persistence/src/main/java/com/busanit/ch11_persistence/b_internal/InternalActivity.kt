package com.busanit.ch11_persistence.b_internal

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.InputDeviceCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch11_persistence.R
import com.busanit.ch11_persistence.databinding.ActivityInternalBinding
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.lang.StringBuilder

class InternalActivity : AppCompatActivity() {
    lateinit var binding: ActivityInternalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInternalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 내부 저장소 (Internal Storage)
        // 앱의 내부 저장소에 파일 형태로 데이터 저장
        // 앱 전용 데이터 저장, 다른 앱에서는 접근이 불가
        // 사용자의 데이터 파일, 캐시 등

        // 내부저장소에 사용된 파일은 사용자가 앱 정보에서 `저장소 지우기`로 삭제 가능

        val fileName = "internal_storage.txt"       // 저장할 파일 이름

        binding.run {
            // 1. 파일 저장
            buttonSave.setOnClickListener {
                val inputData = editTextName.text.toString() // 입력된 데이터

                // 출력스트림을 열고, 바이트 배열로 쓰고, 스트림 종료
                val fileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
                fileOutputStream.write(inputData.toByteArray())
                fileOutputStream.close()
            }

            // 2. 파일 읽기
            buttonLoad.setOnClickListener {
                try {
                    val fileInputStream = openFileInput(fileName)       // 입력 스트림 열기
                    val isr = InputStreamReader(fileInputStream)        // 문자열 읽기
                    val br = BufferedReader(isr)                        // 버퍼 리더
                    val sb = StringBuilder()            // 문자열을 담을 문자열 빌더
                    var text: String?                   // 한 줄 읽을 문자열
                    while (br.readLine().also {
                            text = it
                        } != null) {
                        // 파일을 끝까지 한줄씩 읽고,
                        sb.append(text)     // 빌더에 추가
                    }

                    // 리소스 정리
                    br.close()
                    isr.close()
                    fileInputStream.close()

                    // 빌더에 담은 문자열 반환
                    val readData = sb.toString()

                    // 뷰에 표시
                    textView.text = readData
                }catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            // 3. 파일 삭제하기
            buttonClear.setOnClickListener {
              // 1. 안드로이드 Context 메서드 사용
                deleteFile(fileName)        // context에서 파일 삭제

                // 2. File 클래스와 경로 사용
                try {
                    val fileStreamPath = getFileStreamPath(fileName)
                    // 경로 확인
                    Log.d("mylog", "onCreate: ${fileStreamPath.toString()}")

                    // 파일 경로가 존재하면 삭제
                    if (fileStreamPath != null) {
                        fileStreamPath.delete()
                    }
                }catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
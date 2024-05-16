package com.busanit.ch05_dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.speech.AlternativeSpan
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch05_dialog.databinding.ActivityDialogBinding
import com.busanit.ch05_dialog.databinding.ActivityToastBinding
import java.util.Calendar

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityDialogBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 경고 다이얼로그
        binding.button1.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.apply {
                setTitle("경고")                      // 타이틀
                setMessage("작업을 수행하시겠습니까?")     // 메시지
                setIcon(R.mipmap.ic_launcher)           // 아이콘

                // 사용자 응답별 수행할 작업(긍정, 부정)
                setPositiveButton("예") { dialog, which ->
                    binding.textView.text = "예, 버튼을 눌렀습니다. "
                }
                setNegativeButton("아니오") {dialog, which ->
                    binding.textView.text = "아니오, 버튼을 눌렀습니다. "
                }
            }
            builder.show()
        }

        // DatePickerDialog
        binding.button2.setOnClickListener {

            // 사용자에게 기본값으로 보여줄 연/월/일
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // 사용자가 날짜를 선택했을 때 수행할 작업
            val listener = object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int)
                {
                    // 매개변수 : 사용자로부터 입력받은 연, 월, 일
                    binding.textView.text = "${year}년 ${month}월 ${dayOfMonth}일"
                }
            }
            // 다이얼로그 객체 생성, 띄우기
            DatePickerDialog(this, listener, year, month, day)
        }

        // TimePickerDialog
        binding.button3.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // 다이얼로그 객체 생성, (이벤트 리스너), 띄우기
           TimePickerDialog(this, {_, hour, minute ->
               binding.textView.text = "선택된 시간 $hour : $minute"
           }, hour, minute, true).show()
        }

        val items = arrayOf<String>("사과", "바나나", "당근", "수박", "복숭아")

        // 목록 출력(AlertDialog.Builder를 사용해서 만듦)
        binding.button4.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("항목 출력")
                setItems(items, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        binding.textView.text = "선택한 과일: ${items[which]}"
                    }
                })
                setPositiveButton("확인", null)
                show()
            }
        }

        // 체크 박스 (여러 항목 선택 가능)
        binding.button5.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("체크박스로 선택")
                setMultiChoiceItems(items,      // 나타낼 아이템 목록

                // 각 아이템의 선택 여부
                    booleanArrayOf(false, true, false, true, false),
                    // 선택시 수행할 작업
                    object : DialogInterface.OnMultiChoiceClickListener {
                        override fun onClick(
                            dialog: DialogInterface?,
                            which: Int,                 // 아이템의 인덱스
                            isChecked: Boolean          // 선택여부
                        ) {
                            binding.textView.text = "${items[which]}가 ${
                                if(isChecked) "선택되었습니다. "
                                else "선택해제 되었습니다. "
                            }"
                        }
                    })
                setPositiveButton("확인", null)
                show()
            }
        }

        //binding.b

    }
}
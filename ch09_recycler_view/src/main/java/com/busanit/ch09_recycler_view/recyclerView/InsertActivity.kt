package com.busanit.ch09_recycler_view.recyclerView

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.R
import com.busanit.ch09_recycler_view.databinding.ActivityInsertBinding
import com.busanit.ch09_recycler_view.databinding.StudentItemBinding

class InsertActivity : AppCompatActivity() {

    // 1. 데이터 클래스(모델)을 정의합니다.
    data class Student(var name: String, var age: Int, var grade: Int)

    val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // 2. 레이아웃을 설정합니다.
        super.onCreate(savedInstanceState)

        val binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.run {
            // 어댑터와 레이아웃매니저 설정
            recyclerView.adapter = MyRecyclerAdapter()
            recyclerView.layoutManager = LinearLayoutManager(this@InsertActivity)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    this@InsertActivity,
                    DividerItemDecoration.VERTICAL
                )
            )  // 구분선 추가

            // 추가 버튼이 클릭되었을 때
            addButton.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toInt()
                val grade = editTextGrade.text.toString().toInt()

                // 입력된 값으로 객체 생성
                val student = Student(name, age, grade)

                // 데이터 리스트에 추가
                studentList.add(student)

                // 입력 필드를 초기화
                editTextName.setText("")
                editTextAge.setText("")
                editTextGrade.setText("")

                // 어댑터에 데이터 변경사실을 알림
                recyclerView.adapter?.notifyDataSetChanged() // 전체 데이터셋 변경

                // 특정위치에 데이터 삽입 알림
                // recyclerView.adapter?.notifyItemInserted(studentList.size - 1)

                // 특정위치에 아이템이 제거됨
//                val position = 1 // 위치를 알 경우 (2번째 항목이 지워짐을 알림)
//                recyclerView.adapter?.notifyItemRemoved(position)

            }
        }
    }

    // 3. 뷰 홀더와 어댑터를 정의
    inner class MyRecyclerAdapter() :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        // 뷰 홀더 정의
        inner class MyViewHolder(val itemBinding: StudentItemBinding) :
            RecyclerView.ViewHolder(itemBinding.root) {
            // 각 View(TextView)의 주소값을 담을 변수
            var textViewName: TextView
            var textViewAge: TextView
            var textViewGrade: TextView

            init {
                // 뷰홀더가 생성될 때 뷰 바인딩을 통해 초기화
                textViewName = itemBinding.textViewName
                textViewAge = itemBinding.textViewAge
                textViewGrade = itemBinding.textViewGrade
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            // 뷰 홀더에 item 레이아웃을 인플레이트하여 리턴
            val itemBinding = StudentItemBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(itemBinding)

        }

        // 항목 개수
        override fun getItemCount(): Int = studentList.size

        // 뷰 홀더와 데이터를 바인딩
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            // n번째 데이터에서 구조분해 할당으로 값 추출
            val (name, age, grade) = studentList[position]

            // 뷰홀더에 데이터 바인딩
            val myHolder = (holder as MyViewHolder).run {
                textViewName.text = name
                textViewAge.text = "${age}세"
                textViewGrade.text = "${grade}점"
            }
        }

    }
}
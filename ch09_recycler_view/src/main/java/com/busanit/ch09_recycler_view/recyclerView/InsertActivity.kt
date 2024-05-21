package com.busanit.ch09_recycler_view.recyclerView

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.R
import com.busanit.ch09_recycler_view.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {

    // 1. 데이터 클래스(모델)을 정의합니다.
    data class Student(var name: String, var age: Int, var grade: Int)
    override fun onCreate(savedInstanceState: Bundle?) {
        // 2. 레이아웃을 설정합니다.
        super.onCreate(savedInstanceState)
        val binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    // 3. 뷰 홀더와 어댑터를 정의
//    class MyRecyclerAdapter(val studentList: MutableList<Student>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
//        class MyViewHolder() : RecyclerView.ViewHolder(itemView)
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//            TODO("Not yet implemented")
//        }
//
//        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//            TODO("Not yet implemented")
//        }
//
//        override fun getItemCount(): Int {
//            TODO("Not yet implemented")
//        }
//    }
}
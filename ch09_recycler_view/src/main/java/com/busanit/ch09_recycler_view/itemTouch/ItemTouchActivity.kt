package com.busanit.ch09_recycler_view.itemTouch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.R
import com.busanit.ch09_recycler_view.databinding.ActivityItemBinding
import com.busanit.ch09_recycler_view.databinding.ItemBinding
import com.busanit.ch09_recycler_view.recyclerView.ItemAdapter

class ItemTouchActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 더미 데이터 생성
        val itemList = mutableListOf<Item>()
        for(i in 1..100) {
            itemList.add(Item("항목 $i"))
        }

        // 7. 액티비티에서 어댑터 및 레이아웃 매니저 설정
        binding.recyclerView.adapter = ItemAdapter(itemList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}




package com.busanit.ch10_fragment.tab_recycler

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch10_fragment.R
import com.busanit.ch10_fragment.databinding.ActivityTabRecyclerBinding

class TabRecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTabRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
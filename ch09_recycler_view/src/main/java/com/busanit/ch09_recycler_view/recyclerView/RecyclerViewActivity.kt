package com.busanit.ch09_recycler_view.recyclerView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.R
import com.busanit.ch09_recycler_view.databinding.ActivityRecyclerViewBinding
import com.busanit.ch09_recycler_view.databinding.CarItemBinding

class RecyclerViewActivity : AppCompatActivity() {
    data class Car(val name: String, val maker: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 준비
        val carList = mutableListOf<Car>()
        for (i in 1..100) {
            carList.add(Car("자동차$i", "제조사$i"))
        }

        // 어댑터 설정
        binding.recyclerView.adapter = RecyclerViewAdapter(carList)
        // 레이아웃 매니저
        // binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}

class RecyclerViewAdapter(val carList: MutableList<RecyclerViewActivity.Car>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 뷰 홀더 클래스를 정의
    inner class ViewHolder(val binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // 뷰 홀더를 클릭했을 떄 이벤트 리스너 설정
        init {
            binding.root.setOnClickListener {
                val carName = binding.carNameTextView.text.toString()
                val carMaker = binding.carNameTextView.text.toString()
                Log.d("mylog", "$carName, $carMaker 선택")
            }
        }

    }

    // 아이템 뷰를 생성해서 뷰 홀더에 바인딩
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // car_item.xml 레이아웃을 인플레이트
        val binding = CarItemBinding.inflate(
            LayoutInflater.from(parent.context),     // layoutInflater를 부모 Context에서 가져옴
            parent,
            false
        )
        // 뷰홀더 반환
        return ViewHolder(binding)
    }

    // 뷰홀더에 데이터를 바인딩
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val car = carList[position]  // 데이터 목록에서 해당 위치 데이터 가져오기

        val binding = (holder as ViewHolder).binding    // 뷰홀더에서 binding 정보 가져오기

        // 뷰에 데이터 바인딩
        binding.run {
            carImageView.setImageResource(R.mipmap.ic_launcher)
            carNameTextView.text = car.name
            carMakerTextView.text = car.maker
        }
    }

    // 항목의 총 개수 반환
    override fun getItemCount(): Int = carList.size
}
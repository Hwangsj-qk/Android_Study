package com.busanit.ch09_recycler_view.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.databinding.ActivityItemBinding
import com.busanit.ch09_recycler_view.databinding.ItemBinding

class ItemEventActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 더미 데이터 생성
        val itemList = mutableListOf<Item>()
        for (i in 1..100) {
            itemList.add(Item("항목 $i"))
        }

        // 7. 액티비티에서 어댑터 및 레이아웃 매니저 설정
        // 3) 어댑터 매개변수로 이벤트 람다식 추가
        binding.recyclerView.adapter = ItemAdapter(itemList) {
            Toast.makeText(this, "${it.text} 이벤트 실행", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }


}

// 1. 데이터 클래스 생성
data class Item(val text: String)

// 2. 리사이클러뷰 레이아웃 XML 설정
// 3. 아이템 항목 레이아웃 XML 설정

// 4. 어댑터 클래스 작성하기
// 매개변수로 데이터 리스트를 받음 (기타 매개변수 추가 가능)
// RecyclerView.Adapter를 상속받고, 커스텀 뷰홀더를 제네릭 추가
class ItemAdapter(val itemList: MutableList<Item>,
    // 3) 람다식을 매개변수로 하여 이벤트 추가
                  val onItemClick: (Item) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // 5. 뷰 홀더 작성하기
    // 매개변수로 항목의 레이아웃 뷰 바인딩을 삽입
    inner class ItemViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // 1) 뷰 홀더에서 이벤트 발생
        /*      init {
                  // binding.root : 항목 전체를 클릭했을 때 이벤트 발생
                  // binding.특정뷰 : 특정 항목 클릭했을 때 이벤트
                  binding.textView.setOnClickListener {

                      val position = adapterPosition      // 위치 정보 가져오기
                      val context = binding.root.context  // context 가져오기
                      // 항목에 따라 이벤트 발생
                      Toast.makeText(context, "${itemList[position].text} 이벤트 발생", Toast.LENGTH_SHORT).show()
                  }
              }*/
    }

    // 6. 어댑터의 메서드 구현
    // 6-1. onCreateViewHolder : 뷰 홀더를 초기화
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // 6-2. getItemCount : 데이터의 개수
    override fun getItemCount(): Int = itemList.size

    // 6-3. onBindViewHolder : 데이터와 뷰홀더 바인딩
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.run {
            textView.text = itemList[position].text
        }
        // 2) binding 메서드에서 클릭 이벤트

        // holder.itemView : 홀더의 항목 자체를 가리킴
        // holder.binding.특정뷰 : 특정뷰를 가리킴
        /*        holder.binding.textView.setOnClickListener {
                    val context = holder.binding.root.context
                    Toast.makeText(context, "${itemList[position]}", Toast.LENGTH_SHORT).show()
                }*/

        // 3) 외부에서 클릭 리스너 주입
        holder.itemView.setOnClickListener {
            onItemClick(itemList[position])
        }
    }
}
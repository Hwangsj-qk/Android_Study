package com.busanit.ch09_recycler_view.itemTouch

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

// 커스텀 콜백 클래스 ItemTouchHelper.Callback() 상속
class ItemTouchHelperCallback(val adapter: ItemAdapter) : ItemTouchHelper.Callback() {
    // 드래그와 스와이프를 위한 플래그 설정
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // 위 아래 방향을 드래그 플래그 설정
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        // 왼쪽 오른쪽 방향을 스와이프 플래그 설정
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        // 플래그를 리턴
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = viewHolder.adapterPosition   // 이동 시작 위치
        val toPosition = target.adapterPosition // 이동 끝 위치
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition // 스와이프 되는 아이템 위치
        adapter.deleteItem(position)           // 어댑터에서 데이터 삭제 및 알림
    }
}


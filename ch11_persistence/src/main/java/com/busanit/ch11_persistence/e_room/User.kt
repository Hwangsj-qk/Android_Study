package com.busanit.ch11_persistence.e_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity 클래스 : 데이터베이스의 테이블을 나타내는 클래스
// users라는 이름의 테이블
@Entity(tableName = "users")
data class User(
    // 기본키, 자동생성
    @PrimaryKey(autoGenerate = true)    // 자동 생성 (id)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "age")
    val age: Int

    // ,@ColumnInfo(name = "first_name") val firstName: String
    // 프로퍼티의 이름과 컬럼의 이름이 다른 경우
)

// 데이터 클래스의 프로퍼티는 테이블의 컬럼을 가리킨다.
// 테이블의 컬럼명은 @ColumnInfo의 name 속성으로 지정

package com.black.trackash.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExtractDAO {
    @Insert
    fun insert(extract: Extract)

    @Query("select * from extract order by id desc limit 1")
    fun getLast(): LiveData<Extract>

    @Query("select count(*) from extract")
    fun getCount(): Int

    @Update
    fun update(extract: Extract)
}
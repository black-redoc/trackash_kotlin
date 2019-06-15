package com.black.trackash.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExtractDAO {
    @Insert
    suspend fun insert(extract: Extract)

    @Query("select * from extract order by id desc limit 1")
    suspend fun getLast(): LiveData<Extract>

    @Update
    suspend fun update(extract: Extract)
}
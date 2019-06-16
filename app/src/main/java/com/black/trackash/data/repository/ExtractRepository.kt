package com.black.trackash.data.repository

import androidx.lifecycle.LiveData
import com.black.trackash.data.model.Extract

interface ExtractRepository {
    suspend fun getCount(): Int

    suspend fun getLast(): LiveData<Extract>

    suspend fun insert(extract: Extract)

    suspend fun update(extract: Extract)
}
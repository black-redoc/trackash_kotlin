package com.black.trackash.data.repository

import androidx.lifecycle.LiveData
import com.black.trackash.data.model.Extract
import com.black.trackash.data.model.ExtractDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExtractRepositoryImpl(
    val extractDAO: ExtractDAO
): ExtractRepository {
    override suspend fun getCount(): Int {
        return withContext(Dispatchers.IO) {
            return@withContext extractDAO.getCount()
        }
    }

    override suspend fun getLast(): LiveData<Extract> {
        return withContext(Dispatchers.IO) {
            return@withContext extractDAO.getLast()
        }
    }

    override suspend fun insert(extract: Extract) {
        withContext(Dispatchers.IO) {
            extractDAO.insert(extract)
        }
    }

    override suspend fun update(extract: Extract) {
        withContext(Dispatchers.IO) {
            extractDAO.update(extract)
        }
    }
}
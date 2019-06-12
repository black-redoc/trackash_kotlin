package com.black.trackash.data.repository

import androidx.lifecycle.LiveData
import com.black.trackash.data.model.Transaction
import org.threeten.bp.LocalDate

interface TransactionRepository {
    suspend fun getTransactions(startDate: LocalDate): LiveData<out List<Transaction>>
}
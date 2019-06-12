package com.black.trackash.data.repository

import androidx.lifecycle.LiveData
import com.black.trackash.data.model.Transaction
import com.black.trackash.data.model.TransactionDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate

//class TransactionRepositoryImpl(
//    private val transactionDao: TransactionDAO
//) : TransactionRepository {
//    init {
//
//    }
//
//    override suspend fun getTransactions(startDate: LocalDate): LiveData<out List<Transaction>> {
//        return withContext(Dispatchers.IO) {
//            return@withContext transactionDao.getMonthTransactions(startDate)
//        }
//    }
//}
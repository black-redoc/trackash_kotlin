package com.black.trackash.data.repository

import androidx.lifecycle.LiveData
import com.black.trackash.data.model.Transaction
import com.black.trackash.data.model.TransactionDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDAO
) : TransactionRepository {

    override suspend fun getTransactions(startDate: LocalDate): LiveData<out List<Transaction>> {
        return withContext(Dispatchers.IO) {
            return@withContext transactionDao.getMonthTransactions(startDate)
        }
    }

    override suspend fun lastTransactions(startDate: LocalDate): LiveData<out List<Transaction>> {
        return withContext(Dispatchers.IO) {
            return@withContext transactionDao.getLastTransactions(startDate)
        }
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.instert(transaction)
        }
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.update(transaction)
        }
    }

    override suspend fun insertTransactions(transactions: List<Transaction>) {
        withContext(Dispatchers.IO) {
            transactionDao.insert(transactions)
        }
    }
}
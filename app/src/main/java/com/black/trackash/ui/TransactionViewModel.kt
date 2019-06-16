package com.black.trackash.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.black.trackash.data.model.Transaction
import com.black.trackash.data.repository.TransactionRepository
import com.black.trackash.utils.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class TransactionViewModel (
    private val transactionRepository: TransactionRepository
): ViewModel(){
    val transactions by lazyDeferred {
        val now = LocalDate.now(ZoneId.of("UTC-05:00"))
        val start = LocalDate.of(now.year,1,1)
        transactionRepository.getTransactions(start)
    }

    val monthlyTransactions by lazyDeferred {
        val now = LocalDate.now(ZoneId.of("UTC-05:00"))
        val start = LocalDate.of(now.year,now.month,1)
        transactionRepository.getTransactions(start)
    }

    val lastTransactions by lazyDeferred {
        val now = LocalDate.now(ZoneId.of("UTC-05:00"))
        val start = LocalDate.of(now.year,now.month,1)
        transactionRepository.lastTransactions(start)
    }

    fun transactionByMonth(date: LocalDate) = lazyDeferred {
        return@lazyDeferred transactionRepository.getTransactions(date)
    }

    fun insert(transaction: Transaction)  = GlobalScope.launch  (Dispatchers.IO) {
        transactionRepository.insertTransaction(transaction)
    }

    fun insert(transactions: List<Transaction>) = GlobalScope.launch (Dispatchers.IO) {
        transactionRepository.insertTransactions( transactions )
    }

    fun update(transaction: Transaction) = GlobalScope.launch(Dispatchers.IO) {
        transactionRepository.updateTransaction(transaction)
    }
}
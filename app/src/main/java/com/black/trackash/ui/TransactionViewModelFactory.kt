package com.black.trackash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.black.trackash.data.repository.TransactionRepository

class TransactionViewModelFactory(
    private val transactionRepository: TransactionRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(
            transactionRepository
        ) as T
    }
}
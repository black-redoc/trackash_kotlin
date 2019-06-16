package com.black.trackash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.black.trackash.data.repository.ExtractRepository

class ExtractViewModelFactory(
    private val extractRepository: ExtractRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return ExtractViewModel(
            extractRepository
        ) as T
    }
}
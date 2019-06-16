package com.black.trackash.ui

import androidx.lifecycle.ViewModel
import com.black.trackash.data.model.Extract
import com.black.trackash.data.repository.ExtractRepository
import com.black.trackash.utils.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExtractViewModel(
    private val extractRepository: ExtractRepository
): ViewModel() {
    val count by lazyDeferred {
        extractRepository.getCount()
    }

    val last by lazyDeferred {
        extractRepository.getLast()
    }

    fun insert(extract: Extract) = GlobalScope.launch (Dispatchers.IO) {
        extractRepository.insert(extract)
    }

    fun update(extract: Extract) = GlobalScope.launch(Dispatchers.IO) {
        extractRepository.update(extract)
    }
}
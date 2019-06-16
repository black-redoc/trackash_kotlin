package com.black.trackash.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Extract(
    val month: String,
    val total: Double,
    val incomes: Double,
    val expenses: Double,
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null
)
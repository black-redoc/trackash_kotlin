package com.black.trackash.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Extract(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val month: String,
    val total: Double,
    val incomes: Double,
    val expenses: Double
)
package com.black.trackash.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate

const val INITIAL_COUNT = 0L

@Entity
data class Transaction(
    val concept: String,
    val value: Double,
    val type: String,
    val date: LocalDate
) {
    @PrimaryKey val id: Long = INITIAL_COUNT
}
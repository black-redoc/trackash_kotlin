package com.black.trackash.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate


const val INITIAL_COUNT = 1L

@Entity
data class Transaction(
    val concept: String,
    val value: Double,
    val type: String,
    val date: LocalDate
) {
    @PrimaryKey var id: Long = INITIAL_COUNT
        get() = id
        set(value) {
            field = value
        }
}
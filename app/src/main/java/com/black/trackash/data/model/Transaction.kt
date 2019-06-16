package com.black.trackash.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate

@Entity
data class Transaction(
    val concept: String,
    val value: Double,
    val type: String,
    val date: LocalDate,
    @PrimaryKey(autoGenerate = true) var id: Long? = null
) {
    override fun toString(): String {
        return "Transaction(concept='$concept', value=$value, type='$type', date=$date, id=$id)"
    }
}
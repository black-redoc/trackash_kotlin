package com.black.trackash.data.model

import androidx.lifecycle.LiveData
import androidx.room.*
import org.threeten.bp.LocalDate

@Dao
interface TransactionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transactions: List<Transaction>)

    @Query("select * from `transaction` where date(date) >= date(:startDate)")
    fun getMonthTransactions(startDate: LocalDate): LiveData<List<Transaction>>

    @Query("select count(id) from `transaction` where date(date) >= date(:startDate)")
    fun countTransaction(startDate: LocalDate): Int

    @Update
    fun update(transaction: Transaction)

    @Delete
    fun deleteTransaction(transaction: Transaction)
}
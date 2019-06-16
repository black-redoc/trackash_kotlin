package com.black.trackash.data.model

import androidx.lifecycle.LiveData
import androidx.room.*
import org.threeten.bp.LocalDate

@Dao
interface TransactionDAO {
    @Insert
    fun insert(transactions: List<Transaction>)

    @Insert
    fun instert(transaction: Transaction)

    @Query("select * from `transaction` where date(date) >= date(:startDate) and date(date) < date(:endDate) order by id desc")
    fun getMonthTransactions(startDate: LocalDate, endDate: LocalDate): LiveData<List<Transaction>>

    @Query("select * from `transaction` where date(date) >= date(:startDate) and date(date) < date(:endDate) order by id desc limit 3")
    fun getLastTransactions(startDate: LocalDate, endDate: LocalDate): LiveData<List<Transaction>>

    @Query("select count(id) from `transaction` where date(date) >= date(:startDate)")
    fun countTransaction(startDate: LocalDate): Int

    @Update
    fun update(transaction: Transaction)

    @Delete
    fun deleteTransaction(transaction: Transaction)
}
package com.black.trackash.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.black.trackash.data.model.Extract
import com.black.trackash.data.model.ExtractDAO
import com.black.trackash.data.model.Transaction
import com.black.trackash.data.model.TransactionDAO

@Database(
    entities = [Transaction::class, Extract::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateConverter::class)
abstract class TracKashDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDAO
    abstract fun extractDao(): ExtractDAO

    companion object {
        @Volatile private var instance: TracKashDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                TracKashDatabase::class.java, "tracKashContainer.db")
                .build()
    }
}
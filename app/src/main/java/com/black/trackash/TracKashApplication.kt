package com.black.trackash

import android.app.Application
import com.black.trackash.data.db.TracKashDatabase
import com.black.trackash.data.repository.TransactionRepository
import com.black.trackash.data.repository.TransactionRepositoryImpl
import com.black.trackash.ui.TransactionViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TracKashApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@TracKashApplication))

        bind() from singleton { TracKashDatabase(instance()) }
        bind() from singleton { instance<TracKashDatabase>().transactionDao() }
        bind<TransactionRepository>() with singleton { TransactionRepositoryImpl(instance()) }
        bind() from provider { TransactionViewModelFactory(instance()) }
    }
}
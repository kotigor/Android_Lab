package ru.konstantinov.lab4.ui.common

import android.app.Application
import ru.konstantinov.lab4.MainRouter
import ru.konstantinov.lab4.data.db.AppDatabase
import ru.konstantinov.lab4.data.repository.EventReminderRepositoryDb
import ru.konstantinov.lab4.repository.EventReminderRepository

class App : Application() {
    lateinit var mainRouter: MainRouter
    private val appDatabase: AppDatabase by lazy { AppDatabase.getInstance(this) }

    val eventReminderRepository: EventReminderRepository by lazy {
        EventReminderRepositoryDb(appDatabase)
    }

    override fun onCreate() {
        super.onCreate()
    }
}
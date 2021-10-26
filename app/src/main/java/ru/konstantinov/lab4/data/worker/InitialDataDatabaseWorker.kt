package ru.konstantinov.lab4.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.konstantinov.lab4.data.db.AppDatabase
import ru.konstantinov.lab4.data.db.model.EventReminderDb
import java.util.*

class InitialDataDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val list = listOf(
            EventReminderDb(1, "День Рождения", "Мой день рождения", Date(122, 0, 28)),
            EventReminderDb(2, "Новый год", "Купить мандаринов бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла", Date(121, 11, 31)),
            EventReminderDb(3, "День группы", "", Date()),
        )

        try {
            val database = AppDatabase.getInstance(applicationContext)
            database.eventReminderDao().insertAll(list)
            Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}
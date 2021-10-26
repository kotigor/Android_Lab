package ru.konstantinov.lab4.data.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ru.konstantinov.lab4.data.db.dao.EventReminderDao
import ru.konstantinov.lab4.data.db.helpers.DATABASE_NAME
import ru.konstantinov.lab4.data.db.helpers.DateConverter
import ru.konstantinov.lab4.data.db.model.EventReminderDb
import ru.konstantinov.lab4.data.worker.InitialDataDatabaseWorker

@Database(entities = [EventReminderDb::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventReminderDao(): EventReminderDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback( object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<InitialDataDatabaseWorker>()
                            .build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }
}
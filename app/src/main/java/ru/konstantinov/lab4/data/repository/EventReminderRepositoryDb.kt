package ru.konstantinov.lab4.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import ru.konstantinov.lab4.data.db.AppDatabase
import ru.konstantinov.lab4.data.db.model.EventReminderDb
import ru.konstantinov.lab4.data.mapper.mapToDb
import ru.konstantinov.lab4.model.EventReminder
import ru.konstantinov.lab4.repository.EventReminderRepository

class EventReminderRepositoryDb(
    private val database: AppDatabase,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : EventReminderRepository {

    override suspend fun getEventReminderList(): Flow<List<EventReminder>> = withContext(defaultDispatcher) {
        database.eventReminderDao().getAll()
            .map {
                return@map it.map {
                    EventReminder(it.id, it.title, it.desc ?: "", it.dateStart)
                }
            }
    }

    override suspend fun getEventReminder(id: Int): EventReminder? = withContext(defaultDispatcher) {
        val eventDb = database.eventReminderDao().getById(id).firstOrNull()

        eventDb?.run { EventReminder(id, title, desc ?: "", dateStart) }
    }

    override suspend fun removeEventReminder(eventReminder: EventReminder) = withContext(defaultDispatcher) {
        val eventDb = with(eventReminder) {
            EventReminderDb(id, title, desc, dateStart)
        }
        database.eventReminderDao().remove(eventDb)
    }

    override suspend fun saveEventReminder(eventReminder: EventReminder) = withContext(defaultDispatcher){
        database.eventReminderDao().save(eventReminder.mapToDb())
    }
}
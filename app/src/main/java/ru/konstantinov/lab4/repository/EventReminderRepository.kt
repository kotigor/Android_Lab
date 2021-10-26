package ru.konstantinov.lab4.repository

import kotlinx.coroutines.flow.Flow
import ru.konstantinov.lab4.model.EventReminder

interface EventReminderRepository {

    suspend fun getEventReminderList(): Flow<List<EventReminder>>

    suspend fun getEventReminder(id: Int): EventReminder?

    suspend fun removeEventReminder(eventReminder: EventReminder)

    suspend fun saveEventReminder(eventReminder: EventReminder)
}
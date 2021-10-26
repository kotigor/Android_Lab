package ru.konstantinov.lab4.data.mapper

import ru.konstantinov.lab4.data.db.model.EventReminderDb
import ru.konstantinov.lab4.model.EventReminder

fun EventReminder.mapToDb() = with(this) {
    EventReminderDb(id, title, desc, dateStart)
}
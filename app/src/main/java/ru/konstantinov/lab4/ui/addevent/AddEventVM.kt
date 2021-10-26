package ru.konstantinov.lab4.ui.addevent

import android.app.Application
import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.konstantinov.lab4.model.EventReminder
import ru.konstantinov.lab4.ui.common.App
import java.text.SimpleDateFormat
import java.util.*

class AddEventVM(application: Application) : AndroidViewModel(application) {
    private var eventReminder: EventReminder = EventReminder.emptyEventReminder()

    val titleObs = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(value)
            eventReminder = eventReminder.copy(title = value!!)
        }
    }

    val descObs = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(value)
            eventReminder = eventReminder.copy(desc = value!!)
        }
    }

    fun updateDate(date: Date){
        eventReminder = eventReminder.copy(dateStart = date!!)
    }

    val dateString = object : ObservableField<String>() {
        override fun get(): String? {
            return SimpleDateFormat("dd.MM.yy").format(eventReminder.dateStart)
        }
    }

    fun loadData(eventId: Int) {
        viewModelScope.launch {
            val event = getApplication<App>().eventReminderRepository.getEventReminder(eventId)
            eventReminder = event ?: EventReminder.emptyEventReminder()
            eventReminder.run {
                titleObs.set(title)
                descObs.set(desc)
                dateString.set(SimpleDateFormat("dd.MM.yy").format(eventReminder.dateStart))
            }
        }
    }

    fun getDate() : Date{
        return eventReminder.dateStart
    }

    fun saveData() {
        viewModelScope.launch {
            getApplication<App>().eventReminderRepository.saveEventReminder(eventReminder)
            closeView()
        }
    }

    fun removeEvent(){
        viewModelScope.launch {
            getApplication<App>().eventReminderRepository.removeEventReminder(eventReminder)
            closeView()
        }
    }

    fun openAddView(){
        val addEventFragment = AddEventFragment()
        val bundle = Bundle()
        bundle.putInt("ARG_EVENT_ID", eventReminder.id!!)
        addEventFragment.arguments = bundle
        getApplication<App>().mainRouter.changeMainFragment(addEventFragment)
    }

    fun closeView() {
        getApplication<App>().mainRouter.closeLastFragment()
    }

}
package ru.konstantinov.lab4.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.konstantinov.lab4.model.EventReminder
import java.util.*

class EventViewModel : ViewModel() {
    private val eventsReminder: MutableLiveData<List<EventReminder>> = MutableLiveData()

    init{
        loadEventReminder()
    }

    fun getEvents(): LiveData<List<EventReminder>>{
        return eventsReminder
    }

    fun loadEventReminder(){
        val list = listOf(
            EventReminder("День Рождения", "Мой день рождения", Date(122, 0, 28)),
            EventReminder("Новый год", "Купить мандаринов", Date(121, 11, 31))
        )
        eventsReminder.postValue(list)
    }
}
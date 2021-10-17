package ru.konstantinov.lab4.ui.event

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.konstantinov.lab4.model.EventReminder
import ru.konstantinov.lab4.ui.AddEventFragment
import ru.konstantinov.lab4.ui.common.App
import java.util.*

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private val eventsReminder: MutableLiveData<MutableList<EventReminder>> = MutableLiveData()

    init{
        loadEventReminder()
    }

    fun getEvents(): LiveData<MutableList<EventReminder>>{
        return eventsReminder
    }

    fun addEvent(event: EventReminder){
        eventsReminder.value?.add(event)
        eventsReminder.value = eventsReminder.value
    }

    fun showAddEventFragment(){
        getApplication<App>().mainRouter.changeMainFragment(AddEventFragment())
    }

    fun closeAddEventFragment(){
        getApplication<App>().mainRouter.closeLastFragment()
    }

    fun loadEventReminder(){
        val list = mutableListOf(
            EventReminder("День Рождения", "Мой день рождения", Date(122, 0, 28)),
            EventReminder("Новый год", "Купить мандаринов бла бла бла бла бла бла бла бла бла бла", Date(121, 11, 31))
        )
        eventsReminder.postValue(list)
    }
}
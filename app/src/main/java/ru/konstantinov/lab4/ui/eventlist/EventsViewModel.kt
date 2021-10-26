package ru.konstantinov.lab4.ui.eventlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import ru.konstantinov.lab4.ui.addevent.AddEventFragment
import ru.konstantinov.lab4.ui.common.App

class EventsViewModel(application: Application) : AndroidViewModel(application) {
    private val eventsReminder: MutableLiveData<List<EventVM>> = MutableLiveData()

    init{
        loadEventReminder()
    }

    fun getEvents(): LiveData<List<EventVM>>{
        return eventsReminder
    }


    fun showAddEventFragment(){
        getApplication<App>().mainRouter.changeMainFragment(AddEventFragment())
    }

    fun closeAddEventFragment(){
        getApplication<App>().mainRouter.closeLastFragment()
    }

    private fun loadEventReminder(){
        viewModelScope.launch {
            getApplication<App>().eventReminderRepository
                .getEventReminderList()
                .collect{
                    eventsReminder.postValue(it.map{ EventVM(getApplication<App>(), it) })
                }
        }
    }
}
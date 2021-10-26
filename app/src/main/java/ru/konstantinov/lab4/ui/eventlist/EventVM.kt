package ru.konstantinov.lab4.ui.eventlist

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import ru.konstantinov.lab4.model.EventReminder
import ru.konstantinov.lab4.ui.common.App
import ru.konstantinov.lab4.ui.eventdesc.EventDescFragment

class EventVM(application: Application, val eventReminder: EventReminder) : AndroidViewModel(application) {
    fun onClick(){
        val eventDescFragment = EventDescFragment()
        val bundle = Bundle()
        bundle.putInt("ARG_EVENT_ID", eventReminder.id!!)
        eventDescFragment.arguments = bundle
        getApplication<App>().mainRouter.changeMainFragment(eventDescFragment)
    }
}
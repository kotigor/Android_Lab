package ru.konstantinov.lab4.ui

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import ru.konstantinov.lab4.ui.common.App

class ProfileVM(application: Application) : AndroidViewModel(application) {
    fun changeMainFragment(fragment: Fragment){
        getApplication<App>().mainRouter.changeMainFragment(fragment)
    }

}
package ru.konstantinov.lab4.ui.common

import android.app.Application
import ru.konstantinov.lab4.MainRouter

class App : Application() {
    lateinit var mainRouter: MainRouter

    override fun onCreate() {
        super.onCreate()
    }
}
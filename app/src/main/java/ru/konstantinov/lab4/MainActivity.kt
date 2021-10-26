package ru.konstantinov.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.konstantinov.lab4.databinding.ActivityMainBinding
import ru.konstantinov.lab4.ui.eventlist.EventListFragment
import ru.konstantinov.lab4.ui.holidays.HolidaysFragment
import ru.konstantinov.lab4.ui.profile.ProfileFragment
import ru.konstantinov.lab4.ui.common.App

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).mainRouter = MainRouter(supportFragmentManager)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.EventNavigation -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, EventListFragment()).commit()
                    true
                }
                R.id.HolidaysNavigation -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HolidaysFragment()).commit()
                    true
                }
                R.id.ProfileNavigation -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, EventListFragment()).commit()
    }
}
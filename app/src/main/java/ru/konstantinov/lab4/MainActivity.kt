package ru.konstantinov.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ru.konstantinov.lab4.databinding.ActivityMainBinding
import ru.konstantinov.lab4.ui.EventListFragment
import ru.konstantinov.lab4.ui.HolidaysFragment
import ru.konstantinov.lab4.ui.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}
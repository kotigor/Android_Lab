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
        val navController = findNavController(R.id.container)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.EventNavigation, R.id.HolidaysNavigation, R.id.ProfileNavigation))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}
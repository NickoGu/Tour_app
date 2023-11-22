package com.example.tour_app

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tour_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getLongExtra(Constantes.USER_ID_INTENT, -1)
        println(userId)
        val bundle = Bundle()
        if (userId != -1L) {
            bundle.putLong(Constantes.USER_ID_INTENT, userId)
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        //navController.setGraph(R.navigation.mobile_navigation, bundle)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_cart, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener { menuItem ->
            val userIdBundle = bundleOf(Constantes.USER_ID_FRAGMENT_EXTRA to userId)
            when (menuItem.itemId) {
                R.id.navigation_profile -> {
                    navController.navigate(
                        R.id.navigation_profile,
                        userIdBundle
                    )
                }
                R.id.navigation_home -> {
                    navController.navigate(
                        R.id.navigation_home
                    )
                }
                R.id.navigation_cart -> {
                    navController.navigate(
                        R.id.navigation_cart,
                        userIdBundle
                    )
                }
            }
            true
        }
    }
}
package com.example.tour_app

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
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

        val userId = intent.extras?.getLong(Constantes.USER_ID_INTENT)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController.setGraph(
            R.navigation.mobile_navigation,
            bundleOf(Constantes.USER_ID_ARG_HOME to userId)
        )

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_cart, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        val userIdNavArg = NavArgument.Builder().setDefaultValue(userId).build()
        navController.findDestination(R.id.navigation_home)?.apply {
            addArgument(this.arguments.keys.first(), userIdNavArg)
        }
        navController.findDestination(R.id.navigation_cart)?.apply {
            addArgument(this.arguments.keys.first(), userIdNavArg)
        }
        navController.findDestination(R.id.navigation_profile)?.apply {
            addArgument(this.arguments.keys.first(), userIdNavArg)
        }
        navView.setupWithNavController(navController)
    }
}
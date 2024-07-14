package com.example.e_comapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.e_comapplication.databinding.ActivityMainBinding
import com.example.e_comapplication.fragments.DescriptionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//
        val navHostFragmanet = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment


        val navController = navHostFragmanet.navController

        binding?.bottomNav?.setupWithNavController(navController)

    }


}
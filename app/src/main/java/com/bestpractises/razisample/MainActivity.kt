package com.bestpractises.razisample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bestpractises.razisample.databinding.ActivityMainBinding
import com.bestpractises.razisample.ui.movieList.presentation.MovieListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        theme.applyStyle(R.style.Theme_RaziSample, true)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_container, MovieListFragment()).commit()

    }


}
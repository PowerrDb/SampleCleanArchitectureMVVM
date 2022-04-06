package com.bestpractises.razisample.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bestpractises.razisample.MainActivity
import com.trendyol.medusalib.navigator.MultipleStackNavigator

abstract class BaseFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    abstract fun onViewCreatedFirstTime()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }






}
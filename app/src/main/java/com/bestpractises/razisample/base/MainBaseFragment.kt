package com.bestpractises.razisample.base

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.bestpractises.razisample.util.AppUtility.convertDpToPixel

abstract class MainBaseFragment : BaseFragment() {


    private var isLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
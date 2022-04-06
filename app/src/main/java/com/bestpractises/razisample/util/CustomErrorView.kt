package com.bestpractises.razisample.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.bestpractises.razisample.R
import com.bestpractises.razisample.databinding.ViewCustomErrorBinding
import com.bestpractises.razisample.util.extension.gone
import com.bestpractises.razisample.util.extension.invisible
import com.bestpractises.razisample.util.extension.visible

class CustomErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    CardView(context, attrs, defStyleAttr) {
    private lateinit var binding : ViewCustomErrorBinding
    init { // inflate binding and add as view
        binding = ViewCustomErrorBinding.inflate(LayoutInflater.from(context), this, true)
    }
    private var displayLoading: Boolean = true
    private var mBackgroundColor: Int = 0
    private var mElevation = 8.0f
    var isLoadingLiveData = MutableLiveData<Boolean>()

    init {

        View.inflate(context, R.layout.view_custom_error, this)
        cardElevation = 8f
//        cardElevation = 0f
//        radius = 0f

       // bringToFront()

        bringToFront()


        //ViewCompat.setTranslationZ(this, 100f)

        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomErrorView)
            displayLoading = typedArray.getBoolean(
                R.styleable.CustomErrorView_cev_showLoading,
                true
            )
            mBackgroundColor = typedArray.getColor(
                R.styleable.CustomErrorView_cev_backgroundColor, ContextCompat.getColor(
                    context,
                    R.color.background_color
                )
            )


            mElevation = typedArray.getDimension(
                R.styleable.CustomErrorView_cev_elevation,
                8f
            )


            typedArray.recycle()
        }

        isLoadingLiveData.postValue(true)
        if(!displayLoading){
            hideLoading()
        }

        cardElevation = mElevation

        binding.clRoot.setBackgroundColor(mBackgroundColor)



    }


    fun setRetryClickListener(onClickRetryCallback: () -> Unit) {
        binding.btnRetry.setOnClickListener {
            onClickRetryCallback()
        }
    }

    fun showLoading() {
        visible()
        showErrorViews(false)
    }

    fun hideLoading() {
        isLoadingLiveData.postValue(false)
        gone()
    }


    fun showError(errorMessage: String):CustomErrorView {
        visible()
        binding.tvError.text = errorMessage
        showErrorViews(true)
        return  this
    }


    fun hide() {
        isLoadingLiveData.postValue(false)
        gone()
    }

    private fun showErrorViews(show: Boolean){
        if(show){
           binding.prgLoad.gone()
           binding.imgErrorIcon.visible()
           binding.tvError.visible()
           binding.btnRetry.visible()
            isLoadingLiveData.postValue(false)
        }else{
           binding.prgLoad.visible()
           binding.imgErrorIcon.invisible()
           binding.tvError.invisible()
           binding.btnRetry.invisible()
            isLoadingLiveData.postValue(true)
        }
    }


}
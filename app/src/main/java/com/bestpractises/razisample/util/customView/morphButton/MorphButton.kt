package com.bestpractises.razisample.util.customView.morphButton

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.bestpractises.razisample.R
import com.bestpractises.razisample.databinding.MorphButtonBinding


class MorphButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {
    private var binding: MorphButtonBinding


    private var mTextSize = 40f
    private var mText: String? = ""
    private var textColor: Int? = null
    private var mBackgroundColor: Int? = null
    private var fontId = R.font.iran_sans_mobile
    private var progressColor: Int? = null
    private var cornerRadius: Float?

    init {
        binding = MorphButtonBinding.inflate(LayoutInflater.from(context), this, true)


        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.MorphButton)
            mText = typedArray.getString(R.styleable.MorphButton_android_text)
            mTextSize = typedArray.getDimension(R.styleable.MorphButton_android_textSize, 40f)
            cornerRadius = typedArray.getDimension(R.styleable.MorphButton_mb_cornerRadius, 12f)
            textColor = typedArray.getColor(
                R.styleable.MorphButton_android_textColor,
                ContextCompat.getColor(context, R.color.black)
            )
            mBackgroundColor = typedArray.getColor(
                R.styleable.MorphButton_mb_backgroundColor,
                ContextCompat.getColor(context, R.color.white)
            )
            progressColor = typedArray.getColor(
                R.styleable.MorphButton_mb_progressColor,
                ContextCompat.getColor(context, R.color.black)
            )
            fontId = typedArray.getResourceId(
                R.styleable.MorphButton_android_fontFamily,
                R.font.iran_sans_mobile
            )
            typedArray.recycle()
        }

        binding.txtText.typeface = ResourcesCompat.getFont(context, fontId)
        binding.txtText.text = mText ?: ""
        binding.txtText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize)
        textColor?.let { textColor -> binding.txtText.setTextColor(textColor) }
        mBackgroundColor?.let { mBackgroundColor ->
            binding.mcvButton.setCardBackgroundColor(
                mBackgroundColor
            )
        }


        cornerRadius?.let { cornerRadius ->
            binding.mcvButton.radius = cornerRadius
        }


        progressColor?.let { progressColor ->
            binding.prgLoad.indeterminateDrawable.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    progressColor, BlendModeCompat.SRC_ATOP
                )
        }

        binding.mcvButton.isEnabled
        binding.mcvButton.setOnClickListener {
            showLoading()
        }


    }


    fun showLoading() {
        binding.motionLayout.transitionToEnd()
    }

    fun hideLoading() {
        binding.motionLayout.transitionToStart()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.prgLoad.z = 10f
            binding.txtText.z = 10f
        } else {
            binding.mcvButton.cardElevation = 0f
        }


        binding.motionLayout.getConstraintSet(R.id.start)?.let { startConstraintSet ->
            val mcvButtonConstraint = startConstraintSet.getConstraint(R.id.mcvButton)
            mcvButtonConstraint.mCustomConstraints["Radius"]?.setFloatValue(cornerRadius ?: 12f)
        }

        binding.motionLayout.getConstraintSet(R.id.end)?.let { endConstraintSet ->
            val mcvButtonConstraint = endConstraintSet.getConstraint(R.id.mcvButton)
            mcvButtonConstraint.mCustomConstraints["Radius"]?.setFloatValue(binding.mcvButton.height / 2f)
        }
    }
}

//<com.irantrade.iranhen.util.customView.morphButton.MorphButton
//android:layout_width="match_parent"
//android:layout_gravity="center"
//android:id="@+id/morph"
//android:textSize="@dimen/_14sdp"
//android:fontFamily="@font/iran_sans_mobile_bold"
//android:textColor="@color/white"
//android:text="ورود"
//app:mb_cornerRadius="@dimen/_12sdp"
//app:mb_progressColor="@color/white"
//app:mb_backgroundColor="#ff0000"
//
//android:layout_height="@dimen/_56sdp"/>
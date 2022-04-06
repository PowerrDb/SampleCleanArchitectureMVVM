package com.bestpractises.razisample

import android.content.Context
import android.content.res.TypedArray
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doAfterTextChanged
import com.bestpractises.razisample.databinding.CustomEditTextBinding
import com.bestpractises.razisample.util.extension.gone
import com.bestpractises.razisample.util.extension.visible


class CustomEditText(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    enum class TypeEnum {
        PASSWORD, NORMAL, DROP_DOWN, PHONE_NUMBER
    }

    private var isIconVisible: Boolean = true
    private var isRequire: Boolean = true
    private var type: TypeEnum = TypeEnum.NORMAL
    private var isDropDown: Boolean = false
    private var binding: CustomEditTextBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = CustomEditTextBinding.inflate(inflater, this, true)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)

        getAttribute(attributes)
        initIcon()
        attributes.recycle()

    }

    private fun iconClickListener() {
        binding.iconEditText.setOnClickListener {
            when (type) {
                TypeEnum.PASSWORD -> {
                    if (binding.editText.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        binding.editText.inputType = InputType.TYPE_CLASS_TEXT or
                                InputType.TYPE_TEXT_VARIATION_PASSWORD

                        binding.iconEditText.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_visibility,
                                null
                            )
                        )

                    } else {
                        binding.editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        binding.iconEditText.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_visibility_off,
                                null
                            )
                        )
                    }
                    binding.editText.setSelection(binding.editText.text.length)
                }
                TypeEnum.NORMAL -> {
                    binding.editText.setText("")
                }
                TypeEnum.DROP_DOWN -> {

                }
                TypeEnum.PHONE_NUMBER -> {

                }
            }
        }


    }

    fun rootClickable(callbacks: () -> Unit) {
        binding.editText.setOnClickListener {
            callbacks()
        }

        binding.root.setOnClickListener {
            callbacks()
        }

    }

    fun editTextClickListener(callbacks: () -> Unit) {
        if (!isDropDown) return
        binding.editText.setOnClickListener {
            callbacks()
        }

    }

    fun textValue() = binding.editText.text.toString()

    fun showError(error: String?) {
        if (error == null) {
            binding.txtError.gone()
            binding.editText.background =
                ResourcesCompat.getDrawable(resources, R.drawable.shape_edit_text, null)
            binding.txtHint.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.blue1e3b58,
                    null
                )
            )
        } else {
            binding.txtError.visible()
            binding.txtError.text = error
            binding.editText.background =
                ResourcesCompat.getDrawable(resources, R.drawable.shape_edit_text_error, null)
            binding.txtHint.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.red,
                    null
                )
            )
        }
    }

    fun setHint(hint: String?) {
        if (hint == null) {
            binding.txtHint.gone()
        } else {
            binding.txtHint.gone()
            binding.txtHint.text = hint
        }
    }

    fun setText(text: String?) {
        text?.let {
            binding.editText.setText(it)
        }
    }

    fun textWatcher(callbacks: (String) -> Unit) {
        binding.editText.doAfterTextChanged {
            callbacks(it.toString())
        }
    }

    fun clear() {
        binding.editText.setText("")
    }

    fun showProgressBar() {
        binding.iconEditText.gone()
        binding.progressBar.visible()
        binding.editText.isEnabled = false
    }

    fun hideProgressBar() {
        if (isIconVisible) {
            binding.iconEditText.visible()
        }
        binding.progressBar.gone()
        binding.editText.isEnabled = true
    }

    private fun getAttribute(attributes: TypedArray) {
        type = TypeEnum.values()[attributes.getInt(R.styleable.CustomEditText_type, 0)]
        isRequire = attributes.getBoolean(R.styleable.CustomEditText_require, false)
        isIconVisible = attributes.getBoolean(R.styleable.CustomEditText_showIcon, true)

        if (isRequire) {
            binding.txtRequire.visible()
        }
        binding.txtHint.text = attributes.getText(R.styleable.CustomEditText_hint) ?: ""
        binding.editText.setText(attributes.getText(R.styleable.CustomEditText_text) ?: "")
        val input = attributes.getInt(
            R.styleable.CustomEditText_android_inputType,
            InputType.TYPE_CLASS_TEXT
        )
        binding.editText.isFocusableInTouchMode =
            attributes.getBoolean(R.styleable.CustomEditText_android_focusableInTouchMode, true)
        binding.txtError.text = attributes.getText(R.styleable.CustomEditText_error) ?: ""
    }

    private fun initIcon() {
        if (!isIconVisible) {
            binding.iconEditText.gone()
        } else {
            iconClickListener()
            when (type) {
                TypeEnum.PASSWORD -> {
                    binding.editText.inputType = InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_PASSWORD
                    binding.iconEditText.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_visibility,
                            null
                        )
                    )
                }
                TypeEnum.NORMAL -> {
                    binding.editText.inputType = InputType.TYPE_CLASS_TEXT
                    binding.iconEditText.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_clear,
                            null
                        )
                    )
                }
                TypeEnum.DROP_DOWN -> {
                    binding.editText.inputType = InputType.TYPE_CLASS_TEXT
                    isDropDown = true
                    binding.editText.isFocusable = false
                    binding.iconEditText.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_arrow_down,
                            null
                        )
                    )
                }
                TypeEnum.PHONE_NUMBER -> {
                    binding.editText.inputType = InputType.TYPE_CLASS_NUMBER
                    binding.iconEditText.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_mobile,
                            null
                        )
                    )
                }
            }
        }
    }

}
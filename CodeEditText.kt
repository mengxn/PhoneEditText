package com.paqu.view

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import com.paqu.ui.TextWatcherAdapter

/**
 * 验证码 111 格式化
 * @author mengxn
 * @date 2019-05-31
 */
class CodeEditText(context: Context, attributeSet: AttributeSet? = null) : EditText(context, attributeSet) {

    companion object {

        private const val MAX_LENGTH = 4

    }

    init {
        inputType = InputType.TYPE_CLASS_PHONE
        addTextChangedListener(object : TextWatcherAdapter() {

            val builder = StringBuilder()

            override fun afterTextChanged(s: Editable) {
                builder.clear()
                builder.append(s.toString().replace(Regex("\\s"), ""))

                if (builder.length > MAX_LENGTH) {
                    builder.delete(MAX_LENGTH, builder.length)
                }
                for (i in builder.length - 1 downTo 1) {
                    builder.insert(i, " ")
                }
                if (builder.toString() != s.toString()) {
                    s.replace(0, s.length, builder.toString())
                }
            }
        })
    }

}
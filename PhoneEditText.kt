package com.paqu.view

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import com.paqu.ui.TextWatcherAdapter

/**
 * 手机号 344 格式化
 * @author mengxn
 * @date 2019-05-31
 */
class PhoneEditText(context: Context, attributeSet: AttributeSet? = null) : EditText(context, attributeSet) {

    companion object {

        private const val MAX_LENGTH = 11
        private const val BLOCK_1 = 3
        private const val BLOCK_2 = 7

    }

    init {
        inputType = InputType.TYPE_CLASS_PHONE
        addTextChangedListener(object : TextWatcherAdapter() {

            val builder = StringBuilder()

            override fun afterTextChanged(s: Editable) {
                builder.clear()
                builder.append(s.toString())
                for (i in builder.length - 1 downTo 0) {
                    if (builder[i] == ' ') {
                        builder.deleteCharAt(i)
                    }
                }
                if (builder.length > MAX_LENGTH) {
                    builder.delete(MAX_LENGTH, s.length)
                }
                if (builder.length > BLOCK_2) {
                    builder.insert(BLOCK_2, " ")
                }
                if (builder.length > BLOCK_1) {
                    builder.insert(BLOCK_1, " ")
                }
                if (builder.toString() != s.toString()) {
                    s.replace(0, s.length, builder.toString())
                }
            }
        })
    }

}
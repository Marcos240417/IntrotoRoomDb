package com.example.introductiontoroom.persontransformations

import android.text.Editable
import android.text.TextWatcher

class DateMask : TextWatcher {
    private var isUpdating = false


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (isUpdating) {
            return
        }

        val text = s.toString().filter { it.isDigit() }
        val masked = StringBuilder()

        var i = 0
        if (text.length > 2) {
            masked.append(text.substring(0, 2)).append("/")
            i = 2
        }
        if (text.length > 4) {
            masked.append(text.substring(2, 4)).append("/")
            i = 4
        }
        if (i < text.length) {
            masked.append(text.substring(i))
        }

        isUpdating = true
        s?.replace(0, s.length, masked.toString())
        isUpdating = false
    }
}

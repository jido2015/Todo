package com.a.todo.data.helper

import android.util.Log
import org.apache.commons.lang3.StringUtils

class HelperClass {

    companion object {
        fun phoneNumberFormat(number: String): String {
            var number = number
            number = number.replace("\\s+".toRegex(), "")
            Log.i("isNumeric", number.substring(0, 2))
            Log.i("isNumeric", StringUtils.left(number, 2))
            val firstTwo: String = StringUtils.left(number, 2)
            if (firstTwo == "01") {
                Log.i("isNumeric", "it is 01")
                Log.i("isNumeric", number)
                return number
            } else if (number.length == 11) {
                Log.i("isNumeric", "is Same")
                number = number.substring(1)
                number = "234$number"
                Log.i("isNumeric2", number)
                return number
            } else if (number.length == 13) {
                Log.i("isNumeric", number)
                return number
            } else if (StringUtils.contains(number, "+")) {
                number = number.substring(1)
                Log.i("isNumeric", number)
                return number
            }
            return number
        }

    }

}
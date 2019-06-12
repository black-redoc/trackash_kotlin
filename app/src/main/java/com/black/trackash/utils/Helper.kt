package com.black.trackash.utils

import java.lang.StringBuilder


fun Double.exchange(): String {
    val value = this.toInt().toString()
    val newValue: StringBuilder = StringBuilder()
    var count = 1
    for(i in value.reversed()) {
        if(count == 3) {
            newValue.append("$i.")
            count = 1
        } else {
            count++
            newValue.append("$i")
        }
    }

    val v = newValue.toString().reversed()

    return if(v[0] == '.') v.substring(1) else v
}
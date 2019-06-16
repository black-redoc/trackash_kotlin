package com.black.trackash.utils

import java.lang.StringBuilder


fun Double.exchange(): String {
    val value = this.toInt().toString()
    val newValue = StringBuilder()
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

    var v = newValue.toString().reversed()

    v =  if(v[0] == '.') v.substring(1) else v
    return "$$v"
}

fun Double.doubleExchange(): String {
    val value = this.toString()

    return when {
        value.length == 6  -> "$${value.substring(0,2)[0]}${if(!value.substring(0,2)[1].equals("0"))  ".${value.substring(0,2)[1]}" else ""}k"
        value.length in 6..7 -> "$${value.substring(0,2)}k"
        value.length == 8 -> "$${value.substring(0,3)}k"
        value.length == 9 -> "$${value.substring(0,2)[0]}.${value.substring(0,2)[1]}m"
        value.length in 10..12 -> "$${value.substring(0,3)}m"
        else -> "$$value"
    }
}
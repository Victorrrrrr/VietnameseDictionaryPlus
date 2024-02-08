package com.gp.lib_framework.ext

inline fun <reified T> Any.saveAs() : T {
    return this as T
}

fun <T> Any.saveAsUnChecked() : T {
    return this as T
}

inline fun <reified T> Any.isEqualType() : Boolean {
    return this is T
}
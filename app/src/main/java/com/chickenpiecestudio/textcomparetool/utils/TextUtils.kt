package com.chickenpiecestudio.textcomparetool.utils

fun String?.indicesOf(substr: String, ignoreCase: Boolean = true): List<List<Int>> {
    return this?.let {
        val regex = if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr)
        regex.findAll(this).map { it.range.start + 4 }.toList().chunked(2)
    } ?: emptyList()
}



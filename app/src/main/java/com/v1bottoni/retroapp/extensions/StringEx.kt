package com.v1bottoni.retroapp.extensions

fun String.Companion.unknownError(TAG: String,line: () -> Int): String =
    "Unknown Error in $TAG at line: ${line.invoke()} }"
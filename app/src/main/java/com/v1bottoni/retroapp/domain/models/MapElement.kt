package com.v1bottoni.retroapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class MapElement(
): Parcelable

@Parcelize
data class User(
    val id:String ,
    val title : String,
    val firstName : String,
    val lastName : String,
    val picture : String
):MapElement()
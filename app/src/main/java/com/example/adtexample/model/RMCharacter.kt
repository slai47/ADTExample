package com.example.adtexample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RMCharacter(val id: Int, val name: String,
                       val status: String, val species: String,
                       val gender: String, val location: Location,
                       val image: String, val url : String)  : Parcelable
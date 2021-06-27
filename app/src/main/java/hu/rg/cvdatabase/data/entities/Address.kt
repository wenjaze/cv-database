package hu.rg.cvdatabase.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address (
    val streetName : String,
    val streetNumber : Int,
    val postalCode : Int,
    val cityName : String
) : Parcelable
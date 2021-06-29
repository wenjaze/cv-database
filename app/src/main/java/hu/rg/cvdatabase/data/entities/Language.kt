package hu.rg.cvdatabase.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Language(
    val levelOne: String,
    val levelTwo: String,
    val levelThree: String,
    val languageOneName: String,
    val languageTwoName: String,
    val languageThreeName: String,
) : Parcelable
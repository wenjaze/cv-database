package hu.rg.cvdatabase.data.entities

import android.os.Parcelable
import android.widget.TwoLineListItem
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Skill(
    val skillOne : String,
    val skillTwo : String,
    val skillThree : String
) : Parcelable

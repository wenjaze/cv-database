package hu.rg.cvdatabase.data.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class CV(
    @PrimaryKey(autoGenerate = false)
    val name : String,
    val age : Int,
    @Embedded val address : Address,
    val motivationLetter : String
//    @Ignore
//    val img : Bitmap
) : Parcelable

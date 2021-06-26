package hu.rg.cvdatabase.data.entities

import android.graphics.Bitmap
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class CV(
    @PrimaryKey(autoGenerate = false)
    val name : String,
    val age : Int,
    @Embedded val address : Address,
    val motivationLetter : String
//    @Ignore
//    val img : Bitmap
)

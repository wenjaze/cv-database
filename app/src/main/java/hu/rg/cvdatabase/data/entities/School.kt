package hu.rg.cvdatabase.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class School(
    @PrimaryKey(autoGenerate = false)
    val name : String,
    val from : String,
    val to : String,
    val personName : String
)
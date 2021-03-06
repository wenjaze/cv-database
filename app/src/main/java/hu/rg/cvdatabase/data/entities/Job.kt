package hu.rg.cvdatabase.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Job (
    @PrimaryKey
    val title: String,
    val from : String,
    val to : String,
    val company : String,
    val personName : String
)
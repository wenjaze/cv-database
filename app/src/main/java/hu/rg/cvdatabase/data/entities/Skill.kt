package hu.rg.cvdatabase.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Skill(
    @PrimaryKey
    val skillName : String,
    val personName : String
)
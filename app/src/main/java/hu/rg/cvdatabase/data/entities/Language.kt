package hu.rg.cvdatabase.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Language(
    val level: String,
    @PrimaryKey
    val languageName: String,
    val personName: String
)
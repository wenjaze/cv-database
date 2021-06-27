package hu.rg.cvdatabase.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import javax.security.auth.Subject

@Entity
class School(
    @PrimaryKey(autoGenerate = false)
    val name : String,
    val from : String,
    val to : String,
    val subject: String,
    val personName : String
)
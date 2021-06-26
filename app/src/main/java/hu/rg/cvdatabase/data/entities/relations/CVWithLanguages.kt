package hu.rg.cvdatabase.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import hu.rg.cvdatabase.data.entities.CV
import hu.rg.cvdatabase.data.entities.Language

data class CVWithLanguages(
    @Embedded val cv : CV,
    @Relation(
        parentColumn = "name",
        entityColumn = "personName"
    )
    val languages : List<Language>
)
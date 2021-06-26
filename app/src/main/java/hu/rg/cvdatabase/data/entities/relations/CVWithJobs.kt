package hu.rg.cvdatabase.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import hu.rg.cvdatabase.data.entities.CV
import hu.rg.cvdatabase.data.entities.Job

data class CVWithJobs(
    @Embedded val cv : CV,
    @Relation(
        parentColumn = "name",
        entityColumn = "personName"
    )
    val jobs : List<Job>
)
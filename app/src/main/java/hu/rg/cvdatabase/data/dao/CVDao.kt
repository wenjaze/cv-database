package hu.rg.cvdatabase.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.rg.cvdatabase.data.entities.*
import hu.rg.cvdatabase.data.entities.relations.CVWithJobs
import hu.rg.cvdatabase.data.entities.relations.CVWithLanguages
import hu.rg.cvdatabase.data.entities.relations.CVWithSchools
import hu.rg.cvdatabase.data.entities.relations.CVWithSkills

@Dao
interface CVDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCV(cv: CV)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLanguage(language: Language)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkill(skill: Skill)

    @Query("SELECT * FROM cv")
    fun getAllCVs() : LiveData<List<CV>>

    @Transaction
    @Query("SELECT * FROM cv WHERE name = :personName")
    fun getCVWithJobs(personName: String): LiveData<List<CVWithJobs>>

    @Transaction
    @Query("SELECT * FROM cv WHERE name = :personName")
    fun getCVWithSchools(personName: String): LiveData<List<CVWithSchools>>

    @Transaction
    @Query("SELECT * FROM cv WHERE name = :personName")
    fun getCVWithLanguages(personName: String): LiveData<List<CVWithLanguages>>

    @Transaction
    @Query("SELECT * FROM cv WHERE name = :personName")
    fun getCVWithSkills(personName: String): LiveData<List<CVWithSkills>>
}
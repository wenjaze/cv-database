package hu.rg.cvdatabase.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.rg.cvdatabase.data.entities.*
import hu.rg.cvdatabase.data.entities.relations.CVWithJobs
import hu.rg.cvdatabase.data.entities.relations.CVWithSchools

@Dao
interface CVDao {

    // INSERT functions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCV(cv: CV)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job)

    @Query("DELETE FROM cv")
    suspend fun deleteAllCVs()

    @Delete
    suspend fun deleteCV(cv : CV)

    @Update
    suspend fun updateCV(cv : CV)

    @Update
    suspend fun updateJob(job:Job)

    @Update
    suspend fun updateSchool(school: School)

    @Query("SELECT * FROM cv WHERE LOWER(languageOneName) LIKE LOWER(:language)  or LOWER(languageTwoName) == LOWER(:language) or LOWER(languageThreeName) == LOWER(:language)")
    fun getCVsWithLanguage(language: String) : LiveData<List<CV>>


    @Query("SELECT * FROM cv WHERE LOWER(skillOne) LIKE LOWER(:skill)  or LOWER(skillTwo) == LOWER(:skill) or LOWER(skillThree) == LOWER(:skill)")
    fun getCVsWithSkill(skill : String) : LiveData<List<CV>>

    @Query("SELECT * FROM cv WHERE LOWER(name) LIKE LOWER(:name)")
    fun getCVByName(name : String) : LiveData<List<CV>>

    @Query("SELECT * FROM cv")
    fun getAllCVs() : LiveData<List<CV>>

    @Transaction
    @Query("SELECT * FROM cv WHERE name = :personName")
    fun getCVWithJobs(personName: String): LiveData<List<CVWithJobs>>

    @Query("SELECT * FROM cv WHERE name IN (SELECT personName FROM job WHERE company LIKE :companyName)")
    fun getCVWithCompanyName(companyName : String) : LiveData<List<CV>>

    @Query("SELECT * FROM cv WHERE name IN (SELECT personName FROM school WHERE name LIKE :schoolName)")
    fun getCVWithSchoolName(schoolName : String) : LiveData<List<CV>>

    @Transaction
    @Query("SELECT * FROM cv WHERE name = :personName")
    fun getCVWithSchools(personName: String): LiveData<List<CVWithSchools>>
}
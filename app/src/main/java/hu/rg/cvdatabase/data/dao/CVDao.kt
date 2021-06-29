package hu.rg.cvdatabase.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.rg.cvdatabase.data.entities.*
import hu.rg.cvdatabase.data.entities.relations.CVWithJobs
import hu.rg.cvdatabase.data.entities.relations.CVWithSchools

@Dao
interface CVDao {
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

    @Query("SELECT * FROM cv")
    fun getAllCVs() : LiveData<List<CV>>

    @Transaction
    @Query("SELECT * FROM cv WHERE name = :personName")
    fun getCVWithJobs(personName: String): LiveData<List<CVWithJobs>>

    @Transaction
    @Query("SELECT * FROM cv WHERE name = :personName")
    fun getCVWithSchools(personName: String): LiveData<List<CVWithSchools>>
}
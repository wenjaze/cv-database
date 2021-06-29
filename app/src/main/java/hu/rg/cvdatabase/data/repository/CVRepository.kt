package hu.rg.cvdatabase.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Update
import hu.rg.cvdatabase.data.dao.CVDao
import hu.rg.cvdatabase.data.entities.*

class CVRepository(private val cvDao: CVDao) {

    val getAllCVs: LiveData<List<CV>> = cvDao.getAllCVs()


    suspend fun deleteCV(cv: CV) {
        cvDao.deleteCV(cv)
    }

    suspend fun deleteAllCVs() {
        cvDao.deleteAllCVs()
    }

    fun getCVWithCompanyNAme(companyName : String) = cvDao.getCVWithCompanyName(companyName)
    fun getCVWithSchoolName(schoolName : String) = cvDao.getCVWithSchoolName(schoolName)

    fun getCVsWithLanguage(language : String)=
        cvDao.getCVsWithLanguage(language)


    fun getCVWithJobs(personName: String) = cvDao.getCVWithJobs(personName)
    fun getCvWithSchools(personName: String) = cvDao.getCVWithSchools(personName)

    fun getCVByName(name:String) = cvDao.getCVByName(name)

    suspend fun updateCV(cv: CV) {
        cvDao.updateCV(cv)
    }

    suspend fun updateJob(job: Job) {
        cvDao.updateJob(job)
    }

    suspend fun updateSchool(school: School) {
        cvDao.updateSchool(school)
    }

    suspend fun addCV(cv: CV) {
        cvDao.insertCV(cv)
    }

    suspend fun addSchool(school: School) {
        cvDao.insertSchool(school)
    }

    suspend fun addJob(job: Job) {
        cvDao.insertJob(job)
    }


}
package hu.rg.cvdatabase.data.repository

import androidx.lifecycle.LiveData
import hu.rg.cvdatabase.data.dao.CVDao
import hu.rg.cvdatabase.data.entities.*

class CVRepository(private val cvDao: CVDao) {

    val getAllCVs : LiveData<List<CV>> = cvDao.getAllCVs()

    fun getCVWithLanguages(personName: String) = cvDao.getCVWithLanguages(personName)
    fun getCVWithSkills(personName: String) = cvDao.getCVWithSkills(personName)
    fun getCVWithJobs(personName: String) = cvDao.getCVWithJobs(personName)
    fun getCvWithSchools(personName: String) = cvDao.getCVWithSchools(personName)
    fun getPeopleWithLanguage(lang : String) = cvDao.getCVWhoSpeaksLanguage(lang)

    suspend fun addCV(cv : CV) {
        cvDao.insertCV(cv)
    }

    suspend fun addSchool(school: School) {
        cvDao.insertSchool(school)
    }

    suspend fun addLanguage(language: Language) {
        cvDao.insertLanguage(language)
    }

    suspend fun addSkill(skill: Skill) {
        cvDao.insertSkill(skill)
    }

    suspend fun addJob(job : Job) {
        cvDao.insertJob(job)
    }




}
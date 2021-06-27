package hu.rg.cvdatabase.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hu.rg.cvdatabase.data.CVDatabase
import hu.rg.cvdatabase.data.entities.*
import hu.rg.cvdatabase.data.repository.CVRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CVViewModel(application: Application) : AndroidViewModel(application) {
    val getAllCVs : LiveData<List<CV>>
    private val repository : CVRepository

    init {
        val cvDao = CVDatabase.getInstance(application).cvDao
        repository = CVRepository(cvDao)
        getAllCVs = repository.getAllCVs
    }
    fun getCVWithSkills(personName: String) = repository.getCVWithSkills(personName)
    fun getCVWithLanguages(personName: String) = repository.getCVWithLanguages(personName)
    fun getCVWithSchools(personName: String) = repository.getCvWithSchools(personName)
    fun getCVWithJobs(personName : String) = repository.getCVWithJobs(personName)
    fun getPeopleWithLanguage(lang : String) = repository.getPeopleWithLanguage(lang)

    fun insertCV(cv : CV) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCV(cv)
        }
    }

    fun insertSchool(school: School){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSchool(school)
        }
    }

    fun insertJob(job:Job){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addJob(job)
        }
    }

    fun insertSkill(skill: Skill){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSkill(skill)
        }
    }

    fun insertLanguage(language: Language){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLanguage(language)
        }
    }

}
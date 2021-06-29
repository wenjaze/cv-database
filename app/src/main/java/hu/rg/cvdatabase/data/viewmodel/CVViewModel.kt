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

    fun deleteCV(cv : CV) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCV(cv)
        }
    }

    fun getCVByName(name:String) = repository.getCVByName(name)

    fun getCVWithCompanyName(companyName : String) = repository.getCVWithCompanyNAme(companyName)
    fun getCVWithSchoolName(schoolName : String) = repository.getCVWithSchoolName(schoolName)

    fun getCVsWithLanguage(language : String) = repository.getCVsWithLanguage(language)

    fun deleteAllCVs() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCVs()
        }
    }

    fun getCVWithSchools(personName: String) = repository.getCvWithSchools(personName)
    fun getCVWithJobs(personName : String) = repository.getCVWithJobs(personName)

    fun updateCV(cv : CV) { viewModelScope.launch(Dispatchers.IO) {  repository.updateCV(cv) }}
    fun updateJob(job:Job) { viewModelScope.launch(Dispatchers.IO) { repository.updateJob(job) }}
    fun updateSchool(school: School) { viewModelScope.launch(Dispatchers.IO) { repository.updateSchool(school) }}

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


}
package hu.rg.cvdatabase.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hu.rg.cvdatabase.data.entities.CV
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

    fun insertCV(cv : CV) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCV(cv)
        }
    }
}
package hu.rg.cvdatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import hu.rg.cvdatabase.data.dao.CVDao
import hu.rg.cvdatabase.data.CVDatabase
import hu.rg.cvdatabase.data.entities.*
import hu.rg.cvdatabase.data.viewmodel.CVViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        // MAGIC ONE LINER
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        insertSampleData()

    }

    private fun insertSampleData(){

        val dao = CVDatabase.getInstance(this).cvDao
        val mCVViewModel = ViewModelProvider(this).get(CVViewModel::class.java)


        val schools = listOf(
            School("Jake Wharton School","2013","2030","Computer Science","Edward Pollen"),
            School("Kotlin School","2010","2030","Bucthery","Rajesh Anish"),
            School("JetBrains School","2013","2030","Mathematics","Fildberg Gunther")
        )

        val jobs = listOf(
            Job("Software Engineer","2010","2012","Google","Edward Pollen"),
            Job("Butcher","2010","2012","TESCO","Rajesh Anish"),
            Job("Postman","2004","2007","Posta Hungary","Rajesh Anish"),
            Job("Senior Kotlin Engineer","2010","2012","Google","Fildberg Gunther"),
            Job("Team Lead Developer","2012","2015","Google","Fildberg Gunther")
        )

        val cvs = listOf(
            CV("Fildberg Gunther", 24,
                Address("Kelvin street",2,65110,"Miami"),
                Skill("Confidency","Braveness","Dexterity"),
                Language("C","","","French","",""),
                getString(R.string.mot_letter)),
            CV("Rajesh Anish", 52,Address("Dove street",12,4112,"Los Angeles"),
                Skill("Agile","","Dexterity"),
                Language("C","","","Polish","",""),
                getString(R.string.mot_letter)),
            CV("Edward Pollen", 34,Address("Oregon street",98,3012,"Moscow"),
                Skill("","","Clever"),
                Language("C","","","Russian","",""),
                getString(R.string.mot_letter))
        )


        lifecycleScope.launch {
            schools.forEach { dao.insertSchool(it) }
            cvs.forEach { dao.insertCV(it) }
            jobs.forEach { dao.insertJob(it)}
        }
    }

    private suspend fun getJobs(person : String, dao : CVDao) = dao.getCVWithJobs("Edward Pollen")
}
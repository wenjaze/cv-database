package hu.rg.cvdatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import hu.rg.cvdatabase.data.CVDao
import hu.rg.cvdatabase.data.CVDatabase
import hu.rg.cvdatabase.data.entities.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

    }

    private fun insertSampleData(){

        val dao = CVDatabase.getInstance(this).cvDao

        val skills = listOf(
            Skill("confidency", "Edward Pollen"),
            Skill("intelligence", "Edward Pollen"),
            Skill("braveness", "Edward Pollen"),
            Skill("confidency", "Fildberg Gunther"),
            Skill("intelligence", "Rajesh Anish"),
            Skill("braveness", "Fildberg Gunther")
        )
        val schools = listOf(
            School("Jake Wharton School","2013","2030","Edward Pollen"),
            School("Kotlin School","2010","2030","Rajesh Anish"),
            School("JetBrains School","2013","2030","Fildberg Gunther")
        )
        val jobs = listOf(
            Job(2L,"Software Engineer","2010","2012","Google","Edward Pollen"),
            Job(3L,"Butcher","2010","2012","TESCO","Rajesh Anish"),
            Job(4L,"Postman","2004","2007","Posta Hungary","Rajesh Anish"),
            Job(5L,"Senior Kotlin Engineer","2010","2012","Google","Fildberg Gunther"),
            Job(6L,"Team Lead Developer","2012","2015","Google","Fildberg Gunther")
        )
        val cvs = listOf(
            CV("Fildberg Gunther", 24,
                Address("Kelvin street",2,65110,"Miami"),
                getString(R.string.mot_letter)),
            CV("Rajesh Anish", 52,Address("Dove street",12,4112,"Los Angeles"),
                getString(R.string.mot_letter)),
            CV("Edward Pollen", 34,Address("Oregon street",98,3012,"Moscow"),
                getString(R.string.mot_letter))
        )

        val languages = listOf(
            Language('C',"French","Edward Pollen") ,
            Language('B',"English","Edward Pollen"),
            Language('C',"Russian","Rajesh Anish") ,
            Language('B',"Polish","Fildberg Gunther")
        )

        lifecycleScope.launch {
            skills.forEach { dao.insertSkill(it) }
            schools.forEach { dao.insertSchool(it) }
            cvs.forEach { dao.insertCV(it) }
            languages.forEach { dao.insertLanguage(it) }
            jobs.forEach { dao.insertJob(it)}

            val cvWithJobs = dao.getCVWithJobs("Rajesh Anish")
            val jobs = cvWithJobs

        }
    }

    private suspend fun getJobs(person : String, dao : CVDao) = dao.getCVWithJobs("Edward Pollen")
}
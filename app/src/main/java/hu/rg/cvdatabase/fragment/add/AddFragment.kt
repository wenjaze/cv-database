package hu.rg.cvdatabase.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.entities.*
import hu.rg.cvdatabase.data.viewmodel.CVViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mcvViewModel: CVViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_add, container, false)

        mcvViewModel = ViewModelProvider(this).get(CVViewModel::class.java)

        rootView.updateAddButton.setOnClickListener {
            addCVToDB()
        }

        return rootView
    }


    private fun addCVToDB() {


        // Personal information
        val name = updateNameTextField.text.toString()
        val age = updateAgeTextField.text.toString()

        // Address
        val streetName = updateStreetNameTextField.text.toString()
        val streetNumber = updateStreetNumberTextField.text.toString()
        val postalCode = updatePostalCodeTextField.text.toString()
        val cityName = updateCityNameTextField.text.toString()

        // Education
        val schoolName = updateSchoolName.text.toString()
        val schoolFrom = updateSchoolFrom.text.toString()
        val schoolTo = updateSchoolTo.text.toString()
        val schoolSubject = updateSchoolSubject.text.toString()

        // Experience
        val jobTitle = updateJobName.text.toString()
        val jobFrom = updateJobFrom.text.toString()
        val jobTo = updateJobTo.text.toString()
        val companyName = updateCompanyName.text.toString()

        // Skills
        val skillOne = updateSkillOne.text.toString()
        val skillTwo = updateSkillTwo.text.toString()
        val skillThree = updateSkillThree.text.toString()

        // Languages
        val languageOne = updateLanguageOne.text.toString()
        val languageTwo = updateLanguageTwo.text.toString()
        val languageThree = updateLanguageThree.text.toString()
        val levelOne = updateLevelOne.text.toString()
        val levelTwo = updateLevelTwo.text.toString()
        val levelThree = updateLevelThree.text.toString()

        // Motivation letter
        val motivationLetter = motivationLetter.text.toString()



        if (inputCheck(name,age)){
            val cv = CV(name,
                Integer.parseInt(age),
                Address(streetName,Integer.parseInt(streetNumber),Integer.parseInt(postalCode),cityName),
                motivationLetter)

            val job = Job(jobTitle,jobFrom,jobTo,companyName,name)

            val languages : List<Language> = listOf(
                Language(levelOne,languageOne,name),
                Language(levelTwo,languageTwo,name),
                Language(levelThree,languageThree,name))

            val skills : List<Skill> = listOf(
                Skill(skillOne,name),
                Skill(skillTwo,name),
                Skill(skillThree,name)
            )

            val school = School(schoolName,schoolFrom,schoolTo,schoolSubject,name)

            mcvViewModel.insertCV(cv)
            mcvViewModel.insertJob(job)
            languages.forEach { mcvViewModel.insertLanguage(it) }
            skills.forEach { mcvViewModel.insertSkill(it) }
            mcvViewModel.insertSchool(school)
            Toast.makeText(requireContext(),"CV succesfully added to database.",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else {
            Toast.makeText(this.context,"Please fill name and age fields at least.",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name : String, age : String) : Boolean = !(TextUtils.isEmpty(name) && TextUtils.isEmpty(
        age.toString()
    ))

}
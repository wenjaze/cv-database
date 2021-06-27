package hu.rg.cvdatabase.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) findNavController().popBackStack()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add, container, false)

        mcvViewModel = ViewModelProvider(this).get(CVViewModel::class.java)

        rootView.addButton.setOnClickListener {
            addCVToDB()
        }

        return rootView
    }


    private fun addCVToDB() {


        // Personal information
        val name = nameTextField.text.toString()
        val age = ageTextField.text.toString()

        // Address
        val streetName = streetNameTextField.text.toString()
        val streetNumber = streetNumberTextField.text.toString()
        val postalCode = postalCodeTextField.text.toString()
        val cityName = cityNameTextField.text.toString()

        // Education
        val schoolName = schoolName.text.toString()
        val schoolFrom = schoolFrom.text.toString()
        val schoolTo = schoolTo.text.toString()
        val schoolSubject = schoolSubject.text.toString()

        // Experience
        val jobTitle = jobName.text.toString()
        val jobFrom = jobFrom.text.toString()
        val jobTo = jobTo.text.toString()
        val companyName = companyName.text.toString()

        // Skills
        val skillOne = skillOne.text.toString()
        val skillTwo = skillTwo.text.toString()
        val skillThree = skillThree.text.toString()

        // Languages
        val languageOne = languageOne.text.toString()
        val languageTwo = languageTwo.text.toString()
        val languageThree = languageThree.text.toString()
        val levelOne = levelOne.text.toString()
        val levelTwo = levelTwo.text.toString()
        val levelThree = levelThree.text.toString()

        // Motivation letter
        val motivationLetter = motivationLetter.text.toString()



        if (inputCheck(name, age, streetName, streetNumber, cityName, postalCode)) {
            val cv = CV(
                name,
                Integer.parseInt(age),
                Address(
                    streetName,
                    Integer.parseInt(streetNumber),
                    Integer.parseInt(postalCode),
                    cityName
                ),
                motivationLetter
            )

            val job = Job(jobTitle, jobFrom, jobTo, companyName, name)

            val languages: List<Language> = listOf(
                Language(levelOne, languageOne, name),
                Language(levelTwo, languageTwo, name),
                Language(levelThree, languageThree, name)
            )

            val skills: List<Skill> = listOf(
                Skill(skillOne, name),
                Skill(skillTwo, name),
                Skill(skillThree, name)
            )

            val school = School(schoolName, schoolFrom, schoolTo, schoolSubject, name)

            mcvViewModel.insertCV(cv)
            mcvViewModel.insertJob(job)
            languages.forEach { mcvViewModel.insertLanguage(it) }
            skills.forEach { mcvViewModel.insertSkill(it) }
            mcvViewModel.insertSchool(school)
            Toast.makeText(requireContext(), "CV succesfully added to database.", Toast.LENGTH_LONG)
                .show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(
                this.context,
                "Please fill name and age fields at least.",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun inputCheck(
        name: String,
        age: String,
        streetName: String,
        streetNumber: String,
        cityName: String,
        postalCode: String
    ): Boolean =
        !(
                TextUtils.isEmpty(name)
                        && TextUtils.isEmpty(age)
                        && TextUtils.isEmpty(streetName)
                        && TextUtils.isEmpty(streetNumber)
                        && TextUtils.isEmpty(cityName)
                        && TextUtils.isEmpty(postalCode)

                )

}
package hu.rg.cvdatabase.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Index
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.entities.*
import hu.rg.cvdatabase.data.viewmodel.CVViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.lang.IndexOutOfBoundsException

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mCVViewModel: CVViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) findNavController().popBackStack()
        if (item.itemId == R.id.menu_delete) deleteCV()
        return super.onOptionsItemSelected(item)
    }

    private fun deleteCV() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mCVViewModel.deleteCV(args.selectedCV)
            Toast.makeText(
                requireContext(),
                "CV : "
                        + args.selectedCV.name +
                        " succesfully deleted.",
                Toast.LENGTH_LONG
            )
                .show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.selectedCV.name}?")
        builder.setMessage("Are you sure you want to delete ${args.selectedCV.name} from the CVs?")
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mCVViewModel = ViewModelProvider(this).get(CVViewModel::class.java)
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_update, container, false)

        val mCVViewModel = ViewModelProvider(this).get(CVViewModel::class.java)
        val currentCV = args.selectedCV


        mCVViewModel.getCVWithJobs(currentCV.name)
            .observe(viewLifecycleOwner, Observer { jobs ->
                if (jobs[0].jobs.isNotEmpty()) {
                    rootView.updateJobName.setText(jobs.lastOrNull()?.jobs?.last()?.title)
                    rootView.updateJobFrom.setText(jobs?.lastOrNull()?.jobs?.last()?.from)
                    rootView.updateJobTo.setText(jobs?.lastOrNull()?.jobs?.last()?.to)
                    rootView.updateCompanyName.setText(jobs?.lastOrNull()?.jobs?.last()?.company)
                }
            })

        mCVViewModel.getCVWithSchools(currentCV.name)
            .observe(viewLifecycleOwner, Observer { schools ->
                if (schools[0].schools.isNotEmpty()) {
                    rootView.updateSchoolNameTextField.setText(schools.last().schools.last().name)
                    rootView.updateSchoolFrom.setText(schools.last().schools.last().from)
                    rootView.updateSchoolTo.setText(schools.last().schools.last().to)
                    rootView.updateSchoolSubject.setText(schools.last().schools.last().subject)
                }
            })

        rootView.updateNameTextField.setText(currentCV.name)
        rootView.updateAgeTextField.setText(currentCV.age.toString())
        rootView.updateStreetNameTextField.setText(currentCV.address.streetName)
        rootView.updateStreetNumberTextField.setText(currentCV.address.streetNumber.toString())
        rootView.updatePostalCodeTextField.setText(currentCV.address.postalCode.toString())
        rootView.updateCityNameTextField.setText(currentCV.address.cityName)
        rootView.updateSkillOne.setText(currentCV.skills.skillOne)
        rootView.updateSkillTwo.setText(currentCV.skills.skillTwo)
        rootView.updateSkillThree.setText(currentCV.skills.skillThree)
        rootView.updateLanguageOne.setText(currentCV.languages.languageOneName)
        rootView.updateLanguageTwo.setText(currentCV.languages.languageTwoName)
        rootView.updateLanguageThree.setText(currentCV.languages.languageThreeName)
        rootView.updateLevelOne.setText(currentCV.languages.levelOne)
        rootView.updateLevelTwo.setText(currentCV.languages.levelTwo)
        rootView.updateLevelThree.setText(currentCV.languages.levelThree)
        rootView.updateMotivationLetter.setText(currentCV.motivationLetter)

        rootView.updateAddButton.setOnClickListener {
            updateItems()
        }

        return rootView
    }

    private fun updateItems() {

        val name = updateNameTextField.text.toString()
        val age = updateAgeTextField.text.toString()

        val streetName = updateStreetNameTextField.text.toString()
        val streetNumber = updateStreetNumberTextField.text.toString()
        val cityName = updateCityNameTextField.text.toString()
        val postalCode = updatePostalCodeTextField.text.toString()

        val schoolName = updateSchoolNameTextField.text.toString()
        val schoolFrom = updateSchoolFrom.text.toString()
        val schoolTo = updateSchoolTo.text.toString()
        val schoolSubject = updateSchoolSubject.text.toString()

        val jobName = updateJobName.text.toString()
        val jobFrom = updateJobFrom.text.toString()
        val jobTo = updateJobTo.text.toString()
        val companyName = updateCompanyName.text.toString()

        val skillOne = updateSkillOne.text.toString()
        val skillTwo = updateSkillTwo.text.toString()
        val skillThree = updateSkillThree.text.toString()

        val languageOne = updateLanguageOne.text.toString()
        val languageTwo = updateLanguageTwo.text.toString()
        val languageThree = updateLanguageThree.text.toString()

        val levelOne = updateLevelOne.text.toString()
        val levelTwo = updateLevelTwo.text.toString()
        val levelThree = updateLevelThree.text.toString()

        val motivationLetter = updateMotivationLetter.text.toString()

        if (inputCheck(
                name.toString(),
                age.toString(),
                streetName.toString(),
                streetNumber.toString(),
                cityName.toString(),
                postalCode.toString()
            )
        ) {
            val updatedCV = CV(
                name,
                Integer.parseInt(age),
                Address(
                    streetName,
                    Integer.parseInt(streetNumber),
                    Integer.parseInt(postalCode),
                    cityName
                ),
                Skill(skillOne, skillTwo, skillThree),
                Language(levelOne, levelTwo, levelThree, languageOne, languageTwo, languageThree),
                motivationLetter
            )

            val job = Job(jobName, jobFrom, jobTo, companyName, name)
            val school = School(schoolName, schoolFrom, schoolTo, schoolSubject, name)

            mCVViewModel.updateCV(updatedCV)
            mCVViewModel.insertJob(job)
            mCVViewModel.insertSchool(school)

            Toast.makeText(
                requireContext(),
                "CV "
                        + name +
                        " succesfully updated.",
                Toast.LENGTH_LONG
            )
                .show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "Please fill Personal informations fields at least.",
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

        (
                name.isNotBlank()
                        && age.isNotBlank()
                        && streetName.isNotBlank()
                        && streetNumber.isNotBlank()
                        && cityName.isNotBlank()
                        && postalCode.isNotBlank()

                )
}
package hu.rg.cvdatabase.fragment.update

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.CVDatabase
import hu.rg.cvdatabase.data.entities.Job
import hu.rg.cvdatabase.data.viewmodel.CVViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.coroutines.launch
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) findNavController().popBackStack()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_update, container, false)

        val mCVViewModel = ViewModelProvider(this).get(CVViewModel::class.java)
        val currentCV = args.selectedCV


        mCVViewModel.getCVWithJobs(currentCV.name).observe(viewLifecycleOwner, Observer { jobs->
            rootView.updateJobName.setText(jobs.last().jobs.last().title)
            rootView.updateJobFrom.setText(jobs.last().jobs.last().from)
            rootView.updateJobTo.setText(jobs.last().jobs.last().to)
            rootView.updateCompanyName.setText(jobs.last().jobs.last().company)
        })

        mCVViewModel.getCVWithSchools(currentCV.name).observe(viewLifecycleOwner, Observer { schools->
            rootView.updateSchoolName.setText(schools.last().schools.last().name)
            rootView.updateSchoolFrom.setText(schools.last().schools.last().from)
            rootView.updateSchoolTo.setText(schools.last().schools.last().to)
            rootView.updateSchoolSubject.setText(schools.last().schools.last().subject)
        })


        mCVViewModel.getCVWithSkills(currentCV.name).observe(viewLifecycleOwner, Observer { skills ->
            try {
                rootView.updateSkillOne.setText(skills[0].skills[0].skillName)
                rootView.updateSkillTwo.setText(skills[0].skills[1].skillName)
                rootView.updateSkillThree.setText(skills[0].skills[2].skillName)
            } catch (e: IndexOutOfBoundsException) {
                Log.e(this.toString(), e.toString())
            }
        })

        mCVViewModel.getCVWithLanguages(currentCV.name).observe(viewLifecycleOwner, Observer { languages->
            try {
                rootView.updateLanguageOne.setText(languages[0].languages[0].languageName)
                rootView.updateLevelOne.setText(languages[0].languages[0].level)
                rootView.updateLanguageOne.setText(languages[0].languages[1].languageName)
                rootView.updateLevelTwo.setText(languages[0].languages[1].level)
                rootView.updateLanguageThree.setText(languages[0].languages[2].languageName)
                rootView.updateLevelThree.setText(languages[0].languages[2].level)
            } catch (e : IndexOutOfBoundsException) {
                Log.e(this.toString(),e.toString())
            }
        })

        rootView.updateNameTextField.setText(currentCV.name)
        rootView.updateAgeTextField.setText(currentCV.age.toString())
        rootView.updateStreetNameTextField.setText(currentCV.address.streetName)
        rootView.updateStreetNumberTextField.setText(currentCV.address.streetNumber.toString())
        rootView.updatePostalCodeTextField.setText(currentCV.address.postalCode.toString())
        rootView.updateCityNameTextField.setText(currentCV.address.cityName)
        rootView.updateMotivationLetter.setText(currentCV.motivationLetter)



        return rootView
    }

}
package hu.rg.cvdatabase.fragment.preview

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.entities.CV
import hu.rg.cvdatabase.data.viewmodel.CVViewModel
import hu.rg.cvdatabase.fragment.update.UpdateFragmentArgs
import kotlinx.android.synthetic.main.fragment_preview.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class PreviewFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mCVViewModel: CVViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
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
            findNavController().navigate(R.id.action_previewFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.selectedCV.name}?")
        builder.setMessage("Are you sure you want to delete ${args.selectedCV.name} from the CVs?")
        builder.create().show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_preview, container, false)
        val selectedCV : CV = args.selectedCV
        mCVViewModel = ViewModelProvider(this).get(CVViewModel::class.java)
        val eduList = rootView.educationList
        val expList = rootView.experienceList
        val expAdapter = ExperienceListAdapter()
        val eduAdapter = EducationListAdapter()
        eduList.adapter = eduAdapter
        expList.adapter = expAdapter
        expList.layoutManager = LinearLayoutManager(requireContext())
        eduList.layoutManager = LinearLayoutManager(requireContext())

        mCVViewModel.getCVWithSchools(selectedCV.name).observe(viewLifecycleOwner, Observer {
            schools -> eduAdapter.setList(schools[0].schools)
        })

        mCVViewModel.getCVWithJobs(selectedCV.name).observe(viewLifecycleOwner, Observer {
                jobList -> expAdapter.setList(jobList[0].jobs)
        })


        rootView.nameTextField.setText(selectedCV.name)
        rootView.ageTextField.setText(selectedCV.age.toString())
        rootView.streetNameTextField.setText(selectedCV.address.streetName)
        rootView.streetNumberTextField.setText(selectedCV.address.streetNumber.toString())
        rootView.postalCodeTextField.setText(selectedCV.address.postalCode.toString())
        rootView.cityNameTextField.setText(selectedCV.address.cityName)
        rootView.skillOne.setText(selectedCV.skills.skillOne)
        rootView.skillTwo.setText(selectedCV.skills.skillTwo)
        rootView.skillThree.setText(selectedCV.skills.skillThree)
        rootView.languageOne.setText(selectedCV.languages.languageOneName)
        rootView.languageTwo.setText(selectedCV.languages.languageTwoName)
        rootView.languageThree.setText(selectedCV.languages.languageThreeName)
        rootView.levelOne.setText(selectedCV.languages.levelOne)
        rootView.levelTwo.setText(selectedCV.languages.levelTwo)
        rootView.levelThree.setText(selectedCV.languages.levelThree)
        rootView.motivationLetter.setText(selectedCV.motivationLetter)



        return rootView
    }
}
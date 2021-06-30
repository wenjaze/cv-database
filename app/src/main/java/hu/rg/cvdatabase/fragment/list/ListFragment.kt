package hu.rg.cvdatabase.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.entities.CV
import hu.rg.cvdatabase.data.viewmodel.CVViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_list, container, false)
        val mCVViewModel = ViewModelProvider(this).get(CVViewModel::class.java)
        val adapter = ListAdapter(mCVViewModel,requireContext())
        val recyclerView = rootView.recyclerView


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val spinner = rootView.spinner
        var selected: Int = 0

        val categories: MutableList<String> = ArrayList()
        categories.add("Language")
        categories.add("Company name")
        categories.add("School name")
        categories.add("Skills")
        val dataAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = dataAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selected = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        rootView.searchButton.setOnClickListener {
            when (selected){
                0 -> mCVViewModel.getCVsWithLanguage("%" + rootView.searchField.text.toString() + "%").observe(viewLifecycleOwner, Observer { cvs -> adapter.setList(cvs) })
                1 -> mCVViewModel.getCVWithCompanyName("%"+rootView.searchField.text+"%").observe(viewLifecycleOwner, Observer { cvs -> adapter.setList(cvs) })
                2 -> mCVViewModel.getCVWithSchoolName("%"+rootView.searchField.text+"%").observe(viewLifecycleOwner, Observer { cvs -> adapter.setList(cvs) })
                3 -> mCVViewModel.getCVsWithSkill("%"+rootView.searchField.text+"%").observe(viewLifecycleOwner, Observer { cvs -> adapter.setList(cvs) })
            }
        }

        mCVViewModel.getAllCVs.observe(viewLifecycleOwner, Observer { cvs ->
            adapter.setList(cvs)
        })


        rootView.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return rootView
    }


//    fun getCVsWithLanguage(cvs : List<CV>,queriedLanguage : String): List<CV> {
//        val selectedCVs = emptyList<CV>()
//        for (cv in cvs)
//        {
//            if (cv.languages.languageOneName == queriedLanguage || cv.languages.languageThreeName == queriedLanguage || cv.languages.languageTwoName == queriedLanguage ){
//                selectedCVs + cv
//            }
//        }
//        return cvs
//    }

}



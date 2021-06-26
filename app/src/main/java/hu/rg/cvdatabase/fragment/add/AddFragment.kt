package hu.rg.cvdatabase.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.CVViewModel
import hu.rg.cvdatabase.data.entities.Address
import hu.rg.cvdatabase.data.entities.CV
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

        rootView.addButton.setOnClickListener {
            addCVToDB()
        }

        return rootView
    }

    private fun addCVToDB() {
        val name = nameTextField.text.toString()
        val age = Integer.parseInt(ageTextField.text.toString())
        val streetName = streetNameTextField.text.toString()
        val streetNumber = Integer.parseInt(streetNumberTextField.text.toString())
        val postalCode = Integer.parseInt(postalCodeTextField.text.toString())
        val cityName = cityNameTextField.text.toString()

        if (inputCheck(name,age)){
            val cv = CV(name,age,Address(streetName,streetNumber,postalCode,cityName),getString(R.string.mot_letter))
            mcvViewModel.insertCV(cv)
            Toast.makeText(requireContext(),"CV succesfully added to database.",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(),"Please fill name and age fields at least.",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name : String, age : Int) : Boolean = !(TextUtils.isEmpty(name) && TextUtils.isEmpty(
        age.toString()
    ))

}
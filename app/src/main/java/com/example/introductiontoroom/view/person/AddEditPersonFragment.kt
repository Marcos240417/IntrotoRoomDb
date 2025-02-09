package com.example.introductiontoroom.view.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.introductiontoroom.data.model.PersonEntity
import com.example.introductiontoroom.databinding.FragmentAddEditPersonBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddEditPersonFragment(private val listener: AddEditPersonListener, private val personEntity: PersonEntity?) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddEditPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEditPersonBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (personEntity != null){
            setExistingDataOnUi(personEntity)
        }

        attachUiListener()
    }

    private fun setExistingDataOnUi(personEntity: PersonEntity){
        binding.personNameEt.setText(personEntity.name)
        binding.personAgeEt.setText(personEntity.age.toString())
        binding.personCityEt.setText(personEntity.city)
        binding.saveBtn.text = "Update"

    }

    private fun attachUiListener() {
        binding.saveBtn.setOnClickListener {
            val name = binding.personNameEt.text.toString()
            val age = binding.personAgeEt.text.toString()
            val city = binding.personCityEt.text.toString()
            if (name.isNotEmpty() && age.isNotEmpty() && city.isNotEmpty()) {
                val personEntity1 = PersonEntity(personEntity?.pId?: 0, name, age.toInt(), city)
                listener.onSavedBtnClicked(personEntity != null, personEntity1)
            }else
                Toast.makeText(it.context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    companion object {
        internal const val TAG = "AddEditPersonFragment"
    }

    interface AddEditPersonListener {
        fun onSavedBtnClicked(isUpdate: Boolean, personEntity: PersonEntity)
    }
}


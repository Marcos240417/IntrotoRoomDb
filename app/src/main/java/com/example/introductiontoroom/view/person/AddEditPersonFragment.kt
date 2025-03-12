package com.example.introductiontoroom.view.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.introductiontoroom.data.model.PersonEntity
import com.example.introductiontoroom.databinding.FragmentAddEditPersonBinding
import com.example.introductiontoroom.persontransformations.DateMask
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddEditPersonFragment(
    private val listener: AddEditPersonListener,
    private val personEntity: PersonEntity?
) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddEditPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEditPersonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aplica a máscara ao campo de data de nascimento
        binding.personDateBirth.addTextChangedListener(DateMask())

        if (personEntity != null) {
            setExistingDataOnUi(personEntity)
        }

        attachUiListener()
    }

    private fun setExistingDataOnUi(personEntity: PersonEntity) {
        binding.personNameEt.setText(personEntity.name)
        binding.personDateBirth.setText(personEntity.dateBirth)
        binding.personSusEt.setText(personEntity.city)
        binding.saveBtn.text = "Update"
    }

    private fun attachUiListener() {
        binding.saveBtn.setOnClickListener {
            val name = binding.personNameEt.text.toString()
            val dateBirth = binding.personDateBirth.text.toString()
            val city = binding.personSusEt.text.toString()
            val id = personEntity?.pId ?: 0

            if (name.isNotEmpty() && dateBirth.isNotEmpty() && city.isNotEmpty()) {
                val updatedPerson = PersonEntity(
                    id,
                    name,
                    dateBirth,
                    city
                )
                listener.onSavedBtnClicked(personEntity != null, updatedPerson)
            } else {
                Toast.makeText(it.context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
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

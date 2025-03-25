package com.example.introductiontoroom.introduction.view.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.introductiontoroom.databinding.FragmentAddEditPersonBinding
import com.example.introductiontoroom.introduction.persontransformations_fragment.ApplySusCardMask
import com.example.introductiontoroom.introduction.persontransformations_fragment.DateMask
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

        // Aplica a máscara ao campo de Nº do SUS
        binding.personSusEt.addTextChangedListener(ApplySusCardMask())

        // Se personEntity não for nulo, preenche os campos com os dados existentes
        personEntity?.let {
            setExistingDataOnUi(it)
        }

        attachUiListener()
    }

    private fun setExistingDataOnUi(personEntity: PersonEntity) {
        // Preenche os campos com os dados da pessoa
        binding.personNameEt.setText(personEntity.name)
        binding.personDateBirth.setText(personEntity.dateBirth) // Corrigido, sem o toString()
        binding.personSusEt.setText(personEntity.nsus) // Suponho que city seja o número do SUS
        binding.saveBtn.text = "Update" // Altera o texto do botão para "Update"
    }

    private fun attachUiListener() {
        binding.saveBtn.setOnClickListener {
            val name = binding.personNameEt.text.toString()
            val dateBirth = binding.personDateBirth.text.toString()
            val nsus = binding.personSusEt.text.toString() // Número do SUS
            val cep = "" // Adicione o campo de entrada correspondente no layout ou defina um valor padrão
            val logradouro = "" // Outros campos obrigatórios
            val number = ""
            val bairro = ""
            val cidade = ""
            val estado = ""
            val sexo = ""
            val maritalStatus = ""
            val nationality = ""
            val identityRG = ""
            val identityCPF = ""
            val phone = ""
            val email = ""

            val id = personEntity?.pId ?: 0

            if (name.isNotEmpty() && dateBirth.isNotEmpty() && nsus.isNotEmpty() && cep.isEmpty()
                && logradouro.isEmpty() && number.isEmpty() && bairro.isEmpty() && cidade.isEmpty()
                && estado.isEmpty() && sexo.isEmpty() && maritalStatus.isEmpty()
                && nationality.isEmpty() && identityRG.isEmpty() && identityCPF.isEmpty()
                && phone.isEmpty() && email.isEmpty()) {
                val updatedPerson = PersonEntity(
                    pId = id,
                    name = name,
                    dateBirth = dateBirth,
                    nsus = nsus,
                    cep = cep,
                    logradouro = logradouro,
                    number = number,
                    bairro = bairro,
                    cidade = cidade,
                    estado = estado,
                    sexo = sexo,
                    maritalStatus = maritalStatus,
                    nationality = nationality,
                    identityRG = identityRG,
                    identityCPF = identityCPF,
                    phone = phone,
                    email = email
                )
                listener.onSavedBtnClicked(personEntity != null, updatedPerson)
            } else {
                Toast.makeText(
                    it.context,
                    "Preencha todos os campos obrigatórios!",
                    Toast.LENGTH_SHORT
                ).show()
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

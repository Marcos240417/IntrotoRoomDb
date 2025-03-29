package com.example.ui_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.layout.AddressForm
import com.example.ui_compose.theme.IntroductionToRoomTheme
import com.example.ui_compose.ui.AddressViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivityCep : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recuperar dados do Intent
        val personId = intent.getIntExtra("person_id", 0) // Exemplo: ID da pessoa
        val personName = intent.getStringExtra("person_name") // Nome enviado
        val personDateBirth = intent.getStringExtra("person_date_birth") // Data de nascimento
        val personNsus = intent.getStringExtra("person_nsus") // Número do SUS

        // Use os dados conforme necessário
        // Exemplo: Logar os dados no console
        println("ID: $personId, Nome: $personName, Data Nascimento: $personDateBirth, SUS: $personNsus")

        setContent {
            IntroductionToRoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Obter instância do ViewModel com Koin
                    val addressViewModel = koinViewModel<AddressViewModel>()
                    val uiState = addressViewModel.uiState.collectAsState().value // Observa o estado
                    val coroutineScope = rememberCoroutineScope()



                    // Formulário de endereço
                    AddressForm(
                        uiState = uiState.selectedAddress ?: PersonEntity(
                            pId = personId,
                            name = personName ?: "",
                            dateBirth = personDateBirth ?: "",
                            nsus = personNsus ?: "",
                            cep = "",
                            logradouro = "",
                            number = "",
                            bairro = "",
                            cidade = "",
                            estado = "",
                            sexo = "",
                            maritalStatus = "",
                            nationality = "",
                            identityRG = "",
                            identityCPF = "",
                            phone = "",
                            email = ""
                        ), // Passa o estado completo que inclui os dados da API
                        onSearchAddressClick = { cep ->
                            addressViewModel.fetchAddressFromApi(cep)
                        },
                        onSaveAddressClick = { // Função para salvar no banco de dados
                            coroutineScope.launch {
                                // Chama a função do ViewModel para salvar ou atualizar no banco
                                addressViewModel.confirmSaveAddress()
                            }
                        }
                    )
                }
            }
        }
    }
}

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
import androidx.navigation.compose.rememberNavController
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.layout.AddressFormScreen
import com.example.ui_compose.theme.IntroductionToRoomTheme
import com.example.ui_compose.ui.AddressViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivityCep : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recuperar dados do Intent
        val personId = intent.getIntExtra("person_id", -1)
        val personName = intent.getStringExtra("person_name").orEmpty()
        val personDateBirth = intent.getStringExtra("person_date_birth").orEmpty()
        val personNsus = intent.getStringExtra("person_nsus").orEmpty()

        // Exibir logs para depuração
        println("ID: $personId, Nome: $personName, Data Nascimento: $personDateBirth, SUS: $personNsus")

        setContent {
            IntroductionToRoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Obter instância do ViewModel via Koin
                    val navController = rememberNavController()
                    val addressViewModel = koinViewModel<AddressViewModel>()
                    val uiState =
                        addressViewModel.searchedPersons.collectAsState(initial = emptyList()).value
                    val coroutineScope = rememberCoroutineScope()

                    // Verifica se há dados carregados, senão usa os valores da Intent
                    val selectedAddress = uiState.firstOrNull() ?: PersonEntity(
                        pId = personId,
                        name = personName,
                        dateBirth = personDateBirth,
                        nsus = personNsus,
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
                    )

                    // Tela de Formulário de Endereço (Entrada de Dados)
                    AddressFormScreen(
                        uiState = selectedAddress,

                        onSearchAddressClick = { cep, personEntity ->
                            addressViewModel.fetchAddressFromApi(cep, personEntity)
                        },
                        onSaveAddressClick = { updatedPerson -> // Agora recebe os dados atualizados
                            coroutineScope.launch {
                                addressViewModel.saveAddress(updatedPerson) // Salva corretamente
                                //navController.navigate("address_list_screen")
                            }
                        }
                    )
                }
            }
        }
    }
}


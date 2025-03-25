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

                    // Estado inicial da entidade Room
                    val initialPersonEntity = PersonEntity(
                        pId = 0,
                        name = "",
                        dateBirth = "",
                        nsus = "",
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
                        email = null
                    )

                    // Formulário de endereço
                    AddressForm(
                        uiState = uiState,
                        uiStateRoom = initialPersonEntity,
                        onSearchAddressClick = { cep ->
                            coroutineScope.launch {
                                addressViewModel.findAddress(cep, initialPersonEntity)
                            }
                        },
                        viewModel = null // Se necessário, passar o PersonViewModel aqui
                    )
                }
            }
        }
    }
}

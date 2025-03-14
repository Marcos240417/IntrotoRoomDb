package com.example.introductiontoroom.ui_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.introductiontoroom.ui_compose.layout.AddressForm
import com.example.introductiontoroom.ui_compose.ui.theme.IntroductionToRoomTheme
import com.example.introductiontoroom.viewmodel.AddressViewModel
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
                    val scope = rememberCoroutineScope()
                    val viewModel = koinViewModel<AddressViewModel>()
                    val uiState = viewModel.uiState.collectAsState().value
                    AddressForm(
                        uiState = uiState,
                        onSearchAddressClick = { cep ->
                            scope.launch {
                                viewModel.findAddress(cep)
                            }
                        },
                        onSaveClick = {
                            scope.launch {
                                // Implemente a lógica de salvamento aqui
                                viewModel.saveForm()
                            }
                        }
                    )

                }
            }
        }
    }
}


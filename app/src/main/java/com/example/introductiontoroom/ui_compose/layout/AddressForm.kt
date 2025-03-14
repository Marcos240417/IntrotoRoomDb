package com.example.introductiontoroom.ui_compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.ondefica.ui.transformations.CepVisualTransformation
import com.example.introductiontoroom.ui_compose.repositorys.state.AddressFormUiState
import com.example.introductiontoroom.ui_compose.ui.theme.IntroductionToRoomTheme

@Composable
fun AddressForm(
    uiState: AddressFormUiState,
    onSearchAddressClick: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxSize()) {
        when {
            uiState.isError -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                ) {
                    Text(
                        text = "Falha ao buscar o endereço",
                        Modifier
                            .padding(8.dp)
                            .align(Alignment.Center),
                        color = Color.White
                    )
                }
            }

            uiState.isLoading -> {
                Box(Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        Modifier
                            .padding(8.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
        Column(
            modifier
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val addressTextFieldModifier = Modifier
                .fillMaxWidth()
            var cep by remember {
                mutableStateOf("")
            }
            Row(
                modifier = Modifier
                    .width(300.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = cep,
                    onValueChange = {
                        if (it.length < 9) {
                            cep = it
                        }
                    },
                    Modifier.weight(1f),

                    label = {
                        Text(text = "CEP")
                    },
                    visualTransformation = CepVisualTransformation
                )
                Spacer(modifier = Modifier.width(28.dp)) // Espaçamento entre o TextField e o Icon

                IconButton(onClick = { onSearchAddressClick(cep) },
                    modifier = Modifier
                        .weight(0.5f) // Define um peso para o IconButton
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                        ) {
                    Icon(
                        Icons.Default.Search,
                        "lupa indicando ação de busca",
                        Modifier.fillMaxHeight()
                    )
                }
            }
            var logradouro by remember(uiState.logradouro) {
                mutableStateOf(uiState.logradouro)
            }
            TextField(
                value = logradouro,
                onValueChange = {
                    logradouro = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "Logradouro")
                }
            )
            var numero by remember {
                mutableStateOf("")
            }
            TextField(
                value = numero,
                onValueChange = {
                    numero = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "Número")
                }
            )
            var bairro by remember(uiState.bairro) {
                mutableStateOf(uiState.bairro)
            }
            TextField(
                value = bairro,
                onValueChange = {
                    bairro = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "Bairro")
                }
            )
            var cidade by remember(uiState.localidade) {
                mutableStateOf(uiState.localidade)
            }
            TextField(
                value = cidade,
                onValueChange = {
                    cidade = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "Cidade")
                }
            )
            var estado by remember(uiState.estado) {
                mutableStateOf(uiState.estado)
            }
            TextField(
                value = estado,
                onValueChange = {
                    estado = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "Estado")
                }
            )
        }
        // Botão de Salvar Cadastro
        Button(
            onClick = { onSaveClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Salvar Cadastro")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddressFormPreview() {
    IntroductionToRoomTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AddressForm(
                uiState = AddressFormUiState(),
                onSearchAddressClick = {},
                onSaveClick = {}
            )
        }
    }
}
package com.example.ui_compose.layout

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.ondefica.ui.transformations.CepVisualTransformation
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.theme.IntroductionToRoomTheme


@Composable
fun AddressForm(

    uiState: PersonEntity,
    onSaveAddressClick: (PersonEntity) -> Unit, // Definindo que a função aceita um PersonEntity
    onSearchAddressClick: (String) -> Unit,
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
            val textFieldModifier = Modifier.fillMaxWidth()

            // Usando Koin para obter o ViewModel

            var name by remember { mutableStateOf(uiState.name) }
            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = textFieldModifier,
                label = { Text(text = "Nome") }
            )


            var dateBirth by remember(uiState.dateBirth)
            { mutableStateOf(uiState.dateBirth) }
            TextField(
                value = dateBirth,
                onValueChange = { dateBirth = uiState.dateBirth },
                textFieldModifier,
                label = { Text(text = "Data de Nascimento") }
            )

            var nsus by remember(uiState.nsus)
            { mutableStateOf(uiState.nsus) }
            TextField(
                value = nsus,
                onValueChange = { nsus = uiState.nsus },
                textFieldModifier,
                label = { Text(text = "Número do SUS") }
            )

            var cep by remember {
                mutableStateOf("")
            }
            Row(
                Modifier.fillMaxWidth(),
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
                IconButton(onClick = { onSearchAddressClick(cep) }) {
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
            var cidade by remember(uiState.cidade) {
                mutableStateOf(uiState.cidade)
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

            var sexo by remember(uiState.sexo) {
                mutableStateOf(uiState.sexo)
            }
            TextField(
                value = sexo,
                onValueChange = {
                    sexo = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "Sexo")
                }
            )

            var maritalstatus by remember(uiState.maritalStatus) {
                mutableStateOf(uiState.maritalStatus)
            }
            TextField(
                value = maritalstatus,
                onValueChange = {
                    maritalstatus = it
                },
                addressTextFieldModifier,
                label = { Text(text = "Estado Civil") })

            var nationality by remember(uiState.nationality) {
                mutableStateOf(uiState.nationality)
            }
            TextField(
                value = nationality,
                onValueChange = {
                    nationality = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "Nacionalidade")
                }
            )
            var identityRG by remember(uiState.identityRG) {
                mutableStateOf(uiState.identityRG)
            }
            TextField(
                value = identityRG,
                onValueChange = {
                    identityRG = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "RG")
                })
            var identityCPF by remember(uiState.identityCPF) {
                mutableStateOf(uiState.identityCPF)
            }
            TextField(
                value = identityCPF,
                onValueChange = {
                    identityCPF = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "CPF")
                })

            var phone by remember(uiState.phone) {
                mutableStateOf(uiState.phone)
            }
            TextField(
                value = phone,
                onValueChange = {
                    phone = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "Celular")
                }
            )
            var email by remember(uiState.email) {
                mutableStateOf(uiState.email)
            }
            TextField(
                value = email?: "",
                onValueChange = {
                    email = it
                },
                addressTextFieldModifier,
                label = {
                    Text(text = "Email")
                })

            // Botão para salvar os dados
            val context = LocalContext.current
            Button(
                onClick = {
                    if (cep.isNotBlank() && uiState.logradouro.isNotBlank() && uiState.number.isNotBlank()
                        && uiState.bairro.isNotBlank() && uiState.cidade.isNotBlank() && uiState.estado.isNotBlank()

                    ) {
                        // Não é necessário criar a variável `personEntity` separada
                        onSaveAddressClick(
                            uiState.copy(cep = cep)
                        ) // Enviando diretamente os dados para salvar
                    } else {
                        // Exibe uma mensagem de erro caso algum campo necessário esteja vazio
                        Toast.makeText(
                            context,
                            "Preencha todos os campos obrigatórios!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Salvar Cadastro")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressFormPreview() {
    IntroductionToRoomTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AddressForm(
                uiState = PersonEntity(
                    pId = 1,
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
                    email = ""
                ),

                onSearchAddressClick = {},
                onSaveAddressClick = {},
                modifier = Modifier,

            )
        }
    }
}
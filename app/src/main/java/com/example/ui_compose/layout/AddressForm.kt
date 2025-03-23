package com.example.ui_compose.layout

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.ondefica.ui.transformations.CepVisualTransformation
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.introductiontoroom.introduction.viewmodel.PersonViewModel
import com.example.ui_compose.dataaddres.model.AddressResponse
import com.example.ui_compose.theme.IntroductionToRoomTheme


@Composable
fun AddressForm(
    uiState: AddressResponse,
    uiStateRoom: PersonEntity,
    onSearchAddressClick: (String) -> Unit,
    viewModel: PersonViewModel? = null, // Torna o ViewModel opcional
    modifier : Modifier = Modifier,
) {
    // Obter o contexto correto para exibir Toast
    val context = LocalContext.current

    // Variáveis observáveis para os campos do formulário
    var cep by remember { mutableStateOf(uiStateRoom.cep) }
    var rua by remember { mutableStateOf(uiState.logradouro) }
    var numero by remember { mutableStateOf(uiStateRoom.number) }
    var bairro by remember { mutableStateOf(uiState.bairro) }
    var cidade by remember { mutableStateOf(uiState.localidade) }
    var estado by remember { mutableStateOf(uiState.estado) }
    var sexo by remember { mutableStateOf("") }
    var maritalstatus by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var identityRG by remember { mutableStateOf("") }
    var identityCPF by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier.fillMaxSize()) {
        // Tratamento de estados de erro e carregamento
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
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }

        // Formulário de entrada
        Column(
            modifier
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val addressTextFieldModifier = Modifier.fillMaxWidth()

            // Campo de CEP com botão de busca
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
                Spacer(modifier = Modifier.width(8.dp)) // Espaço entre o campo e o ícone

                IconButton(onClick = { onSearchAddressClick(cep) }
                ) {
                    Icon(
                        Icons.Default.Search,
                        "lupa indicando ação de busca",
                        Modifier.fillMaxHeight()


                    )
                }
            }

            // Campos de Logradouro, Número, Bairro, Cidade e Estado
            TextField(
                value = rua,
                onValueChange = { rua = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Logradouro") }
            )

            TextField(
                value = numero,
                onValueChange = { numero = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Número") }
            )

            TextField(
                value = bairro,
                onValueChange = { bairro = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Bairro") }
            )

            TextField(
                value = cidade,
                onValueChange = { cidade = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Cidade") }
            )

            TextField(
                value = estado,
                onValueChange = { estado = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Estado") }
            )

            // Adiciona campos para informações adicionais
            TextField(
                value = sexo,
                onValueChange = { sexo = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Sexo") }
            )

            TextField(
                value = maritalstatus,
                onValueChange = { maritalstatus = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Estado Civil") }
            )

            TextField(
                value = nationality,
                onValueChange = { nationality = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Nacionalidade") }
            )

            TextField(
                value = identityRG,
                onValueChange = { identityRG = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "RG") }
            )

            TextField(
                value = identityCPF,
                onValueChange = { identityCPF = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "CPF") }
            )

            TextField(
                value = phone,
                onValueChange = { phone = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Celular") }
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = addressTextFieldModifier,
                label = { Text(text = "Email") }
            )

            // Botão para salvar os dados
            Button(
                onClick = {
                    if (cep.isNotBlank() && rua.isNotBlank() && numero.isNotBlank() && bairro.isNotBlank() && cidade.isNotBlank() && estado.isNotBlank()) {
                        val personEntity = PersonEntity(
                            pId = uiStateRoom.pId,
                            name = uiStateRoom.name,
                            dateBirth = uiStateRoom.dateBirth,
                            nsus = uiStateRoom.nsus,
                            cep = cep,
                            logradouro = rua,
                            number = numero,
                            bairro = bairro,
                            cidade = cidade,
                            estado = estado,
                            sexo = sexo,
                            maritalStatus = maritalstatus,
                            nationality = nationality,
                            identityRG = identityRG,
                            identityCPF = identityCPF,
                            phone = phone,
                            email = email
                        )

                        // Chama a função do ViewModel para salvar ou atualizar
                        viewModel?.let {
                            if (personEntity.pId == 0) {
                                it.addPerson(personEntity) // Adiciona nova pessoa
                            } else {
                                it.updatePerson(personEntity) // Atualiza pessoa existente
                            }
                        } ?: Toast.makeText(context, "ViewModel não disponível", Toast.LENGTH_SHORT).show()
                    } else {
                        // Mensagem de erro
                        Toast.makeText(context, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show()
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
                uiState = AddressResponse(
                    logradouro = "Rua Exemplo",
                    bairro = "Bairro Exemplo",
                    localidade = "Cidade Exemplo",
                    estado = "Estado Exemplo",
                    isLoading = false,
                    isError = false,
                    data = arrayListOf()
                ),
                onSearchAddressClick = {},
                viewModel = null, // No preview, ViewModel é opcional
                uiStateRoom = PersonEntity(
                    pId = 1,
                    name = "Nome Exemplo",
                    dateBirth = "01/01/1990",
                    nsus = "123456789",
                    cep = "12345678",
                    logradouro = "Rua Exemplo",
                    number = "10",
                    bairro = "Bairro Exemplo",
                    cidade = "Cidade Exemplo",
                    estado = "Estado Exemplo",
                    sexo = "Masculino",
                    maritalStatus = "Solteiro",
                    nationality = "Brasileira",
                    identityRG = "123456789",
                    identityCPF = "987654321",
                    phone = "1234567890",
                    email = "email@exemplo.com"
                )
            )
        }
    }
}

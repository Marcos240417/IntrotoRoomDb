package com.example.introductiontoroom.ui_compose.repositorys.state

data class AddressFormUiState(
    val logradouro: String = "",
    val bairro: String = "",
    val localidade: String = "",
    val estado: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
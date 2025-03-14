package com.example.introductiontoroom.ui_compose.repositorys.response

import com.example.introductiontoroom.ui_compose.network.AddressService

class AddressRoomRepository(private val api: AddressService,
                            private val addressDao: AddressDao,
                            private val addressService: AddressService
) {
    suspend fun buscarEConverterEndereco(cep: String): Registrationform? {
        try {
            // Chamada à API via Retrofit
            val addressResponse = api.findAddress(cep)

            // Conversão para Registrationform
            val registrationForm = addressResponse.toRegistrationform(
                cep = cep,
                sexo = "Masculino",          // Parâmetros fornecidos pelo seu app
                maritalStatus = "Solteiro", // ou pelo usuário
                nationality = "Brasileira",
                identityRG = 1234567,
                identityCPF = 987654321,
                phone = 987654321,
                email = "exemplo@email.com"
            )

            // Salvar no banco de dados Room
            addressDao.saveAddress(registrationForm)

            // Retornar o objeto Registrationform
            return registrationForm
        } catch (e: Exception) {
            // Tratamento de erro, se necessário
            e.printStackTrace()
            return null
        }
    }

}
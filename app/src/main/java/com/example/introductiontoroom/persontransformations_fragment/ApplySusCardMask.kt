package com.example.introductiontoroom.persontransformations_fragment

import android.text.Editable
import android.text.TextWatcher

class ApplySusCardMask : TextWatcher {
    // Variável para evitar loops infinitos durante a atualização do texto
    private var isUpdating = false

    // Método chamado antes de o texto ser alterado (não é usado nesse caso)
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    // Método chamado quando o texto está sendo alterado (não é usado nesse caso)
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    // Método chamado depois que o texto é alterado
    override fun afterTextChanged(s: Editable?) {
        // Evita que a função seja chamada novamente enquanto o texto está sendo atualizado
        if (isUpdating) {
            return
        }

        // Filtra apenas os números do texto, removendo qualquer caractere não numéricos
        val text = s.toString().filter { it.isDigit() }
        // StringBuilder usado para construir a string formatada
        val masked = StringBuilder()

        var i = 0
        // Verifica o comprimento do texto para aplicar a máscara
        // Adiciona um espaço após os primeiros 3 dígitos
        if (text.length > 2) {
            masked.append(text.substring(0, 3)).append(" ")
            i = 3
        }
        // Adiciona um espaço após os próximos 4 dígitos (do 4º ao 6º)
        if (text.length > 6) {
            masked.append(text.substring(3, 7)).append(" ")
            i = 7
        }
        if (text.length > 10) {
            masked.append(text.substring(7, 11)).append(" ")
            i = 11
        }
        // Adiciona o restante dos dígitos sem mais espaços
        if (i < text.length) {
            masked.append(text.substring(i))
        }

        // Marca que o texto está sendo atualizado para evitar loop infinito
        isUpdating = true

        // Substitui o texto original pelo texto formatado com a máscara
        s?.replace(0, s.length, masked.toString())

        // Libera a variável para futuras edições
        isUpdating = false
    }
}

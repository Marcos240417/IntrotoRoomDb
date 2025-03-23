package com.example.introductiontoroom.introduction.persontransformations_fragment

import android.text.Editable
import android.text.TextWatcher

class DateMask : TextWatcher {
    // Variável para controlar se o TextWatcher está atualizando o texto, evitando loops infinitos
    private var isUpdating = false

    // Este método é chamado antes de o texto ser alterado
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    // Este método é chamado enquanto o texto está sendo alterado
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    // Este método é chamado depois que o texto foi alterado
    override fun afterTextChanged(s: Editable?) {
        // Verifica se já estamos no processo de atualizar o texto, se sim, sai da função para evitar loops infinitos
        if (isUpdating) {
            return
        }

        // Converte o texto para String e filtra apenas os dígitos (remove caracteres não numéricos)
        val text = s.toString().filter { it.isDigit() }

        // StringBuilder para construir o texto com a máscara
        val masked = StringBuilder()

        // Variável para controlar a posição de corte do texto
        var i = 0

        // Adiciona a primeira parte da máscara, se o texto tiver mais de 2 caracteres
        if (text.length > 2) {
            masked.append(text.substring(0, 2)).append("/")  // Adiciona "/"
            i = 2  // Atualiza o índice para a próxima parte
        }

        // Adiciona a segunda parte da máscara, se o texto tiver mais de 4 caracteres
        if (text.length > 4) {
            masked.append(text.substring(2, 4)).append("/")  // Adiciona "/"
            i = 4  // Atualiza o índice
        }

        // Adiciona o restante do texto, após o índice calculado
        if (i < text.length) {
            masked.append(text.substring(i))
        }

        // Marca que estamos atualizando o texto para evitar que o código entre em um loop de atualização
        isUpdating = true

        // Substitui o texto original pela string mascarada
        s?.replace(0, s.length, masked.toString())

        // Marca que a atualização foi concluída
        isUpdating = false
    }
}


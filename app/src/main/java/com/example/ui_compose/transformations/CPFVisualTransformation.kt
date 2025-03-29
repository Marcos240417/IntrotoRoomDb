package com.example.ui_compose.transformations

// Importações necessárias para trabalhar com texto e transformação no Jetpack Compose.
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

// Objeto CPFVisualTransformation: Implementa a interface VisualTransformation
// É responsável por aplicar a máscara de CPF (123.456.789-XX) visualmente em um TextField.
object CPFVisualTransformation : VisualTransformation {

    // Sobrescreve o método "filter", que recebe o texto digitado pelo usuário.
    override fun filter(text: AnnotatedString): TransformedText {
        // "trimmed" corta o texto digitado para no máximo 11 caracteres (tamanho do CPF sem máscara).
        val trimmed = text.text.take(11)

        // "formatted" constrói o texto formatado com a máscara de CPF.
        val formatted = buildString {
            // Itera sobre os índices dos caracteres do texto "trimmed".
            for (i in trimmed.indices) {
                // Adiciona o dígito atual ao texto formatado.
                append(trimmed[i])
                // Adiciona um ponto "." após o 3º e o 6º dígitos.
                if (i == 2 || i == 5) append(".")
                // Adiciona um traço "-" após o 9º dígito.
                if (i == 8) append("-")
            }
        }

        // "offsetMap" é responsável por mapear as posições entre o texto original (apenas números)
        // e o texto transformado (com máscara de CPF).
        val offsetMap = object : OffsetMapping {

            // Define como o índice do texto original é mapeado para o texto transformado.
            override fun originalToTransformed(offset: Int): Int = when {
                offset <= 2 -> offset                 // Antes do primeiro ponto, índice não muda.
                offset <= 5 -> offset + 1            // Após o primeiro ponto, índice é incrementado em 1.
                offset <= 8 -> offset + 2            // Após o segundo ponto, índice é incrementado em 2.
                else -> offset + 3                   // Após o traço, índice é incrementado em 3.
            }

            // Define como o índice do texto transformado é mapeado de volta para o texto original.
            override fun transformedToOriginal(offset: Int): Int = when {
                offset <= 2 -> offset                // Antes do primeiro ponto, índice não muda.
                offset <= 6 -> offset - 1            // Após o primeiro ponto, índice é decrementado em 1.
                offset <= 10 -> offset - 2           // Após o segundo ponto, índice é decrementado em 2.
                else -> offset - 3                   // Após o traço, índice é decrementado em 3.
            }
        }

        // Retorna um TransformedText contendo o texto formatado e o mapeamento dos índices (offsetMap).
        return TransformedText(AnnotatedString(formatted), offsetMap)
    }
}

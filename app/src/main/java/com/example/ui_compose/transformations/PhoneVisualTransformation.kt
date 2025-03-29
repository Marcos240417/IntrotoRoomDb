package com.example.ui_compose.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

// Objeto responsável por aplicar a transformação visual para números de celular no formato (00) 00000-0000
object PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Filtra apenas números e limita o número digitado a 11 dígitos
        val trimmed = text.text.filter { it.isDigit() }.take(11)

        // Se o texto estiver vazio, retorna uma string vazia para evitar o erro
        if (trimmed.isEmpty()) {
            return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
        }

        // Formata o texto no padrão (00) 00000-0000
        val formatted = buildString {
            for (i in trimmed.indices) {
                when (i) {
                    0 -> append("(")          // Adiciona "(" antes do primeiro dígito
                    2 -> append(") ")         // Adiciona ") " após o segundo dígito
                    7 -> append("-")          // Adiciona "-" após o sétimo dígito
                }
                append(trimmed[i])            // Adiciona o dígito ao texto formatado
            }
        }

        // Define o comportamento do cursor entre o texto original e o transformado
        val offsetMap = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 0) return 0 // Se o cursor estiver no início, mantém no início
                return when {
                    offset <= 2 -> offset + 1  // Ajusta para incluir "("
                    offset <= 7 -> offset + 3  // Ajusta para incluir ") "
                    offset <= 11 -> offset + 4 // Ajusta para incluir "-"
                    else -> formatted.length   // Cursor no final do texto
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset == 0) return 0 // Se o cursor estiver no início, mantém no início
                return when {
                    offset <= 3 -> offset - 1  // Ajusta para os dois primeiros dígitos
                    offset <= 8 -> offset - 3  // Ajusta para os dígitos após ") "
                    offset <= 13 -> offset - 4 // Ajusta para os últimos dígitos
                    else -> trimmed.length     // Ajusta para o final do texto
                }
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMap)
    }
}

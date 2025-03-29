package com.example.ui_compose.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object RGVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(7) // Limita o RG a 7 dígitos
        val formatted = buildString {
            for (i in trimmed.indices) {
                append(trimmed[i])
                if (i == 0 || i == 3) append(".") // Adiciona pontos após os índices 0 e 3
            }
        }

        val offsetMap = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = when {
                offset <= 1 -> offset // Antes do primeiro ponto
                offset <= 4 -> offset + 1 // Entre o primeiro e o segundo ponto
                offset <= 7 -> offset + 2 // Após o segundo ponto
                else -> formatted.length // Final
            }

            override fun transformedToOriginal(offset: Int): Int = when {
                offset <= 1 -> offset // Antes do primeiro ponto
                offset <= 5 -> offset - 1 // Entre o primeiro e o segundo ponto
                offset <= 9 -> offset - 2 // Após o segundo ponto
                else -> trimmed.length // Final
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMap)
    }
}


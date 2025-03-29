package com.example.ui_compose.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CPFVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(11)
        val formatted = buildString {
            for (i in trimmed.indices) {
                append(trimmed[i])
                if (i == 2 || i == 5) append(".")
                if (i == 8) append("-")
            }
        }
        val offsetMap = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = when {
                offset <= 2 -> offset
                offset <= 5 -> offset + 1
                offset <= 8 -> offset + 2
                else -> offset + 3
            }
            override fun transformedToOriginal(offset: Int): Int = when {
                offset <= 2 -> offset
                offset <= 6 -> offset - 1
                offset <= 10 -> offset - 2
                else -> offset - 3
            }
        }
        return TransformedText(AnnotatedString(formatted), offsetMap)
    }
}

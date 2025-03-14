package com.example.introductiontoroom.ui_compose.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SaveButton(onSaveClick: () -> Unit) {
    Button(
        onClick = { onSaveClick() },
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = "Salvar Cadastro")
    }
}

@Preview
@Composable
private fun SaveButtonPreview() {

}
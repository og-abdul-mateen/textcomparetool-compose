package com.chickenpiecestudio.textcomparetool.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.chickenpiecestudio.textcomparetool.R

/**
 * Composable function which renders an input box where users can enter text
 *
 * Parameters:
 * [text] to be shown in the input box
 * [onTextChange] a callback that is triggered every time the text changes
 * [visualTransformation] VisualTransformation to apply on the text (e.g. to show diff text)
 * [enabled] whether user interaction is enabled or not
 *
 */
@Composable
fun DiffInputBox(
    text: String,
    onTextChange: (String) -> Unit,
    visualTransformation: VisualTransformation,
    enabled: Boolean = true
) {


    OutlinedTextField(
        value = text,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        placeholder = { Text(text = stringResource(id = R.string.enter_text_here)) },
        visualTransformation = visualTransformation,
        readOnly = !enabled,
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.surface
        )
    )

}

package com.chickenpiecestudio.textcomparetool

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chickenpiecestudio.textcomparetool.ui.theme.TextCompareToolCompareTextAndFindDiffTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextCompareToolCompareTextAndFindDiffTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        DefaultPreview()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun InputBox(defaultText: String = "") {
    var text by remember { mutableStateOf(defaultText) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        placeholder = { Text(text = stringResource(id = R.string.enter_text_here)) }
    )
}

@Composable
fun PrimaryButton() {
    val context = LocalContext.current

    Button(
        onClick = { Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show() },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            all = 12.dp
        )
    ) {
        Text(text = stringResource(id = R.string.find_diff))
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextCompareToolCompareTextAndFindDiffTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .weight(4f)
                    .padding( top = 12.dp, bottom = 6.dp)
            ) {
                InputBox()
            }
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .weight(4f)
                    .padding(top = 6.dp, bottom = 12.dp)
            ) {
                InputBox()
            }
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
            ) {
                PrimaryButton()
            }
        }
    }
}
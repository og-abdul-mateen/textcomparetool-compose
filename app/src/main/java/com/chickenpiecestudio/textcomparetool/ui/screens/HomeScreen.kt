package com.chickenpiecestudio.textcomparetool.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chickenpiecestudio.textcomparetool.R
import com.chickenpiecestudio.textcomparetool.ui.components.DiffInputBox
import com.chickenpiecestudio.textcomparetool.ui.components.PrimaryButton
import com.chickenpiecestudio.textcomparetool.ui.theme.DiffGreen
import com.chickenpiecestudio.textcomparetool.ui.theme.DiffRed
import com.chickenpiecestudio.textcomparetool.ui.theme.Primary
import com.chickenpiecestudio.textcomparetool.ui.theme.TextCompareToolCompareTextAndFindDiffTheme
import com.chickenpiecestudio.textcomparetool.utils.Constants
import com.chickenpiecestudio.textcomparetool.utils.DiffTransformation
import com.chickenpiecestudio.textcomparetool.viewmodels.DiffViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(diffViewModel: DiffViewModel = viewModel()) {

    TextCompareToolCompareTextAndFindDiffTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .weight(4f)
                    .padding(top = 12.dp, bottom = 6.dp)
            ) {
                DiffInputBox(
                    text = diffViewModel.diffUiState.text1,
                    onTextChange = { text -> diffViewModel.setText1(text) },
                    visualTransformation = DiffTransformation(
                        Constants.Delimiters.DELIMITER_OLD,
                        normalColor = MaterialTheme.colors.onSurface,
                        diffColor = DiffRed
                    ),
                    enabled = diffViewModel.diffUiState.textEnabled1
                )
            }
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .weight(4f)
                    .padding(top = 6.dp, bottom = 12.dp)
            ) {
                DiffInputBox(
                    text = diffViewModel.diffUiState.text2,
                    onTextChange = { text -> diffViewModel.setText2(text) },
                    visualTransformation = DiffTransformation(
                        Constants.Delimiters.DELIMITER_NEW,
                        normalColor = MaterialTheme.colors.onSurface,
                        diffColor = DiffGreen
                    ),
                    enabled = diffViewModel.diffUiState.textEnabled2
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
            ) {
                PrimaryButton(
                    title = if (diffViewModel.diffUiState.calculationCompleted) stringResource(id = R.string.clear) else stringResource(
                        id = R.string.find_diff
                    ),
                    onClick = {
                        onButtonClick(
                            diffViewModel = diffViewModel,
                            text1 = diffViewModel.diffUiState.text1,
                            text2 = diffViewModel.diffUiState.text2
                        )
                    },
                    backgroundColor = if (diffViewModel.diffUiState.calculationCompleted) {
                        Color.Red
                    } else {
                        Primary
                    },
                    textColor = Color.White
                )
            }
        }
    }
}


fun onButtonClick(diffViewModel: DiffViewModel, text1: String, text2: String) {
    diffViewModel.calculateDiff2(text1, text2)
}
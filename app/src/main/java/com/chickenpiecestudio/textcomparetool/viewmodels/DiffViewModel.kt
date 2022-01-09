package com.chickenpiecestudio.textcomparetool.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.chickenpiecestudio.textcomparetool.ui.state.DiffUiState
import com.chickenpiecestudio.textcomparetool.utils.Constants
import com.github.difflib.DiffUtils
import com.github.difflib.UnifiedDiffUtils
import com.github.difflib.patch.Patch
import com.github.difflib.text.DiffRow
import com.github.difflib.text.DiffRowGenerator


class DiffViewModel : ViewModel() {
    var diffUiState by mutableStateOf<DiffUiState>(
        DiffUiState(
            text1 = "",
            text2 = "",
            isLoading = false,
            textEnabled1 = true,
            textEnabled2 = true,
            calculationCompleted = false
        )
    )
        private set

    fun calculateDiff(text1: String, text2: String) {
        //build simple lists of the lines of the two testfiles
        //build simple lists of the lines of the two testfiles
        val original: List<String> = text1.lines()
        val revised: List<String> = text2.lines()

//compute the patch: this is the diffutils part

//compute the patch: this is the diffutils part
        val patch: Patch<String> = DiffUtils.diff(original, revised)

//simple output the computed patch to console

//simple output the computed patch to console
        for (delta in patch.getDeltas()) {
            System.out.println(delta)
        }

    }

    fun calculateDiff1(text1: String, text2: String) {
        val original: List<String> = text1.lines()
        val patched: List<String> = text2.lines()

// At first, parse the unified diff file and get the patch

// At first, parse the unified diff file and get the patch
        val patch: Patch<String> = UnifiedDiffUtils.parseUnifiedDiff(patched)

// Then apply the computed patch to the given text

// Then apply the computed patch to the given text
        val result: List<String> = DiffUtils.patch(original, patch)

//simple output to console

//simple output to console
        println(result)

    }


    fun calculateDiff2(text1: String, text2: String) {
        if (diffUiState.calculationCompleted) {
            clear()
            return
        }

        val generator: DiffRowGenerator = DiffRowGenerator.create()
            .showInlineDiffs(true)
            .inlineDiffByWord(false)
            .ignoreWhiteSpaces(false)
            .oldTag { Constants.Delimiters.DELIMITER_OLD }
            .newTag { Constants.Delimiters.DELIMITER_NEW }
            .build()
        val rows: List<DiffRow> = generator.generateDiffRows(
            text1.lines(),
            text2.lines()
        )

        println("|original|new|")
        println("|--------|---|")

        val text1 = rows.map { row -> row.oldLine }.joinToString("\n")
        val text2 = rows.map { row -> row.newLine }.joinToString("\n")

        setText1(text1)
        setText2(text2)

        setTextEditing(false)
        setCalculationComplete(true)

        for (row in rows) {
            System.out.println(
                "|" + row.oldLine.toString() + "|" + row.newLine.toString() + "|"
            )
        }
    }

    fun setText1(text: String) {
        Log.d("findMe Text1: ", text)

        diffUiState = DiffUiState(
            text1 = text,
            text2 = diffUiState.text2,
            isLoading = diffUiState.isLoading,
            textEnabled1 = diffUiState.textEnabled1,
            textEnabled2 = diffUiState.textEnabled2,
            calculationCompleted = false
        )
    }

    fun setText2(text: String) {
        Log.d("findMe Text2: ", text)

        diffUiState = DiffUiState(
            text1 = diffUiState.text1,
            text2 = text,
            isLoading = diffUiState.isLoading,
            textEnabled1 = diffUiState.textEnabled1,
            textEnabled2 = diffUiState.textEnabled2,
            calculationCompleted = false
        )
    }

    fun setTextEditing(enabled: Boolean) {
        diffUiState = DiffUiState(
            text1 = diffUiState.text1,
            text2 = diffUiState.text2,
            isLoading = diffUiState.isLoading,
            textEnabled1 = enabled,
            textEnabled2 = enabled,
            calculationCompleted = false
        )
    }

    private fun setCalculationComplete(complete: Boolean) {
        diffUiState = DiffUiState(
            text1 = diffUiState.text1,
            text2 = diffUiState.text2,
            isLoading = diffUiState.isLoading,
            textEnabled1 = diffUiState.textEnabled1,
            textEnabled2 = diffUiState.textEnabled2,
            calculationCompleted = complete
        )
    }

    fun clear() {
        diffUiState = DiffUiState(
            text1 = "",
            text2 = "",
            isLoading = false,
            textEnabled1 = true,
            textEnabled2 = true,
            calculationCompleted = false
        )
    }

}
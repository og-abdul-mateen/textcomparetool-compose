package com.chickenpiecestudio.textcomparetool.utils

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

class DiffTransformation(private val delimiter: String, private val normalColor: Color, private val diffColor: Color): VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            buildDiffFormattedString(text.toString()),
            generateDiffOffsetTranslator(text.toString())/*OffsetMapping.Identity*/)
    }

    private var totalDelimiterLength = 0

    private fun generateDiffOffsetTranslator(text: String): OffsetMapping {

        return object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                Log.d("findMe offset1: ", "$offset")
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                Log.d("findMe offset2: ", "$offset")
                return offset
            }
        }

    }

    private val diffOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 4) return offset + 1
            return offset + 2
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 5) return offset - 1
            return offset - 2
        }
    }

    /**
     * Formats the provided [text] in diff format and
     * returns an [AnnotatedString] with diff formatting
     */
    private fun buildDiffFormattedString(text:String): AnnotatedString{
        val builder = AnnotatedString.Builder()

        if (text.isBlank()) {
            return builder.toAnnotatedString()
        }


        // split the text on the basis of delimiter
        val tokens: List<String> = text.split(delimiter.toRegex())
        val colors = listOf(normalColor, diffColor)

        for ((count, token) in tokens.withIndex()) {
            if (count%2 == 1) {
                totalDelimiterLength += delimiter.length
            }
            builder.withStyle(style = SpanStyle(color = colors[count%2])) {
                append(token)
            }
        }

        return builder.toAnnotatedString()
    }
}
package com.chickenpiecestudio.textcomparetool.ui.state

data class DiffUiState(
    val text1: String,
    val text2: String,
    val isLoading: Boolean,
    val textEnabled1: Boolean,
    val textEnabled2: Boolean,
    val calculationCompleted: Boolean
)
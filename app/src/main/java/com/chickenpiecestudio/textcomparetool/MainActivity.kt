package com.chickenpiecestudio.textcomparetool

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chickenpiecestudio.textcomparetool.ui.screens.HomeScreenPreview
import com.chickenpiecestudio.textcomparetool.ui.setStatusBarColor
import com.chickenpiecestudio.textcomparetool.ui.theme.TextCompareToolCompareTextAndFindDiffTheme
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adjustResizeSoftKeyboard()

        setContent {
            TextCompareToolCompareTextAndFindDiffTheme {
                setStatusBarColor()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        HomeScreenPreview()
                    }
                }
            }
        }
    }

    private fun initAdMob() {
        MobileAds.initialize(this) {
            
        }
    }

    private fun adjustResizeSoftKeyboard() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }
}
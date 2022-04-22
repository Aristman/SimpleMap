package ru.marslab.simplemap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.marslab.simplemap.feature.mainmap.presentation.MainMapView
import ru.marslab.simplemap.ui.theme.SimpleMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMapTheme {
                MainMapView()
            }
        }
    }
}

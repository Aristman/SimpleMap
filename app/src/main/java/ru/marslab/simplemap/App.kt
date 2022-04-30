package ru.marslab.simplemap

import android.app.Application
import androidx.datastore.preferences.core.doublePreferencesKey
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        const val dataStoreName = "store_name"
        val locationLatKey = doublePreferencesKey("location_lat")
        val locationLonKey = doublePreferencesKey("location_lon")
    }
}

package ru.marslab.simplemap

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.simplemap.feature.mainmap.presentation.MainMapView
import ru.marslab.simplemap.ui.theme.SimpleMapTheme
import javax.inject.Inject

private const val LOCATION_REQUEST_PERIOD = 1000L

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: DataStore<Preferences>

    private val locationListener = object : LocationListener {
        override fun onLocationUpdated(p0: Location) {
            storeLocation(p0)
        }

        override fun onLocationStatusUpdated(p0: LocationStatus) {
            when (p0) {
                LocationStatus.NOT_AVAILABLE -> {}
                LocationStatus.AVAILABLE -> {}
            }
        }
    }
    private val mapLocationManager by lazy {
        MapKitFactory
            .getInstance()
            .createLocationManager()
    }

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey(BuildConfig.yandex_map_api_key)
        MapKitFactory.initialize(this)
        mapLocationManager.subscribeForLocationUpdates(
            0.0,
            LOCATION_REQUEST_PERIOD,
            0.0,
            true,
            FilteringMode.ON,
            locationListener
        )
        setContent {
            SimpleMapTheme {
                val locationPermissionState =
                    rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
                if (locationPermissionState.status.isGranted) {
                    MainMapView()
                } else {
                    SideEffect {
                        locationPermissionState.launchPermissionRequest()
                    }
                }
            }
        }
    }

    private fun storeLocation(location: Location) {
        lifecycleScope.launchWhenCreated {
            dataStore.edit { store ->
                store[App.locationLatKey] = location.position.latitude
                store[App.locationLonKey] = location.position.longitude
            }
        }
    }

    override fun onResume() {
        mapLocationManager.resume()
        super.onResume()
    }

    override fun onStop() {
        mapLocationManager.suspend()
        super.onStop()
    }

    override fun onDestroy() {
        mapLocationManager.unsubscribe(locationListener)
        super.onDestroy()
    }
}

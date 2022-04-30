package ru.marslab.simplemap.feature.mainmap.presentation.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import ru.marslab.simplemap.R

@Composable
fun YandexMapWidget(
    widgetModel: YandexMapWidgetModel,
    modifier: Modifier = Modifier,
    onClick: (point: Point) -> Unit = {},
    onLongClick: (point: Point) -> Unit = {}
) {
    val state = widgetModel.state.collectAsState()
    val mapView = rememberMapViewWithLifecycle(mapId = R.layout.yandex_map)
    val mapClickListener = object : InputListener {
        override fun onMapTap(p0: Map, p1: Point) {
            onClick.invoke(p1)
        }

        override fun onMapLongTap(p0: Map, p1: Point) {
            onLongClick.invoke(p1)
        }
    }
    LaunchedEffect(key1 = mapView) {
        widgetModel.setMap(
            mapView.map.apply {
                addInputListener(mapClickListener)
            }
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(factory = { mapView })
    }
}

@Composable
fun rememberMapViewWithLifecycle(mapId: Int): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = mapId
        }
    }
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
    return mapView
}

@Composable
private fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    MapKitFactory.getInstance().onStart()
                    mapView.onStart()
                }
                Lifecycle.Event.ON_STOP -> {
                    MapKitFactory.getInstance().onStop()
                    mapView.onStop()
                }
                else -> {}
            }
        }
    }

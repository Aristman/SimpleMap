package ru.marslab.simplemap.feature.mainmap.presentation.model

import androidx.datastore.preferences.core.Preferences
import com.yandex.mapkit.geometry.Point
import ru.marslab.simplemap.App

fun Preferences.toPoint(): Point =
    Point(
        this[App.locationLatKey] ?: 0.0,
        this[App.locationLonKey] ?: 0.0
    )

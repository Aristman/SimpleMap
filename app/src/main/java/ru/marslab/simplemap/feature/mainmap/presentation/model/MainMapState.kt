package ru.marslab.simplemap.feature.mainmap.presentation.model

import ru.marslab.simplemap.feature.mainmap.presentation.widget.YandexMapWidgetModel

data class MainMapState(
    val mapWidgetModel: YandexMapWidgetModel = YandexMapWidgetModel()
)

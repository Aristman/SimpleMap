package ru.marslab.simplemap.feature.mainmap.presentation.model

import androidx.annotation.StringRes
import ru.marslab.simplemap.core.Event

sealed class MainMapEvent : Event {
    data class ShowToast(@StringRes val stringId: Int) : MainMapEvent()
}

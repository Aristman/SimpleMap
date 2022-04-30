package ru.marslab.simplemap.core

import kotlinx.coroutines.CoroutineDispatcher

interface BaseRepository {
    val dispatcher: CoroutineDispatcher
}

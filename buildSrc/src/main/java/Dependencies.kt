object Dependencies {
    object Jetpack {
        const val core = "androidx.core:core-ktx:${Versions.jetpackCore}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val lifeCycle =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
        const val lifeCycleVieModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"

        object Paging {
            const val core = "androidx.paging:paging-runtime:${Versions.paging}"
            const val compose = "androidx.paging:paging-compose:${Versions.Compose.paging}"
        }
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinSerialization}"
    }

    object JetpackCompose {
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.core}"
        const val activity = "androidx.activity:activity-compose:${Versions.Compose.activity}"
        const val material = "androidx.compose.material:material:${Versions.Compose.core}"
        const val animation = "androidx.compose.animation:animation:${Versions.Compose.core}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.core}"
        const val lifecycle =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.lifecycle}"
        const val junit = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.core}"
        const val MaterialThemeAdapter =
            "com.google.android.material:compose-theme-adapter:${Versions.Compose.materialThemeAdapter}"
        const val coil = "io.coil-kt:coil-compose:${Versions.Compose.coil}"
        const val landscapistGlide =
            "com.github.skydoves:landscapist-glide:${Versions.Compose.landscapist}"

        object Accompanist {
            const val drawablePainter =
                "com.google.accompanist:accompanist-drawablepainter:${Versions.Accompanist.stable}"
            const val flowlayout =
                "com.google.accompanist:accompanist-flowlayout:${Versions.Accompanist.stable}"
            const val insets =
                "com.google.accompanist:accompanist-insets:${Versions.Accompanist.latest}"
            const val insetsUi =
                "com.google.accompanist:accompanist-insets-ui:${Versions.Accompanist.latest}"
            const val navigationAnimation =
                "com.google.accompanist:accompanist-navigation-animation:${Versions.Accompanist.stable}"
            const val navigationMaterial =
                "com.google.accompanist:accompanist-navigation-material:${Versions.Accompanist.stable}"
            const val pager =
                "com.google.accompanist:accompanist-pager:${Versions.Accompanist.pager}"
            const val pagerIndicators =
                "com.google.accompanist:accompanist-pager-indicators:${Versions.Accompanist.pager}"
            const val placeholder =
                "com.google.accompanist:accompanist-placeholder:${Versions.Accompanist.stable}"
            const val placeholderMaterial =
                "com.google.accompanist:accompanist-placeholder-material:${Versions.Accompanist.stable}"
            const val systemUiController =
                "com.google.accompanist:accompanist-systemuicontroller:${Versions.Accompanist.stable}"
            const val webView =
                "com.google.accompanist:accompanist-webview:${Versions.Accompanist.stable}"
            const val permissions =
                "com.google.accompanist:accompanist-permissions:${Versions.Accompanist.latest}"
            const val swipeRefresh =
                "com.google.accompanist:accompanist-swiperefresh:${Versions.Accompanist.stable}"
            const val theme =
                "com.google.accompanist:accompanist-appcompat-theme:${Versions.Accompanist.stable}"
        }

        object Voyager {
            const val core = "cafe.adriel.voyager:voyager-navigator:${Versions.voyager}"
            const val bottomSheet =
                "cafe.adriel.voyager:voyager-bottom-sheet-navigator:${Versions.voyager}"
            const val tab = "cafe.adriel.voyager:voyager-tab-navigator:${Versions.voyager}"
            const val transitions = "cafe.adriel.voyager:voyager-transitions:${Versions.voyager}"
            const val viewModel = "cafe.adriel.voyager:voyager-androidx:${Versions.voyager}"
            const val koin = "cafe.adriel.voyager:voyager-koin:${Versions.voyager}"
            const val kodein = "cafe.adriel.voyager:voyager-kodein:${Versions.voyager}"
            const val hilt = "cafe.adriel.voyager:voyager-hilt:${Versions.voyager}"
            const val rxJava = "cafe.adriel.voyager:voyager-rxjava:${Versions.voyager}"
            const val liveData = "cafe.adriel.voyager:voyager-livedata:${Versions.voyager}"
        }
    }

    object Design {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object NavigationComponent {
        const val navigationRuntime =
            "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Google {
        object FireBase {
            const val core = "com.google.firebase:firebase-bom:${Versions.FireBase.core}"
            const val messaging =
                "com.google.firebase:firebase-messaging-ktx:${Versions.FireBase.messaging}"
            const val analytics =
                "com.google.firebase:firebase-analytics-ktx:${Versions.FireBase.analytics}"
        }

        const val map = "com.google.android.gms:play-services-maps:${Versions.Google.map}"
        const val mapKtx = "com.google.maps.android:maps-ktx:${Versions.Google.mapKtx}"
        const val location =
            "com.google.android.gms:play-services-location:${Versions.Google.location}"
        const val utils = "com.google.maps.android:android-maps-utils:${Versions.Google.utils}"
        const val compose = "com.google.maps.android:maps-compose:${Versions.Google.compose}"
    }

    object Dagger {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val convertJson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object OkHttp {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val okhttpLoggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object Room {
        const val core = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object DataStore {
        const val core = "androidx.datastore:datastore:${Versions.dataStore}"
        const val preferences = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val logging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val okhttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
    }

    object SqlDelight {
        const val core = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val coroutines =
            "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        const val paging3 =
            "com.squareup.sqldelight:android-paging3-extensions:${Versions.sqlDelight}"
    }
}

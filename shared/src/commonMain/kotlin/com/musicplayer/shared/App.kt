package com.musicplayer.shared

import androidx.compose.runtime.Composable
import com.musicplayer.shared.di.SharedModule
import com.musicplayer.shared.ui.screens.MusicListScreen
import com.musicplayer.shared.ui.theme.MusicPlayerAppTheme

/**
 * Main entry point for the Compose Multiplatform app
 * This can be called from Android, iOS, or Desktop platforms
 */
@Composable
fun App() {
    val sharedModule = SharedModule()
    val viewModel = sharedModule.createViewModel()

    MusicPlayerAppTheme {
        MusicListScreen(viewModel = viewModel)
    }
}


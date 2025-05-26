package com.openclassrooms.arista

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * MainApplication is the application class for the app.
 *
 * Annotated with [HiltAndroidApp] to trigger Hilt's code generation and
 * enable dependency injection throughout the application.
 */
@HiltAndroidApp
class MainApplication : Application()

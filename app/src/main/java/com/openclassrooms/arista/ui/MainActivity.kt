package com.openclassrooms.arista.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.openclassrooms.arista.R
import com.openclassrooms.arista.databinding.ActivityMainBinding
import com.openclassrooms.arista.ui.exercise.ExerciseFragment
import com.openclassrooms.arista.ui.sleep.SleepFragment
import com.openclassrooms.arista.ui.user.UserDataFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity serves as the host activity for the application, managing navigation between fragments.
 *
 * Uses [ActivityMainBinding] for view binding and handles bottom navigation interactions.
 *
 * Features:
 * - Initializes and displays the default fragment ([UserDataFragment]) on first launch.
 * - Maintains and restores the selected bottom navigation item on configuration changes.
 * - Switches between fragments ([UserDataFragment], [ExerciseFragment], [SleepFragment]) based on bottom navigation selection.
 *
 * The activity is annotated with [AndroidEntryPoint] for Hilt dependency injection support.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedMenuItemId: Int = R.id.nav_user_data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener(navListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UserDataFragment()).commit()
        } else {
            savedInstanceState.getInt(SELECTED_MENU_ITEM, R.id.nav_user_data)
                .also { selectedMenuItemId = it }
            binding.bottomNavigation.selectedItemId = selectedMenuItemId
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_MENU_ITEM, selectedMenuItemId)
    }

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        getFragmentById(item.itemId)?.let { selectedFragment ->
            selectedMenuItemId = item.itemId
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment).commit()
            true
        } ?: false
    }

    /**
     * Returns the Fragment corresponding to the given menu item id.
     *
     * @param menuId The id of the selected bottom navigation menu item.
     * @return The associated Fragment instance or null if no match is found.
     */
    private fun getFragmentById(menuId: Int): Fragment? {
        return when (menuId) {
            R.id.nav_user_data -> UserDataFragment()
            R.id.nav_exercise -> ExerciseFragment()
            R.id.nav_sleep -> SleepFragment()
            else -> null
        }
    }

    companion object {
        private const val SELECTED_MENU_ITEM = "selected_menu_item"
    }
}

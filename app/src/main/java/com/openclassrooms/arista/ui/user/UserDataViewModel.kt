package com.openclassrooms.arista.ui.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import com.openclassrooms.arista.domain.usecase.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing and exposing user data to the UI.
 *
 * @property addUserUsecase Use case for adding or updating user information.
 * @property getUserUsecase Use case for retrieving the current user as a flow.
 */
@HiltViewModel
class UserDataViewModel @Inject constructor(
    private val addUserUsecase: AddUserUseCase,
    private val getUserUsecase: GetUserUsecase
) :
    ViewModel() {
    /** StateFlow holding the current user data, observable by the UI. */
    private val _userFlow = MutableStateFlow<User?>(null)
    val userFlow: StateFlow<User?> = _userFlow.asStateFlow()

    init {
        viewModelScope.launch {
            Log.d("OM:UserDataViewModel", "Loading user data")
            loadUserData()
            Log.d("OM:UserDataViewModel", "User data loaded")
        }
    }

    /**
     * Loads user data from the data source and updates the [_userFlow].
     */
    private suspend fun loadUserData() {
        getUserUsecase.execute().collect{_userFlow.value = it}
    }
}
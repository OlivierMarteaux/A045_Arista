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

@HiltViewModel
class UserDataViewModel @Inject constructor(
    private val addUserUsecase: AddUserUseCase,
    private val getUserUsecase: GetUserUsecase
) :
    ViewModel() {
    private val _userFlow = MutableStateFlow<User?>(null)
    val userFlow: StateFlow<User?> = _userFlow.asStateFlow()

    init {
        viewModelScope.launch {
            Log.d("OM:UserDataViewModel", "Loading user data")
            loadUserData()
            Log.d("OM:UserDataViewModel", "User data loaded")
        }
    }

    private suspend fun loadUserData() {
        getUserUsecase.execute().collect{_userFlow.value = it}
    }
}
package com.openclassrooms.arista.ui.user

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import com.openclassrooms.arista.domain.usecase.AddUserUseCase
import com.openclassrooms.arista.utils.exportRoomDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class UserDataViewModel @Inject constructor(private val getUserUsecase: GetUserUsecase) :
//    ViewModel() {
//    private val _userFlow = MutableStateFlow<User?>(null)
//    val userFlow: StateFlow<User?> = _userFlow.asStateFlow()
//
//    init {
//        loadUserData()
//    }
//
//    private fun loadUserData() {
//        val user = getUserUsecase.execute()
//        _userFlow.value = user
//    }
//}

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
//            // add user for initial database creation
//            addUserUsecase.execute(User(1, "User", "user@example.com"))
            loadUserData()
        }
    }

    private suspend fun loadUserData() {
        val user = getUserUsecase.execute()
        _userFlow.value = user
    }
}
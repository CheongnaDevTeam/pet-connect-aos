package com.kkjang.petconnect.feature.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kkjang.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginResult = MutableStateFlow<Result<Unit>?>(null)
    val loginResult: StateFlow<Result<Unit>?> = _loginResult

    fun loginWithKakaoTalk(context: Context) {
        viewModelScope.launch {
            val result = loginRepository.loginWithKakaoTalk(context)
            _loginResult.value = result
        }
    }
}
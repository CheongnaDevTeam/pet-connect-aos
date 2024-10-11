package dev.kkjang.deeplink.state

// UI 상태값 변화에 따른 State 기본 Default
sealed class DefaultState {
    class Success<out T>(data: T) : DefaultState()
    class Error(e: Throwable?) : DefaultState()
    data object Loading : DefaultState()
}

//
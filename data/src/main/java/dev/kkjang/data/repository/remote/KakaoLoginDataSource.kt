package dev.kkjang.data.repository.remote

import android.content.Context
import com.kakao.sdk.user.UserApiClient
import dev.kkjang.data.util.default
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class KakaoLoginDataSource @Inject constructor() {
    suspend fun loginWithKakaoTalk(context: Context): Result<Unit> {
        return try {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                // 카카오톡이 설치되어 있는 경우
                suspendCoroutine { continuation ->
                    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                        if (error != null) {
                            // 에러 발생 시 Result.failure로 래핑하여 전달
                            Timber.i("Fail ${error.message.toString().default()}")
                            continuation.resume(Result.failure(error))
                        } else if (token != null) {
                            // 성공 시 Result.success로 래핑하여 전달
                            Timber.d("Success ${Result.success(Unit)}")
                            continuation.resume(Result.success(Unit))
                        } else {
                            // 알 수 없는 오류 처리
                            Timber.e("Unknown error occurred")
                            continuation.resume(Result.failure(Exception("Unknown error occurred")))
                        }
                    }
                }
            } else {
                // 카카오톡이 설치되어 있지 않은 경우 카카오 계정으로 로그인
                suspendCoroutine { continuation ->
                    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                        if (error != null) {
                            Timber.i("Fail ${error.message.toString().default()}")
                            continuation.resume(Result.failure(error))
                        } else if (token != null) {
                            Timber.d("Success ${Result.success(Unit)}")
                            continuation.resume(Result.success(Unit))
                        } else {
                            Timber.e("Unknown error occurred")
                            continuation.resume(Result.failure(Exception("Unknown error occurred")))
                        }
                    }
                }
            }
        } catch (e: Exception) {
            // 예외 발생 시 Result.failure로 래핑하여 전달
            Timber.e("Error $e")
            Result.failure(e)
        }
    }
}


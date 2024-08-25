package dev.kkjang.data.repository

import android.content.Context
import dev.kkjang.data.repository.remote.KakaoLoginDataSource
import dev.kkjang.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val kakaoLoginDataSource: KakaoLoginDataSource
) : LoginRepository {
    override suspend fun loginWithKakaoTalk(context: Context): Result<Unit> {
        return kakaoLoginDataSource.loginWithKakaoTalk(context)
    }
}
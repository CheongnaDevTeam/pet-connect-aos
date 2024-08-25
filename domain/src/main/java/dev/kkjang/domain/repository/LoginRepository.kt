package dev.kkjang.domain.repository

import android.content.Context

interface LoginRepository {
    suspend fun loginWithKakaoTalk(context: Context): Result<Unit>
}
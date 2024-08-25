package dev.kkjang.data.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kkjang.data.repository.LoginRepositoryImpl
import dev.kkjang.data.repository.remote.KakaoLoginDataSource
import dev.kkjang.domain.repository.LoginRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        kakaoLoginDataSource: KakaoLoginDataSource
    ): LoginRepository = LoginRepositoryImpl(kakaoLoginDataSource)

    @Provides
    @Singleton
    fun provideKakaoLoginDataSource(): KakaoLoginDataSource = KakaoLoginDataSource()
}
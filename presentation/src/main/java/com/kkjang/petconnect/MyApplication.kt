package com.kkjang.petconnect

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    private val notLoggingTree = object : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(if( BuildConfig.DEBUG) TimberDebugTree() else notLoggingTree)
        MultiDex.install(this)
        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))
    }
}

class TimberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "${element.fileName}:${element.lineNumber}#${element.methodName}"
    }
}
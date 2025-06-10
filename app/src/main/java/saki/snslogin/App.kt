package saki.snslogin

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import saki.snslogin.BuildConfig

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.kakaoNativeKey)
    }
}

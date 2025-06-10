package saki.snslogin

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.Constants.TAG
import com.kakao.sdk.user.UserApiClient

fun snsType(type: String, context: Context) {
    when(type) {
        "kakao" -> kakaoLogin(context)
        else -> ""
    }
}

fun kakaoLogin (context: Context) {
    val callback: (OAuthToken?, Throwable?) -> Unit = {token, error ->
        if(error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
        }
    }

    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) {token, error ->
            if (error != null) {
                Log.e(TAG, "카카오톡 로그인 실패", error)

                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            } else if (token != null) {
                Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }
}
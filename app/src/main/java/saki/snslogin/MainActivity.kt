@file:OptIn(ExperimentalMaterial3Api::class)

package saki.snslogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import saki.snslogin.ui.theme.SNSLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SNSLoginTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(text = stringResource(id = R.string.app_name))
                        },)
                    },
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            initBtn()
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun initBtn() {
    Column() { kakaoLoginBtn() }

}

@Composable
fun kakaoLoginBtn() {
    val context = LocalContext.current
    Button(
        onClick = { snsType("kakao", context) }
    ) {
        Text(text = stringResource(id = R.string.kakaoLogin))
    }
}
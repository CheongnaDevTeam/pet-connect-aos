package com.kkjang.petconnect.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkjang.petconnect.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val loginResult by viewModel.loginResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize() // 전체 화면을 채우도록 설정
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween, // 상단과 하단에 공간을 배치
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 상단 영역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), // 상단과 버튼 사이의 공간
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_start_title)
            )
            Spacer(modifier = Modifier.height(16.dp)) // 제목과 버튼 사이에 공간을 추가
            // 추가적인 이미지나 UI 요소가 여기에 올 수 있습니다.
        }

        // 로그인 결과 텍스트
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                loginResult?.isSuccess == true -> {
                    Text("Login successful!")
                }
                loginResult?.isFailure == true -> {
                    Text("Login failed: ${loginResult?.exceptionOrNull()?.message}")
                }
            }
        }

        // 하단 영역
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    viewModel.loginWithKakaoTalk(context)
                }
                .align(Alignment.CenterHorizontally) // 중앙 정렬
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.kakao_login_medium_wide),
                contentDescription = "카카오톡 로그인"
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreView() {
    Column(
        modifier = Modifier
            .fillMaxSize() // 전체 화면을 채우도록 설정
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween, // 상단과 하단에 공간을 배치
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 상단 영역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), // 상단과 버튼 사이의 공간
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_start_title)
            )
            Spacer(modifier = Modifier.height(16.dp)) // 제목과 버튼 사이에 공간을 추가
            // 추가적인 이미지나 UI 요소가 여기에 올 수 있습니다.
        }

        // 하단 영역
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { }
                .align(Alignment.CenterHorizontally) // 중앙 정렬
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.kakao_login_medium_wide),
                contentDescription = "카카오톡 로그인"
            )
        }
    }
}

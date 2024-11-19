package com.kkjang.petconnect.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkjang.petconnect.R
import run.piece.dev.refactoring.compose.common.theme.C_FF7878
import run.piece.dev.refactoring.compose.common.theme.C_FFFFFF
import run.piece.dev.refactoring.compose.common.theme.G600_8C919F
import run.piece.dev.refactoring.compose.common.theme.G700_4F4F52
import run.piece.dev.refactoring.compose.common.theme.G900_141414

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
            .padding(top = 72.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween, // 상단과 하단에 공간을 배치
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = stringResource(id = R.string.permission_screen_title),
                    color = G900_141414,
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = FontFamily(
                            Font(R.font.pretendard_semibold)
                        )
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp) // 자식 뷰 사이 간격 추가
            ) {
                // 동작 및 피트니스
                ImageWithTextRow(
                    imageResId = R.drawable.ic_x24_dumbbell,
                    titleText = stringResource(id = R.string.permission_fitness_title),
                    descriptionText = stringResource(id = R.string.permission_fitness_sub_title)
                )
                // 위치
                ImageWithTextRow(
                    imageResId = R.drawable.ic_x24_mappin,
                    titleText = stringResource(id = R.string.permission_location_title),
                    descriptionText = stringResource(id = R.string.permission_location_sub_title)
                )
                // 카메라
                ImageWithTextRow(
                    imageResId = R.drawable.ic_x24_camera,
                    titleText = stringResource(id = R.string.permission_camera_title),
                    descriptionText = stringResource(id = R.string.permission_camera_sub_title)
                )

                // 사진
                ImageWithTextRow(
                    imageResId = R.drawable.ic_x24_image,
                    titleText = stringResource(id = R.string.permission_profile_title),
                    descriptionText = stringResource(id = R.string.permission_profile_sub_title)
                )

                // 알림
                ImageWithTextRow(
                    imageResId = R.drawable.ic_x24_bell,
                    titleText = stringResource(id = R.string.permission_notification_title),
                    descriptionText = stringResource(id = R.string.permission_notification_sub_title)
                )

                // 저장
                ImageWithTextRow(
                    imageResId = R.drawable.ic_x24_download,
                    titleText = stringResource(id = R.string.permission_save_title),
                    descriptionText = stringResource(id = R.string.permission_save_sub_title)
                )
            }
        }

        ScreenConfirmButton()
    }
}

@Composable
fun ImageWithTextRow(
    imageResId: Int,
    titleText: String,
    descriptionText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min), // Row의 높이를 자식의 최소 높이에 맞춤
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 이미지
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = titleText,
            modifier = Modifier.size(24.dp)
        )

        // 텍스트
        Column(
            modifier = Modifier // Row의 높이를 텍스트가 채우도록 설정
                .weight(1f)
                .padding(start = 16.dp), // 나머지 공간을 차지
            verticalArrangement = Arrangement.SpaceBetween // 상하 배치
        ) {
            Text(
                text = titleText,
                color = G900_141414,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = FontFamily(
                        Font(R.font.pretendard_semibold)
                    )
                )
            )
            Text(
                text = descriptionText,
                color = G700_4F4F52,
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily(
                        Font(R.font.pretendard_regular)
                    )
                )
            )
        }
    }
}

// 확인 버튼
@Composable
fun ScreenConfirmButton() {
    Button(
        onClick = {
            // 버튼 클릭 처리
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = C_FF7878,
            contentColor = C_FFFFFF,
            disabledContainerColor = G600_8C919F,
            disabledContentColor = C_FFFFFF,
        ),
        shape = RoundedCornerShape(28.dp), // 모서리 둥글기 설정
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), // 너비를 화면 가득 채움 (선택 사항)
    ) {
        Text(
            text = stringResource(id = R.string.btn_confirm_title),
            color = C_FFFFFF,
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W600,
                fontFamily = FontFamily(
                    Font(R.font.pretendard_semibold)
                )
            )
        )
    }
}



package com.kkjang.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kkjang.petconnect.ui.theme.PetconnectandroidappTheme
import com.kkjang.petconnect.util.CryptoUtils
import timber.log.Timber
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import android.util.Base64

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 안전한 키 생성 (예제용)
        val secretKey = generateSecretKey().encoded
        println("secretKey : $secretKey")

        // SecretKey를 Base64로 인코딩하지 않고 직접 사용
        // 암호화
        val encryptedValue = CryptoUtils.encrypt(secretKey, "petconnect001!@")
        println("Encrypted Value: $encryptedValue")

        // 복호화
        val decryptedValue = CryptoUtils.decrypt(secretKey, encryptedValue)
        println("Decrypted Value: $decryptedValue")

        enableEdgeToEdge()
        setContent {
            PetconnectandroidappTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PetconnectandroidappTheme {
        Greeting("Android")
    }
}


fun generateSecretKey(): SecretKey {
    val keyGen = KeyGenerator.getInstance("AES")
    keyGen.init(128) // 키 길이 설정 (128, 192, 256 중 하나)
    return keyGen.generateKey()
}


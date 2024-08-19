package com.kkjang.petconnect.util

import android.util.Base64
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object CryptoUtils {

    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES"

    fun encrypt(key: ByteArray, value: String): String {
        val secretKey = SecretKeySpec(key, ALGORITHM)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val encryptedValue = cipher.doFinal(value.toByteArray())
        return Base64.encodeToString(encryptedValue, Base64.DEFAULT)
    }

    fun decrypt(key: ByteArray, encryptedValue: String): String {
        val secretKey = SecretKeySpec(key, ALGORITHM)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)

        val decodedValue = Base64.decode(encryptedValue, Base64.DEFAULT)
        val decryptedValue = cipher.doFinal(decodedValue)

        return String(decryptedValue)
    }

    fun generateSecretKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(128) // 키 길이 설정 (128, 192, 256 중 하나)
        return keyGen.generateKey()
    }

    /****
     *  사용법 예제
     *
     *  // 안전한 키 생성 (예제용)
     *  val secretKey = CryptoUtils.generateSecretKey().encoded
     *  println("secretKey : $secretKey")
     *
     *  // SecretKey를 Base64로 인코딩하지 않고 직접 사용
     *  // 암호화
     *  val encryptedValue = CryptoUtils.encrypt(secretKey, "${String Value}")
     *  println("Encrypted Value: $encryptedValue")
     *
     *
     *  // 복호화
     *  val decryptedValue = CryptoUtils.decrypt(secretKey, encryptedValue)
     *  println("Decrypted Value: $decryptedValue")
     *
     * **/
}
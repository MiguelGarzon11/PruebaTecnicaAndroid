package com.mgarzondev.paymentspt.data.network.auth

import android.util.Base64
import java.lang.IllegalArgumentException

object AuthUtils {
    fun basicAuth(commerceCode: String, terminalCode: String ):String {
        if (commerceCode != "000123" && terminalCode != "000ABC") {
            throw IllegalArgumentException("Invalid credentials: expected commerceCode = 000123, terminalCode = 000ABC")
        }
        val credentials = "$commerceCode$terminalCode"
        val encoded = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        return "Basic $encoded"
    }
}
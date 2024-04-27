package com.catcompany.base.utils

import android.content.Context
import android.widget.Toast
import com.catcompany.base.exception.AuthenticationException
import com.catcompany.base.exception.ConnectedButNoInternetException
import com.catcompany.base.exception.DisplayableException
import com.catcompany.base.exception.NoInternetException

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.showToast(e: Throwable) {
    showToast(e.getErrorDisplayMessage())
}

fun Throwable.getErrorDisplayMessage(): String {
    return when (this) {
        is ConnectedButNoInternetException -> "Connected but no internet!"
        is NoInternetException -> "No internet!"
        is AuthenticationException -> "Session timeout!"
        is DisplayableException -> this.errorMsg
        else -> "Something went wrong!"
    }
}
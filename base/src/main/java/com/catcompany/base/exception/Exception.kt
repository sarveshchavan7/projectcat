package com.catcompany.base.exception

import java.io.IOException

sealed class DisplayableException(val errorMsg: String) : Exception()
data class NoInternetException(val errorMsg: String? = null) : IOException()
data class ConnectedButNoInternetException(val errorMsg: String? = null) : IOException()
data class AuthenticationException(val errorMsg: String? = null) : IOException()
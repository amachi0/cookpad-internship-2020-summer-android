package com.cookpad.android.minicookpad.login

interface LoginContract {
    interface View {
        fun renderLoginSuccess(user: User)
        fun renderLoginFailed(exception: Exception)
        fun renderRegisterSuccess()
        fun renderRegisterFailed(exception: Exception)
    }

    interface Interactor {
        fun restoreUser(onSuccess: (User?) -> Unit, onFailed: (Throwable) -> Unit)
        fun loginUser(name: String, onSuccess: (User?) -> Unit, onFailed: (Throwable) -> Unit)
        fun registerUser(name: String, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit)
    }

    interface Presenter {
        fun onloginCurrentUserRequested()
        fun loginFromUserNameRequested()
        fun registerUserRequested()
    }

    interface Routing {
        fun navigateRecipeList()
    }

    data class User(
        val name: String
    )
}
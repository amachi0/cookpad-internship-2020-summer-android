package com.cookpad.android.minicookpad.login

import com.cookpad.android.minicookpad.datastore.UserDataStore

class LoginInteractor(
    private val userDataStore: UserDataStore
) : LoginContract.Interactor {
    override fun restoreUser(
        onSuccess: (LoginContract.User?) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        userDataStore.currentUser()
    }

    override fun loginUser(
        name: String,
        onSuccess: (LoginContract.User?) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun registerUser(name: String, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit) {
        TODO("Not yet implemented")
    }

}
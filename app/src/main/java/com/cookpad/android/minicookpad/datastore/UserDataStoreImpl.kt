package com.cookpad.android.minicookpad.datastore

import com.cookpad.android.minicookpad.datasource.LocalUserDataSource
import com.cookpad.android.minicookpad.datasource.RemoteUserDataSource
import com.cookpad.android.minicookpad.datasource.UserEntity

class UserDataStoreImpl(
    private val localUserDataSource: LocalUserDataSource,
    private val remoteUserDataSource: RemoteUserDataSource
) : UserDataStore {
    override fun currentUser(): UserEntity? {
        return localUserDataSource.fetch()
    }

    override fun fetch(
        name: String,
        onSuccess: (UserEntity?) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        remoteUserDataSource.fetch(
            name = name,
            onSuccess = { user ->
                user?.let {
                    localUserDataSource.save(
                        user = it,
                        onSuccess = { onSuccess.invoke(it) },
                        onFailed = { throwable -> onFailed(throwable) }
                    )
                }
            },
            onFailed = { throwable -> onFailed(throwable) }
        )
    }

    override fun register(user: UserEntity, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit) {
        remoteUserDataSource.save(
            user = user,
            onSuccess = {
                localUserDataSource.save(
                    user = user,
                    onSuccess = { onSuccess() },
                    onFailed = { throwable -> onFailed(throwable) }
                )
            },
            onFailed = { throwable -> onFailed(throwable) }
        )
    }
}
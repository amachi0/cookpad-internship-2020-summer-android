package com.cookpad.android.minicookpad.datasource

interface RecipeDataSource {
    fun fetchAll(onSuccess: (List<RecipeEntity>) -> Unit, onFailed: (Throwable) -> Unit)
    fun save(recipe: RecipeEntity, onSuccess: (List<RecipeEntity>) -> Unit, onFailed: (Throwable) -> Unit)
}

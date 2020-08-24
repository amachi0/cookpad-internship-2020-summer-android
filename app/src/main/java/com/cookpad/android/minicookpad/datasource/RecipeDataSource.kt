package com.cookpad.android.minicookpad.datasource

import com.cookpad.android.minicookpad.recipe_create.RecipeCreateContract
import com.cookpad.android.minicookpad.recipelist.RecipeListContract

interface RecipeDataSource {
    fun fetchAll(onSuccess: (List<RecipeEntity>) -> Unit, onFailed: (Throwable) -> Unit)
    fun save(recipe: RecipeEntity, onSuccess: (List<RecipeEntity>) -> Unit, onFailed: (Throwable) -> Unit)
}
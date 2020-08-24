package com.cookpad.android.minicookpad.recipe_create

import android.net.Uri
import com.cookpad.android.minicookpad.datasource.ImageDataSource
import com.cookpad.android.minicookpad.datasource.RecipeDataSource
import com.cookpad.android.minicookpad.datasource.RecipeEntity

class RecipeCreateInteractor : RecipeCreateContract.Interactor {
    lateinit var recipeDataSource: RecipeDataSource
    lateinit var imageDataSource: ImageDataSource
    fun uploadImage(
        imageUri: String,
        onSuccess: (imageRef: String) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        imageDataSource.saveImage(
            imageUri,
            onSuccess = { imageRef -> onSuccess(imageRef) },
            onFailed = { throwable -> onFailed(throwable) }
        )
    }

    override fun saveRecipe(
        recipe: RecipeCreateContract.Recipe,
        onSuccess: () -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        imageDataSource.saveImage(
            recipe.imageUri,
            onSuccess = { imageRef ->
                recipeDataSource.save(
                    RecipeEntity(
                        title = recipe.title,
                        imagePath = imageRef,
                        steps = recipe.steps,
                        authorName = recipe.authorName
                    ),
                    onSuccess = { onSuccess() },
                    onFailed = { throwable ->  onFailed(throwable) }
                )
            },
            onFailed = { throwable -> onFailed(throwable) }
        )
    }
}
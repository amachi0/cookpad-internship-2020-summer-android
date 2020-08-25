package com.cookpad.android.minicookpad.recipe_create

import com.cookpad.android.minicookpad.datasource.ImageDataSource
import com.cookpad.android.minicookpad.datasource.RecipeDataSource
import com.cookpad.android.minicookpad.datasource.RecipeEntity

class RecipeCreateInteractor(
    private val recipeDataSource: RecipeDataSource,
    private val imageDataSource: ImageDataSource
) : RecipeCreateContract.Interactor {
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
                        authorName = "クックパド美"
                    ),
                    onSuccess = { onSuccess() },
                    onFailed = { throwable ->  onFailed(throwable) }
                )
            },
            onFailed = { throwable -> onFailed(throwable) }
        )
    }
}

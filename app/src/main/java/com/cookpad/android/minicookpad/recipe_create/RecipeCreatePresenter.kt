package com.cookpad.android.minicookpad.recipe_create

import android.app.Activity

class RecipeCreatePresenter(
    private val view: RecipeCreateContract.View,
    private val viewModel: RecipeCreateViewModel,
    private val interactor: RecipeCreateContract.Interactor,
    private val routing: RecipeCreateContract.Routing
) : RecipeCreateContract.Presenter {
    override fun onImageUploaded(imageUir: String) {
        interactor.uploadImage(
            imageUir,
            onSuccess = { imageRef -> viewModel.updateImageUri(imageRef) },
            onFailed = { throwable ->  view.uploadImageError(throwable) }
        )
    }

    override fun onSendClicked(recipe: RecipeCreateContract.Recipe) {
        val recipe = RecipeCreateContract.Recipe(
            title = recipe.title,
            imagePath = viewModel.imageUri.value ?: "null",
            steps = recipe.steps
        )
    }
    override fun onRecipeListRequested(activity: Activity) {
        routing.navigateRecipeList(activity = activity)
    }
}
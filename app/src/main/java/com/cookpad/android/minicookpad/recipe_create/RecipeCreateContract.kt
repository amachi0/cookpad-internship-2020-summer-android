package com.cookpad.android.minicookpad.recipe_create

import android.app.Activity
import android.net.Uri

interface RecipeCreateContract {
    interface View {
        fun renderRecipeImage(imageUri: Uri)
        fun uploadImageError(throwable: Throwable)
        fun uploadDatabaseError(throwable: Throwable)
    }

    interface Interactor {
        fun saveRecipe(recipe: Recipe, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit)
    }

    interface Presenter {
        fun onImageUploaded(imageUir: String)
        fun onSendClicked(recipe: Recipe)
        fun onRecipeListRequested(activity: Activity)
    }

    interface Routing {
        fun navigateRecipeList(activity: Activity)
    }

    data class Recipe(
        val title: String,
        val imageUri: String,
        val steps: List<String>,
        val authorName: String = "クックパド美"
    )
}
package com.cookpad.android.minicookpad.recipe_create

interface RecipeCreateContract {
    interface View {
        fun renderSuccess()
        fun renderError(throwable: Throwable)
    }

    interface Interactor {
        fun saveRecipe(recipe: Recipe, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit)
    }

    interface Presenter {
        fun onSaveRequested(recipe: Recipe)
    }

    interface Routing {
        fun navigateRecipeList()
    }

    data class Recipe(
        val title: String,
        val imageUri: String,
        val steps: List<String>
    )
}
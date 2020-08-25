package com.cookpad.android.minicookpad.recipe_create

class RecipeCreatePresenter(
    private val view: RecipeCreateContract.View,
    private val interactor: RecipeCreateContract.Interactor,
    private val routing: RecipeCreateContract.Routing
) : RecipeCreateContract.Presenter {
    override fun onSaveRequested(recipe: RecipeCreateContract.Recipe) {
        interactor.saveRecipe(
            recipe,
            onSuccess = {
                view.renderSuccess()
                routing.navigateRecipeList()
            },
            onFailed = { throwable ->   view.renderError(throwable) }
        )
    }
}

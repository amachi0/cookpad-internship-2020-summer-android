package com.cookpad.android.minicookpad.recipe_create

class RecipeCreateRouting(
    private val activity: RecipeCreateActivity
) : RecipeCreateContract.Routing {
    override fun navigateRecipeList() {
        activity.finish()
    }
}

package com.cookpad.android.minicookpad.recipe_create

import android.app.Activity

class RecipeCreateRouting : RecipeCreateContract.Routing {
    override fun navigateRecipeList(activity: Activity) {
        activity.finish()
    }
}
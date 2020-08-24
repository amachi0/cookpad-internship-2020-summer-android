package com.cookpad.android.minicookpad.recipelist

import com.cookpad.android.minicookpad.datasource.RecipeEntity
import com.nhaarman.mockitokotlin2.*
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class RecipeListPresenterTest {
    lateinit var view: RecipeListContract.View

    lateinit var interactor: RecipeListContract.Interactor

    lateinit var routing: RecipeListContract.Routing

    lateinit var presenter: RecipeListPresenter

    @Before
    fun setup() {
        view = mock()
        interactor = mock()
        routing = mock()

        presenter = RecipeListPresenter(view, interactor, routing)
    }

    @Test
    fun verifyFetchRecipeListSuccess() {
        // given
        val recipes = listOf(
            RecipeListContract.Recipe(
                id = "xxxx",
                title = "おいしいきゅうりの塩もみ",
                imagePath = "images/recipe.png",
                steps = "きゅうりを切る, 塩をまく, もむ",
                authorName = "クックパド美"
            )
        )
        val onSuccess: (List<RecipeListContract.Recipe>) -> Unit = mock()
        whenever(interactor.fetchRecipeList(any(), any())).then {
            (it.arguments[0] as (List<RecipeListContract.Recipe>) -> Unit).invoke(recipes)
        }

        // when
        presenter.onRecipeListRequested()

        // then

    }
}
package com.cookpad.android.minicookpad.recipelist

import com.cookpad.android.minicookpad.datasource.RecipeDataSource
import com.cookpad.android.minicookpad.datasource.RecipeEntity
import com.cookpad.android.minicookpad.recipelist.RecipeListContract.Recipe
import com.google.common.truth.Truth.assertThat
import com.google.firebase.FirebaseException
import com.google.firebase.ktx.Firebase
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import java.lang.RuntimeException

class RecipeListInteractorTest {
    lateinit var recipeDataSource: RecipeDataSource

    lateinit var interactor: RecipeListInteractor

    @Before
    fun setup() {
        recipeDataSource = mock() // モック作成
        interactor = RecipeListInteractor(recipeDataSource)
    }

    @Test
    fun verifyFetchRecipeListSuccess() {
        // given
        val recipeEntities = listOf(
            RecipeEntity(
                id = "xxxx",
                title = "おいしいきゅうりの塩もみ",
                imagePath = "images/recipe.png",
                steps = listOf("きゅうりを切る", "塩をまく", "もむ"),
                authorName = "クックパド美"
            )
        )

        val onSuccess: (List<Recipe>) -> Unit = mock()
        whenever(recipeDataSource.fetchAll(any(), any())).then {
            // 引数として受け取ったコールバックをキャストして呼び出す
            (it.arguments[0] as (List<RecipeEntity>) -> Unit).invoke(recipeEntities)
        }

        // when
        interactor.fetchRecipeList(onSuccess, {})

        // then
        val argumentCaptor = argumentCaptor<List<Recipe>>()
        verify(onSuccess).invoke(argumentCaptor.capture())
        argumentCaptor.firstValue.first().also {
            assertThat(it.id).isEqualTo("xxxx")
            assertThat(it.title).isEqualTo("おいしいきゅうりの塩もみ")
            assertThat(it.imagePath).isEqualTo("images/recipe.png")
            assertThat(it.steps).isEqualTo("きゅうりを切る、塩をまく、もむ")
            assertThat(it.authorName).isEqualTo("クックパド美")
        }
    }

    @Test
    fun verifyFetchRecipeListError() {
        // given
        val recipeEntities = listOf(
            RecipeEntity(
                id = "xxxx",
                title = "おいしいきゅうりの塩もみ",
                imagePath = "images/recipe.png",
                steps = listOf("きゅうりを切る", "塩をまく", "もむ"),
                authorName = "クックパド美"
            )
        )

        val onSuccess: (List<Recipe>) -> Unit = mock()
        val onFailed: (Throwable) -> Unit = mock()
        whenever(recipeDataSource.fetchAll(any(), any())).then {
            (it.arguments[0] as (Throwable) -> Unit).invoke(RuntimeException())
        }

        // when
        interactor.fetchRecipeList(onSuccess, onFailed)

        // then
        val argumentCaptor = argumentCaptor<Exception>()
        verify(onFailed).invoke(argumentCaptor.capture())
        argumentCaptor.firstValue.also {
            println(it.toString())
            assertThat(it).isEqualTo(RuntimeException())
        }
    }
}

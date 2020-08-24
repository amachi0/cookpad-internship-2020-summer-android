package com.cookpad.android.minicookpad.recipelist

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cookpad.android.minicookpad.R
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class) // UI テスト用の JUnit テストランナー
class RecipeListFragmentTest {

    lateinit var presenter: RecipeListContract.Presenter
    @Before
    fun setup() {
        presenter = mock()
    }

    @Test
    fun verifyRecipeDetailNavigation() {
        // given
        whenever(presenter.onRecipeDetailRequested(any(), any())).then {}
        val fragmentScenario = launchFragmentInContainer(themeResId = R.style.AppTheme) { // アプリのテーマを設定
            RecipeListFragment()
        }.moveToState(Lifecycle.State.RESUMED)
        fragmentScenario.onFragment { it.presenter = presenter }
        onView(withText("鶏肉と胡瓜のあっさり塩炒め")).perform(click())
        verify(presenter).onRecipeDetailRequested(any(), eq("鶏肉と胡瓜のあっさり塩炒め"))
    }
}
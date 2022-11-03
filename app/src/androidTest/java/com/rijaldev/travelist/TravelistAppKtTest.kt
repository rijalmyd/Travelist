package com.rijaldev.travelist

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.rijaldev.travelist.model.TourismData
import com.rijaldev.travelist.ui.navigation.Screen
import com.rijaldev.travelist.ui.theme.TravelistTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TravelistAppKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.setContent {
            TravelistTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                TravelistApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigateToDetailWithData() {
        composeTestRule.onNodeWithTag("lazy_list").performScrollToIndex(6)
        composeTestRule.onNodeWithText(TourismData.dummyTourism[6].name).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(TourismData.dummyTourism[6].name).assertIsDisplayed()
    }

    @Test
    fun navHost_onFavoriteClick_shouldExistsInFavoritePage() {
        composeTestRule.onNodeWithText(TourismData.dummyTourism[0].name).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithTag("add_remove_favorite").performClick()
        composeTestRule.onNodeWithTag("back_button").performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(TourismData.dummyTourism[0].name).assertIsDisplayed()
    }

    @Test
    fun navHost_removeFavorite_shouldShowEmptyFavoriteList() {
        composeTestRule.onNodeWithText(TourismData.dummyTourism[0].name).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithTag("add_remove_favorite").performClick()
        composeTestRule.onNodeWithTag("back_button").performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(TourismData.dummyTourism[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("fav_button").performClick()
        composeTestRule.onNodeWithStringId(R.string.empty_favorite).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_Working() {
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickAboutMenu_shouldShowAboutMePage() {
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        composeTestRule.onNodeWithTag("about_page").performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithStringId(R.string.my_name).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.my_email).assertIsDisplayed()
    }

    @Test
    fun navHost_aboutMe_rightBack() {
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        composeTestRule.onNodeWithTag("about_page").performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithStringId(R.string.my_name).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.my_email).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Back").performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
    }

    @Test
    fun searchItem_shouldShowResult() {
        composeTestRule.onNodeWithStringId(R.string.placeholder_search).performTextInput("Bromo")
        composeTestRule.onNodeWithText("Bromo").assertIsDisplayed()
    }

    @Test
    fun searchItem_shouldShowEmptyData() {
        val wrongQuery = "$8jdks"
        composeTestRule.onNodeWithStringId(R.string.placeholder_search).performTextInput(wrongQuery)
        composeTestRule.onNodeWithTag("empty_data").assertIsDisplayed()
    }
}
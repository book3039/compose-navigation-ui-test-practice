package com.example.lunchtray

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LunchTrayNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupLunchTrayNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LunchTrayApp(navController)
        }
    }

    @Test
    fun lunchTrayNavHost_verifyStartDestination() {
        assertEquals(LunchTrayScreen.Start.name,
            navController.currentBackStackEntry?.destination?.route
        )
    }

    @Test
    fun lunchTrayNavHost_verifyBackButtonNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun lunchTrayNavHost_clickStartOrder_navigatesToEntreMenuScreen() {
        val startOrderText = composeTestRule.activity.getString(R.string.start_order)
        composeTestRule.onNodeWithText(startOrderText)
            .performClick()

        assertEquals(LunchTrayScreen.Entree.name,
            navController.currentBackStackEntry?.destination?.route
        )
    }

    @Test
    fun lunchTrayNavHost_clickBackOnEntreeScreen_navigatesToStartOrderScreen() {
        navigateToEntreeScreen()
        performNavigateUp()
        assertEquals(LunchTrayScreen.Start.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayNavHost_clickCancelOnEntreeScreen_navigatesToStartOrderScreen() {
        navigateToEntreeScreen()

        val cancelText = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithText(cancelText, ignoreCase = true)
            .performClick()

        assertEquals(LunchTrayScreen.Start.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickNextOnEntreeScreen_navigatesToSideDishScreen() {
        navigateToEntreeScreen()

        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(nextText, ignoreCase = true)
            .performClick()

        assertEquals(LunchTrayScreen.SideDish.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickBackOnSideDishScreen_navigatesToEntreeScreen() {
        navigateToSideDishScreen()
        performNavigateUp()

        assertEquals(LunchTrayScreen.Entree.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickCancelOnSideDishScreen_navigatesToStartOrderScreen() {
        navigateToSideDishScreen()

        val cancelText = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithText(cancelText, ignoreCase = true)
            .performClick()

        assertEquals(LunchTrayScreen.Start.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickNextOnSideDishScreen_navigatesToAccompanimentScreen() {
        navigateToSideDishScreen()

        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(nextText, ignoreCase = true)
            .performClick()

        assertEquals(LunchTrayScreen.Accompaniment.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickBackOnAccompanimentScreen_navigatesToSideDishScreen() {
        navigateToAccompanimentScreen()
        performNavigateUp()

        assertEquals(LunchTrayScreen.SideDish.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickCancelOnAccompanimentScreen_navigatesToStartOrderScreen() {
        navigateToAccompanimentScreen()

        val cancelText = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithText(cancelText, ignoreCase = true)
            .performClick()

        assertEquals(LunchTrayScreen.Start.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickNextOnAccompanimentScreen_navigatesToCheckoutScreen() {
        navigateToAccompanimentScreen()

        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(nextText, ignoreCase = true)
            .performClick()

        assertEquals(LunchTrayScreen.Checkout.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickBackOnCheckoutScreen_navigatesToAccompanimentScreen() {
        navigateToCheckoutScreen()
        performNavigateUp()

        assertEquals(LunchTrayScreen.Accompaniment.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickCancelOnCheckoutScreen_navigatesToStartOrderScreen() {
        navigateToCheckoutScreen()

        val cancelText = composeTestRule.activity.getString(R.string.cancel)
        composeTestRule.onNodeWithText(cancelText, ignoreCase = true)
            .performClick()

        assertEquals(LunchTrayScreen.Start.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun lunchTrayScreen_clickSubmitOnCheckoutScreen_navigatesToStartOrderScreen() {
        navigateToCheckoutScreen()

        val submitText = composeTestRule.activity.getString(R.string.submit)
        composeTestRule.onNodeWithText(submitText, ignoreCase = true)
            .performClick()

        assertEquals(LunchTrayScreen.Start.name,
            navController.currentBackStackEntry?.destination?.route)
    }

    private fun navigateToEntreeScreen() {
        val startOrderText = composeTestRule.activity.getString(R.string.start_order)
        composeTestRule.onNodeWithText(startOrderText)
            .performClick()

        composeTestRule.onNodeWithText("Cauliflower")
            .performClick()
    }

    private fun navigateToSideDishScreen() {
        navigateToEntreeScreen()

        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(nextText, ignoreCase = true)
            .performClick()

        composeTestRule.onNodeWithText("Summer Salad")
            .performClick()
    }

    private fun navigateToAccompanimentScreen() {
        navigateToSideDishScreen()

        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(nextText, ignoreCase = true)
            .performClick()

        composeTestRule.onNodeWithText("Lunch Roll")
            .performClick()
    }

    private fun navigateToCheckoutScreen() {
        navigateToAccompanimentScreen()

        val nextText = composeTestRule.activity.getString(R.string.next)
        composeTestRule.onNodeWithText(nextText, ignoreCase = true)
            .performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText)
            .performClick()
    }

}
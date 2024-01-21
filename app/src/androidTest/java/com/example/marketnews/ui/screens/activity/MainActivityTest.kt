package com.example.marketnews.ui.screens.activity

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marketnews.data.repository.MarketNewsRepository
import com.example.marketnews.utils.Constants.tagCustomRow
import com.example.marketnews.utils.Constants.tagTile
import com.example.marketnews.utils.Constants.tagTitleAuthor
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var repositoryTest: MarketNewsRepository

    @Test
    fun articles_list_is_displayed_successfully() = runTest {

        composeTestRule.waitUntil(
            timeoutMillis = 6000,
            condition = {
                composeTestRule.onAllNodesWithTag(tagCustomRow).fetchSemanticsNodes().isNotEmpty()
            }
        )

        composeTestRule.onAllNodesWithTag(tagCustomRow).get(index = 0).performClick();

        composeTestRule.waitUntil(
            timeoutMillis = 4000,
            condition = {
                composeTestRule.onAllNodesWithTag(tagTitleAuthor).fetchSemanticsNodes().isNotEmpty()
            }
        )

        composeTestRule.onAllNodesWithTag(tagTile).get(index = 0)
            .assertTextEquals("Long Covid More Severe In Women Than Men, Suggests Study - Mint")
    }

}
package com.rijaldev.travelist.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.rijaldev.travelist.model.Tourism
import com.rijaldev.travelist.ui.theme.TravelistTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeTourism = Tourism(
        id = 0,
        name = "Gunung Semeru",
        description = "Gunung Semeru atau Gunung Meru adalah sebuah gunung berapi kerucut di Jawa Timur, Indonesia. Gunung Semeru merupakan gunung tertinggi di Pulau Jawa, dengan puncaknya Mahameru, 3.676 meter dari permukaan laut (mdpl). Gunung ini terbentuk akibat subduksi Lempeng Indo-Australia kebawah Lempeng Eurasia. Gunung Semeru juga merupakan gunung berapi tertinggi ketiga di Indonesia setelah Gunung Kerinci di Sumatra dan Gunung Rinjani di Nusa Tenggara Barat.[2] Kawah di puncak Gunung Semeru dikenal dengan nama Jonggring Saloko.\nGunung Semeru secara administratif termasuk dalam wilayah dua kabupaten, yakni Kabupaten Malang dan Kabupaten Lumajang, Provinsi Jawa Timur. Gunung ini termasuk dalam kawasan Taman Nasional Bromo Tengger Semeru. Semeru mempunyai kawasan hutan Dipterokarp Bukit, hutan Dipterokarp Atas, hutan Montane, dan Hutan Ericaceous atau hutan gunung. Posisi geografis Semeru terletak antara 8°06' LS dan 112°55' BT.\nPada tahun 1913 dan 1946 Kawah Jonggring Saloka memiliki kubah dengan ketinggian 3.744,8 m hingga akhir November 1973. Di sebelah selatan, kubah ini mendobrak tepi kawah menyebabkan aliran lava mengarah ke sisi selatan meliputi daerah Pronojiwo dan Candipuro di Lumajang.",
        location = "Malang, Jawa Timur",
        photoUrl = "https://phinemo.com/wp-content/uploads/2018/03/keindahan-dari-sisi-bawah.jpg",
        rating = 9.8,
        isFavorite = false
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TravelistTheme {
                DetailContent(
                    id = fakeTourism.id,
                    name = fakeTourism.name,
                    description = fakeTourism.description,
                    photoUrl = fakeTourism.photoUrl,
                    location = fakeTourism.location,
                    rating = fakeTourism.rating,
                    isFavorite = fakeTourism.isFavorite,
                    navigateBack = {},
                    onFavoriteButtonClicked = {_, _ ->}
                )
            }
        }
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeTourism.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeTourism.description).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeTourism.location).assertIsDisplayed()
    }

    @Test
    fun addToFavoriteButton_hasClickAction() {
        composeTestRule.onNodeWithTag("add_remove_favorite").assertHasClickAction()
    }

    @Test
    fun detailContent_isScrollable() {
        composeTestRule.onNodeWithTag("scroll").performTouchInput {
            swipeUp()
        }
    }
}
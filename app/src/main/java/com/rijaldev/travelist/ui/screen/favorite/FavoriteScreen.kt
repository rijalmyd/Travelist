package com.rijaldev.travelist.ui.screen.favorite

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rijaldev.travelist.R
import com.rijaldev.travelist.model.Tourism
import com.rijaldev.travelist.ui.common.UiState
import com.rijaldev.travelist.ui.components.EmptyContent
import com.rijaldev.travelist.ui.screen.home.ListTourism
import com.rijaldev.travelist.ui.theme.TravelistTheme

@Composable
fun FavoriteScreen(
    moveToAboutPage: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteTourism()
            }
            is UiState.Success -> {
                FavoriteContent(
                    listTourism = uiState.data,
                    moveToAboutPage = moveToAboutPage,
                    navigateToDetail = navigateToDetail,
                    onFavoriteIconClicked = { id, newState ->
                        viewModel.updateTourismPlace(id, newState)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    listTourism: List<Tourism>,
    moveToAboutPage: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopBarContent(moveToAboutPage = moveToAboutPage)
        },
        modifier = modifier
    ) {
        if (listTourism.isNotEmpty()) {
            ListTourism(
                listTourism = listTourism,
                onFavoriteIconClicked = onFavoriteIconClicked,
                contentPaddingTop = 16.dp,
                navigateToDetail = navigateToDetail
            )
        } else {
            EmptyContent(
                contentText = stringResource(R.string.empty_favorite)
            )
        }
    }
}

@Composable
fun TopBarContent(
    moveToAboutPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.favorite))
        },
        elevation = 0.dp,
        actions = {
            IconButton(
                onClick = moveToAboutPage,
                modifier = Modifier
                    .testTag("about_page")
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "about_page"
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun FavoriteContentPreview() {
    TravelistTheme {
        FavoriteContent(
            listTourism = emptyList(),
            moveToAboutPage = {},
            navigateToDetail = {},
            onFavoriteIconClicked = { _, _ ->  }
        )
    }
}
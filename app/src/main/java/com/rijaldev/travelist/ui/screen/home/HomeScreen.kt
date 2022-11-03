package com.rijaldev.travelist.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rijaldev.travelist.R
import com.rijaldev.travelist.model.Tourism
import com.rijaldev.travelist.ui.common.UiState
import com.rijaldev.travelist.ui.components.EmptyContent
import com.rijaldev.travelist.ui.components.ItemTourism
import com.rijaldev.travelist.ui.components.SearchView
import com.rijaldev.travelist.ui.theme.TravelistTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.search(query)
            }
            is UiState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::search,
                    listTourism = uiState.data,
                    onFavoriteIconClicked = { id, newState ->
                        viewModel.updateTourismPlace(id, newState)
                    },
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listTourism: List<Tourism>,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        SearchView(
            query = query,
            onQueryChange = onQueryChange
        )
        if (listTourism.isNotEmpty()) {
            ListTourism(
                listTourism = listTourism,
                onFavoriteIconClicked = onFavoriteIconClicked,
                navigateToDetail = navigateToDetail
            )
        } else {
            EmptyContent(
                contentText = stringResource(R.string.empty_data),
                modifier = Modifier
                    .testTag("empty_data")
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListTourism(
    listTourism: List<Tourism>,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingTop: Dp = 0.dp,
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = contentPaddingTop),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .testTag("lazy_list")
    ) {
        items(listTourism, key = { it.id }) { item ->
            ItemTourism(
                id = item.id,
                photoUrl = item.photoUrl,
                title = item.name,
                location = item.location,
                rating = item.rating,
                isFavorite = item.isFavorite,
                onFavoriteIconClicked = onFavoriteIconClicked,
                modifier = Modifier
                    .animateItemPlacement(tween(durationMillis = 200))
                    .clickable { navigateToDetail(item.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    TravelistTheme {
        HomeContent(
            query = "",
            onQueryChange = {},
            listTourism = emptyList(),
            onFavoriteIconClicked = { _, _ ->  },
            navigateToDetail = {}
        )
    }
}
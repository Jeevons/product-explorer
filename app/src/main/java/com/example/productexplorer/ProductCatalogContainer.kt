package com.example.productexplorer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

// Le conteneur relie le ViewModel a l'ecran stateless.
@Composable
fun ProductCatalogContainer(
    onProductClick: (ProductUi) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductCatalogViewModel = viewModel()
) {
    ProductCatalogScreen(
        products = viewModel.filteredProducts,
        categories = viewModel.categories,
        searchQuery = viewModel.searchQuery,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        showOnlyInStock = viewModel.showOnlyInStock,
        onToggleStockFilter = viewModel::onToggleStockFilter,
        favoriteProductIds = viewModel.favoriteProductIds,
        onFavoriteClick = viewModel::onFavoriteClick,
        onProductClick = onProductClick,
        modifier = modifier
    )
}

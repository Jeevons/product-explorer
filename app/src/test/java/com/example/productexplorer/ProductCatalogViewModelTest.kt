package com.example.productexplorer

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductCatalogViewModelTest {
    @Test
    fun rechercheParCategorieMarqueEtTitre() {
        val viewModel = ProductCatalogViewModel()

        viewModel.onSearchQueryChange("AuDiO")
        assertEquals(listOf(2, 4), viewModel.uiState.value.products.map { it.id })

        viewModel.onSearchQueryChange("lumihome")
        assertEquals(listOf(5), viewModel.uiState.value.products.map { it.id })

        viewModel.onSearchQueryChange("Toto")
        assertEquals(listOf(1), viewModel.uiState.value.products.map { it.id })

        viewModel.onSearchQueryChange("introuvable")
        assertTrue(viewModel.uiState.value.products.isEmpty())

        viewModel.onSearchQueryChange("")
        assertEquals(5, viewModel.uiState.value.products.size)
    }

    @Test
    fun filtreStockCombineAvecLaRecherche() {
        val viewModel = ProductCatalogViewModel()

        viewModel.onSearchQueryChange("audio")
        viewModel.onToggleStockFilter()
        assertEquals(listOf(4), viewModel.uiState.value.products.map { it.id })

        viewModel.onSearchQueryChange("casque")
        assertTrue(viewModel.uiState.value.products.isEmpty())

        viewModel.onToggleStockFilter()
        assertEquals(listOf(2), viewModel.uiState.value.products.map { it.id })
    }

    @Test
    fun favorisIndependantsDuFiltrage() {
        val viewModel = ProductCatalogViewModel()
        val initialState = viewModel.uiState.value

        viewModel.onFavoriteClick(2)
        viewModel.onFavoriteClick(5)
        viewModel.onSearchQueryChange("audio")
        viewModel.onToggleStockFilter()

        // Le casque masque par le filtre reste dans les favoris.
        assertEquals(listOf(2, 5), viewModel.uiState.value.favoriteProductIds)

        viewModel.onFavoriteClick(2)
        assertEquals(listOf(5), viewModel.uiState.value.favoriteProductIds)

        viewModel.onFavoriteClick(2)
        assertEquals(listOf(5, 2), viewModel.uiState.value.favoriteProductIds)

        val currentState = viewModel.uiState.value
        assertEquals("audio", currentState.searchQuery)
        assertTrue(currentState.showOnlyInStock)
        assertEquals(listOf(4), currentState.products.map { it.id })
        assertEquals(initialState.categories, currentState.categories)

        // Les actions ont produit de nouveaux etats, sans modifier l'ancien.
        assertEquals("", initialState.searchQuery)
        assertEquals(5, initialState.products.size)
        assertTrue(initialState.favoriteProductIds.isEmpty())
    }
}

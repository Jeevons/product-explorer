package com.example.productexplorer

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductCatalogViewModelTest {
    @Test
    fun rechercheParCategorieMarqueEtTitre() {
        val viewModel = ProductCatalogViewModel()

        viewModel.onSearchQueryChange("AuDiO")
        assertEquals(listOf(2, 4), viewModel.filteredProducts.map { it.id })

        viewModel.onSearchQueryChange("lumihome")
        assertEquals(listOf(5), viewModel.filteredProducts.map { it.id })

        viewModel.onSearchQueryChange("Toto")
        assertEquals(listOf(1), viewModel.filteredProducts.map { it.id })

        viewModel.onSearchQueryChange("introuvable")
        assertTrue(viewModel.filteredProducts.isEmpty())

        viewModel.onSearchQueryChange("")
        assertEquals(5, viewModel.filteredProducts.size)
    }

    @Test
    fun filtreStockCombineAvecLaRecherche() {
        val viewModel = ProductCatalogViewModel()

        viewModel.onSearchQueryChange("audio")
        viewModel.onToggleStockFilter()
        assertEquals(listOf(4), viewModel.filteredProducts.map { it.id })

        viewModel.onSearchQueryChange("casque")
        assertTrue(viewModel.filteredProducts.isEmpty())

        viewModel.onToggleStockFilter()
        assertEquals(listOf(2), viewModel.filteredProducts.map { it.id })
    }

    @Test
    fun favorisIndependantsDuFiltrage() {
        val viewModel = ProductCatalogViewModel()

        viewModel.onFavoriteClick(2)
        viewModel.onFavoriteClick(5)
        viewModel.onSearchQueryChange("audio")
        viewModel.onToggleStockFilter()

        // Le casque masque par le filtre reste dans les favoris.
        assertEquals(listOf(2, 5), viewModel.favoriteProductIds)

        viewModel.onFavoriteClick(2)
        assertEquals(listOf(5), viewModel.favoriteProductIds)

        viewModel.onFavoriteClick(2)
        assertEquals(listOf(5, 2), viewModel.favoriteProductIds)
    }
}

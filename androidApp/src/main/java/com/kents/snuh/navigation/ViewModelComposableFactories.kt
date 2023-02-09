@file:Suppress("MatchingDeclarationName")
package com.kents.snuh.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.EntryPointAccessors
import com.kents.snuh.MainActivity.ViewModelFactoryProvider
import com.kents.snuh.features.details.DetailsViewModel

interface BaseViewModelFactoryProvider {
    fun getDetailsViewModelFactory(): DetailsViewModel.Factory
}

@Composable
fun detailViewModel(bookId: String): DetailsViewModel = viewModel(
    factory = DetailsViewModel.provideFactory(
        getViewModelFactoryProvider().getDetailsViewModelFactory(),
        bookId
    )
)

@Composable
private fun getViewModelFactoryProvider() = EntryPointAccessors.fromActivity(
    LocalContext.current as Activity,
    ViewModelFactoryProvider::class.java
)

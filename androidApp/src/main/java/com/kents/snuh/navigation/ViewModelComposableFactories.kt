@file:Suppress("MatchingDeclarationName")
package com.kents.snuh.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kents.core.domain.models.StateCapital
import dagger.hilt.android.EntryPointAccessors
import com.kents.snuh.MainActivity.ViewModelFactoryProvider
import com.kents.snuh.features.details.DetailsViewModel

interface BaseViewModelFactoryProvider {
    fun getDetailsViewModelFactory(): DetailsViewModel.Factory
}

//TODO fix details view model to take the StateCapital model rather than string.
@Composable
fun detailViewModel(stateCapital: String): DetailsViewModel = viewModel(
    factory = DetailsViewModel.provideFactory(
        getViewModelFactoryProvider().getDetailsViewModelFactory(),
        stateCapital
    )
)

@Composable
private fun getViewModelFactoryProvider() = EntryPointAccessors.fromActivity(
    LocalContext.current as Activity,
    ViewModelFactoryProvider::class.java
)

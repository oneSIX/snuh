package com.kents.snuh

import app.cash.turbine.test
import com.kents.core.domain.GetObservation
import com.kents.core.domain.models.Observation
import com.kents.core.domain.models.StateDisplayModel
import com.kents.snuh.features.list.ListViewModel
import com.kents.snuh.features.list.ListViewModel.ListScreenUiState.ErrorFromAPI
import com.kents.snuh.features.list.ListViewModel.ListScreenUiState.LoadingFromAPI
import com.kents.snuh.features.list.ListViewModel.ListScreenUiState.UpdateSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ViewModelAndCoroutinesExampleUnitTest {

    @get:Rule
    val coroutinesTestRule = MockMainDispatcherTestRule()

    private val getObservation = mockk<GetObservation>()

    private fun buildVM(): ListViewModel = ListViewModel(getObservation)

    @Test
    fun `Test Initial State`() = runTest(coroutinesTestRule.testDispatcher) {
        // Arrange
        val vm = buildVM()

        // Assert
        vm.state.test {
            assertThat(
                awaitItem(),
                instanceOf(LoadingFromAPI::class.java)
            )
        }
    }

    @Test
    fun `Test Refresh`() = runTest(coroutinesTestRule.testDispatcher) {
        // Arrange
        val result = Result.success(
            listOf(
                StateDisplayModel(
                    "test-city",
                    "test-state",
                    100.0,
                    "test-unit-code",
                    "test-time-stamp",
                    "test-geo-code"
                )
            )
        )


        //coEvery { getObservation.invoke("CODE") } returns result

        val vm = buildVM()

        // Act
        vm.refresh()

        // Assert
        vm.state.test {
            assertEquals(
                awaitItem(),
                UpdateSuccess(result.getOrThrow())
            )
        }
    }

    @Test
    fun `Test Error State`() = runTest(coroutinesTestRule.testDispatcher) {
        // Arrange
        val result: Result<List<StateDisplayModel>> = Result.failure(Exception(""))
//        coEvery { getObservation.invoke("1010") } returns result

        val vm = buildVM()

        // Act
        vm.refresh()

        // Assert
        vm.state.test {
            assertThat(
                awaitItem(),
                instanceOf(ErrorFromAPI::class.java)
            )
        }
    }
}

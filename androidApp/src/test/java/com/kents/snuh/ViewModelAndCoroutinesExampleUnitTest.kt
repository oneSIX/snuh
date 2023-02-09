package com.kents.snuh

import app.cash.turbine.test
import com.kents.core.domain.GetBooks
import com.kents.core.domain.models.Book
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

    private val getBooksMock = mockk<GetBooks>()

    private fun buildVM(): ListViewModel = ListViewModel(
        getBooksMock
    )

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
        val result = Result.success(listOf(Book("1", "bloco.io")))
        coEvery { getBooksMock.invoke() } returns result

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
        val result: Result<List<Book>> = Result.failure(Exception(""))
        coEvery { getBooksMock.invoke() } returns result

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

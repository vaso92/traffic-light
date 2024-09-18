package com.vasilv.traffic_light.presentation.screens.home

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

// Reusable JUnit4 TestRule to override the Main dispatcher
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun minimumCarModelLengthNotReached_formIsInvalid() = runTest {
        val homeViewModel = HomeViewModel()

        val carModel = "1"
        homeViewModel.onCarModelChange(carModel)

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            homeViewModel.state.collect()
        }

        assertEquals(carModel, homeViewModel.state.value.carModel)
        assertEquals(false, homeViewModel.state.value.isFormValid)
    }

    @Test
    fun minimumCarModelLengthReached_formIsValid() = runTest {
        val homeViewModel = HomeViewModel()

        val carModel = "1234"
        homeViewModel.onCarModelChange(carModel)

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            homeViewModel.state.collect()
        }

        advanceUntilIdle()

        assertEquals(carModel, homeViewModel.state.value.carModel)
        assertEquals(true, homeViewModel.state.value.isFormValid)
    }
}
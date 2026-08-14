package com.home.overseer.navigation

import androidx.navigation.NavController
import com.home.core.ui.AppNavigator
import com.home.overseer.R
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    private val navController: NavController,
) : AppNavigator {

    override fun openServersList() {
        navController.navigate(
            R.id.action_startFragment_to_serversListFragment
        )
    }

}
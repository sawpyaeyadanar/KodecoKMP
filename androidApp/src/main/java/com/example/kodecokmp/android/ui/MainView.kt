package com.example.kodecokmp.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kodecokmp.android.MyApplicationTheme
import com.example.kodecokmp.android.bottomNavigationItems

// 1
@Composable
// 2 This function takes a function that can provide a top bar (toolbar on Android) and
// defaults to an empty composable.
fun MainView(actionBarFun: topBarFun = { EmptyComposable() }) {
    // 3 Hold the state for showing the add dialog.
    // If the state object is true then the app will show a dialog,
    // otherwise it will hide the add dialog.
    val showAddDialog = remember { mutableStateOf(false) }
    // 4 Hold the state containing a list of current time zone strings.
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }
    // 5 Hold the state containing the currently selected index.
    val selectedIndex = remember { mutableIntStateOf(0)}
    // 6 Use the current theme composable.
    MyApplicationTheme {
        // TODO: Add Scaffold   1
        Scaffold(
            topBar = {
                // TODO: Add Toolbar    2
                actionBarFun(selectedIndex.intValue)

            },
            floatingActionButton = {
                // TODO: Add Floating action button     3
                if (selectedIndex.intValue == 0) {
                    // 1
                    FloatingActionButton(
                        // 2
                        modifier = Modifier
                            .padding(16.dp),
                        shape = FloatingActionButtonDefaults.largeShape,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        // 3
                        onClick = {
                            showAddDialog.value = true
                        }
                    ) {
                        // 4
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Timezone"
                        )
                    }
                }

            },
            bottomBar = {
                // TODO: Add bottom bar     4
                // 1
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    // 2
                    bottomNavigationItems.forEachIndexed { i, bottomNavigationItem ->
                        // 3
                        NavigationBarItem(
                            colors =  NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedIconColor = Color.Black,
                                unselectedTextColor = Color.Black,
                                indicatorColor = MaterialTheme.colorScheme.primary,
                            ),
                            label = {
                                Text(bottomNavigationItem.route, style = MaterialTheme.typography.bodyMedium)
                            },
                            // 4
                            icon = {
                                Icon(
                                    bottomNavigationItem.icon,
                                    contentDescription = bottomNavigationItem.iconContentDescription
                                )
                            },
                            // 5
                            selected = selectedIndex.intValue == i,
                            // 6
                            onClick = {
                                selectedIndex.intValue = i
                            }
                        )
                    }
                }

            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                // TODO: Replace with Dialog        //6
                // 1
                if (showAddDialog.value) {
                    AddTimeZoneDialog(
                        // 2
                        onAdd = { newTimezones ->
                            showAddDialog.value = false
                            for (zone in newTimezones) {
                                // 3
                                if (!currentTimezoneStrings.contains(zone)) {
                                    currentTimezoneStrings.add(zone)
                                }
                            }
                        },
                        onDismiss = {
                            // 4
                            showAddDialog.value = false
                        },
                    )
                }

                // TODO: Replace with screens           //5
                when (selectedIndex.intValue	) {
                    0 -> TimeZoneScreen(currentTimezoneStrings)
                    // 1 -> FindMeetingScreen(currentTimezoneStrings)
                }


            }
        }

    }
}

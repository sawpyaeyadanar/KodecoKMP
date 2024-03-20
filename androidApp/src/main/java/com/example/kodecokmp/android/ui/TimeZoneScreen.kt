package com.example.kodecokmp.android.ui

import AnimatedSwipeDismiss
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kodecokmp.TimeZoneHelper
import com.example.kodecokmp.TimeZoneHelperImpl
import kotlinx.coroutines.delay

const val timeMillis = 1000 * 60L // 1 second
@Composable                 //2
fun TimeZoneScreen(
    currentTimezoneStrings: SnapshotStateList<String>
) {
    // 1
    val timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
    // 2
    val listState = rememberLazyListState()
    // 3
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // TODO: Add Content                //2
        // 1 Remember the current time. Note that here you are using by instead of =.
        // In this case, the time variable is the current time in a string format.
        var time by remember { mutableStateOf(timezoneHelper.currentTime()) }
// 2 It will be launched once but continue to run.
// The method will get the updated time every minute.
// You pass Unit as a parameter to LaunchedEffect so that it is not canceled and
// re-launched when LaunchedEffect is recomposed.
        LaunchedEffect(Unit) {
            while (true) {
                time = timezoneHelper.currentTime()
                delay(timeMillis) // Every minute
            }
        }
// 3
        LocalTimeCard(
            city = timezoneHelper.currentTimeZone(),
            time = time, date = timezoneHelper.getDate(timezoneHelper.currentTimeZone())
        )
        Spacer(modifier = Modifier.size(16.dp))

// TODO: Add Timezone items //3

        // 1
        LazyColumn(
            state = listState,
        ) {
            // 2
            items(currentTimezoneStrings.size,
                // 3
                key = { timezone ->
                    timezone
                }) { index ->
                val timezoneString = currentTimezoneStrings[index]
                // 4
                AnimatedSwipeDismiss(
                    item = timezoneString,
                    // 5
                    background = { _ ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(50.dp)
                                .background(Color.Red)
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp
                                )
                        ) {
                            val alpha = 1f
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd),
                                tint = Color.White.copy(alpha = alpha)
                            )
                        }
                    },
                    content = {
                        // 6
                        TimeCard(
                            timezone = timezoneString,
                            hours = timezoneHelper.hoursFromTimeZone(timezoneString),
                            time = timezoneHelper.getTime(timezoneString),
                            date = timezoneHelper.getDate(timezoneString)
                        )
                    },
                    // 7
                    onDismiss = { zone ->
                        if (currentTimezoneStrings.contains(zone)) {
                            currentTimezoneStrings.remove(zone)
                        }
                    }
                )
            }
        }

    }
}


package com.example.kodecokmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kodecokmp.android.ui.MainView
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1
        Napier.base(DebugAntilog())
        setContent {
            // 2
            MainView {
                // 3
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                    title = {
                        // 4
                        when (it) {
                            0 -> androidx.compose.material.Text(text = stringResource(R.string.world_clocks))
                            else -> androidx.compose.material.Text(text = stringResource(R.string.findmeeting))
                        }
                    })
            }
        }

    }
}

@Composable
fun GreetingView(text: String) {
    Text(text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

package com.example.kodecokmp.android.ui

import androidx.compose.runtime.Composable

// 1 Define an alias named OnAddType that takes a list of strings and doesn’t return anything.
typealias OnAddType =  (List<String>) -> Unit
// 2 Define an alias used when dismissing a dialog.
typealias onDismissType =  () -> Unit
// 3 Define a composable function.
typealias composeFun =  @Composable () -> Unit
// 4 Define a function that takes an integer.
typealias topBarFun =  @Composable (Int) -> Unit

// 5 Define an empty composable function (as a default variable for the Top Bar).
@Composable
fun EmptyComposable() {
}


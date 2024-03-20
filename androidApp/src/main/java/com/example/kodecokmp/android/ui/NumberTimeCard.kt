package com.example.kodecokmp.android.ui

import android.annotation.SuppressLint
import android.widget.NumberPicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kodecokmp.TimeZoneHelperImpl
import com.example.kodecokmp.android.MyApplicationTheme

// 1
@Composable
fun NumberTimeCard(label: String, hour: MutableState<Int>) {
    // 2
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        // 3
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // 4
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Black)
            )
            Spacer(modifier = Modifier.size(16.dp))
            // 5
            NumberPicker(hour = hour, range = IntRange(0, 23),
                onStateChanged = {
                    hour.value = it
                })
        }
    }
}

@Preview
@Composable
fun NumberTimeCardPreview() {
    val hour =  remember {
        mutableStateOf(123)
    }
    MyApplicationTheme {
        NumberTimeCard(label = "Label", hour = hour)
    }
}

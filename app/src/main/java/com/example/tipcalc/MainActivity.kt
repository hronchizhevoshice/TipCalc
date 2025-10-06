package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalc.ui.theme.TipCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Order(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Order(modifier: Modifier = Modifier)
{
    var orderAmount by remember { mutableStateOf("") }
    var Count by remember { mutableStateOf("") }
    var sliderValue by remember { mutableStateOf(0f) }

    val discount = when (Count.toIntOrNull() ?: 0) {
        in 1..2 -> 3
        in 3..5 -> 5
        in 6..10 -> 7
        in 11..Int.MAX_VALUE -> 10
        else -> 0
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(top = 50.dp)
            .padding(horizontal = 20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Сумма заказа:",
                fontSize = 15.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(20.dp)
                    .background(Color(0xFFFFC0CB))
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
            androidx.compose.foundation.text.BasicTextField(
                modifier = Modifier
                    .width(150.dp),
                value = orderAmount,
                onValueChange = { orderAmount = it },
                singleLine = true,
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black)
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Количество блюд:",
                fontSize = 15.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(20.dp)
                    .background(Color(0xFFFFC0CB))
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                androidx.compose.foundation.text.BasicTextField(
                    modifier = Modifier
                        .width(50.dp),
                    value = Count,
                    onValueChange = { Count = it },
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black)
                )
            }
        }
        // Слайдер
        Column {
            Text(
                text = "Чаевые:",
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..25f,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "0")
                Text(text = "25")
            }
        }
        //скидка
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = "Скидка:",
                fontSize = 20.sp,
                modifier=modifier.padding(top = 10.dp)
            )
            Row()
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    androidx.compose.material3.RadioButton(
                        selected = discount == 3,
                        onClick = {},
                        enabled = false
                    )
                    Text("3%")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    androidx.compose.material3.RadioButton(
                        selected = discount == 5,
                        onClick = {},
                        enabled = false
                    )
                    Text("5%")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    androidx.compose.material3.RadioButton(
                        selected = discount == 7,
                        onClick = {},
                        enabled = false
                    )
                    Text("7%")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    androidx.compose.material3.RadioButton(
                        selected = discount == 10,
                        onClick = {},
                        enabled = false
                    )
                    Text("10%")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderPreview()
{
    TipCalcTheme {
        Order()
    }
}
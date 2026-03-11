package com.example.cmpstudy.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmpstudy.composeapp.generated.resources.*
import com.example.cmpstudy.White
import com.example.cmpstudy.chart.presentation.BarChartSection
import com.example.cmpstudy.chart.presentation.DonutChartSection
import com.example.cmpstudy.chart.presentation.LineChartSection
import com.example.cmpstudy.chart.presentation.PieChartSection
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChartScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        stringResource(Res.string.line_chart),
        stringResource(Res.string.bar_chart),
        stringResource(Res.string.pie_chart),
        stringResource(Res.string.donut_chart)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .systemBarsPadding()
    ) {
        SecondaryScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = White,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            when (selectedTabIndex) {
                0 -> LineChartSection()
                1 -> BarChartSection()
                2 -> PieChartSection()
                3 -> DonutChartSection()
            }
        }
    }
}

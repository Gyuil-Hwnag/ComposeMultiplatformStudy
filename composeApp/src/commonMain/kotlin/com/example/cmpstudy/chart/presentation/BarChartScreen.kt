package com.example.cmpstudy.chart.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cmpstudy.composeapp.generated.resources.*
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters
import com.example.cmpstudy.core.presentation.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun BarChartSection() {
    Text(
        text = stringResource(Res.string.bar_chart),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    val barParameters = listOf(
        BarParameters(
            dataName = stringResource(Res.string.android),
            data = listOf(65.0, 80.0, 55.0, 90.0, 75.0),
            barColor = AndroidColor
        ),
        BarParameters(
            dataName = stringResource(Res.string.ios),
            data = listOf(85.0, 70.0, 95.0, 60.0, 100.0),
            barColor = iosColor
        ),
        BarParameters(
            dataName = stringResource(Res.string.web),
            data = listOf(50.0, 60.0, 40.0, 80.0, 70.0),
            barColor = WebColor
        ),
        BarParameters(
            dataName = stringResource(Res.string.desktop),
            data = listOf(85.0, 80.0, 95.0, 90.0, 60.0),
            barColor = DesktopColor
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        BarChart(
            chartParameters = barParameters,
            gridColor = Color.LightGray,
            xAxisData = listOf("Q1", "Q2", "Q3", "Q4", "연간"),
            isShowGrid = true,
            animateChart = true,
            showGridWithSpacer = true,
            yAxisStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            xAxisStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            yAxisRange = 14,
            barWidth = 20.dp
        )
    }
}

@PreviewDevices
@Composable
private fun BarChartPreview() {
    BarChartSection()
}

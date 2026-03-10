package com.example.cmpstudy.chart.presentation

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
import com.aay.compose.donutChart.DonutChart
import com.aay.compose.donutChart.model.PieChartData
import com.example.cmpstudy.core.presentation.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun DonutChartSection() {
    Text(
        text = stringResource(Res.string.donut_chart),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    val donutChartData = listOf(
        PieChartData(
            partName = stringResource(Res.string.android),
            data = 50.0,
            color = AndroidColor
        ),
        PieChartData(
            partName = stringResource(Res.string.ios),
            data = 25.0,
            color = iosColor
        ),
        PieChartData(
            partName = stringResource(Res.string.web),
            data = 15.0,
            color = WebColor
        ),
        PieChartData(
            partName = stringResource(Res.string.desktop),
            data = 10.0,
            color = DesktopColor
        )
    )

    DonutChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        pieChartData = donutChartData,
        centerTitle = "Languages",
        centerTitleStyle = MaterialTheme.typography.titleMedium,
        outerCircularColor = Color.LightGray,
        innerCircularColor = Color.Gray,
        ratioLineColor = Color.LightGray,
        textRatioStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
    )
}

@PreviewDevices
@Composable
private fun DonutChartPreview() {
    DonutChartSection()
}

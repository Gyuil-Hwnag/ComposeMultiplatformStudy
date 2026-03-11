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
import com.aay.compose.donutChart.PieChart
import com.aay.compose.donutChart.model.PieChartData
import com.example.cmpstudy.core.presentation.AndroidColor
import com.example.cmpstudy.core.presentation.DesktopColor
import com.example.cmpstudy.core.presentation.WebColor
import com.example.cmpstudy.core.presentation.iosColor
import org.jetbrains.compose.resources.stringResource

@Composable
fun PieChartSection() {
    Text(
        text = stringResource(Res.string.pie_chart),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    val pieChartData = listOf(
        PieChartData(
            partName = stringResource(Res.string.android),
            data = 45.0,
            color = AndroidColor
        ),
        PieChartData(
            partName = stringResource(Res.string.ios),
            data = 35.0,
            color = iosColor
        ),
        PieChartData(
            partName = stringResource(Res.string.web),
            data = 15.0,
            color = WebColor
        ),
        PieChartData(
            partName = stringResource(Res.string.desktop),
            data = 5.0,
            color = DesktopColor
        )
    )

    PieChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        pieChartData = pieChartData,
        ratioLineColor = Color.LightGray,
        textRatioStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
    )
}

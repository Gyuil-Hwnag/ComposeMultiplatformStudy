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
import com.aay.compose.lineChart.LineChart
import com.aay.compose.lineChart.model.LineParameters
import com.aay.compose.lineChart.model.LineType
import com.example.cmpstudy.core.presentation.AndroidColor
import com.example.cmpstudy.core.presentation.DesktopColor
import com.example.cmpstudy.core.presentation.WebColor
import com.example.cmpstudy.core.presentation.iosColor
import org.jetbrains.compose.resources.stringResource

@Composable
fun LineChartSection() {
    Text(
        text = stringResource(Res.string.line_chart),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    val lineParameters = listOf(
        LineParameters(
            label = stringResource(Res.string.android),
            data = listOf(65.0, 80.0, 55.0, 90.0, 75.0),
            lineColor = AndroidColor,
            lineType = LineType.CURVED_LINE,
            lineShadow = true
        ),
        LineParameters(
            label = stringResource(Res.string.ios),
            data = listOf(85.0, 70.0, 95.0, 60.0, 100.0),
            lineColor = iosColor,
            lineType = LineType.CURVED_LINE,
            lineShadow = true
        ),
        LineParameters(
            label = stringResource(Res.string.web),
            data = listOf(50.0, 60.0, 40.0, 80.0, 70.0),
            lineColor = WebColor,
            lineType = LineType.CURVED_LINE,
            lineShadow = true
        ),
        LineParameters(
            label = stringResource(Res.string.desktop),
            data = listOf(85.0, 80.0, 95.0, 90.0, 60.0),
            lineColor = DesktopColor,
            lineType = LineType.CURVED_LINE,
            lineShadow = true
        )
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        linesParameters = lineParameters,
        isGrid = true,
        gridColor = Color.LightGray,
        xAxisData = listOf("1월", "2월", "3월", "4월", "5월"),
        animateChart = true,
        showGridWithSpacer = true,
        yAxisStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
        xAxisStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
        yAxisRange = 14,
        oneLineChart = false,
        gridOrientation = com.aay.compose.baseComponents.model.GridOrientation.VERTICAL
    )
}

package com.che.architecture.atomic.design.molecules

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.che.architecture.atomic.design.foundation.dimension.LocalPadding

private data class Point(val x: Float, val y: Float)

@Composable
fun DrawChart(
    modifier: Modifier = Modifier,
    chartPoints: List<Double>
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(LocalPadding.current.s)
    ) {
        val points = mutableListOf<Point>()
        val distance = size.width / (chartPoints.size)
        var x = 0F
        val max = chartPoints.maxOrNull() ?: 0.0

        chartPoints.forEachIndexed { index, currentData ->
            if (chartPoints.size >= index + 2) {
                val y0 = (max - currentData) * (size.height / max)
                val x0 = x + distance
                points.add(Point(x0, y0.toFloat()))
                x += distance
            }
        }

        val cubicPointsFirst = mutableListOf<Point>()
        val cubicPointsSecond = mutableListOf<Point>()

        for (i in 1 until points.size) {
            cubicPointsFirst.add(Point((points[i].x + points[i - 1].x) / 2, points[i - 1].y))
            cubicPointsSecond.add(Point((points[i].x + points[i - 1].x) / 2, points[i].y))
        }

        val path = Path()
        path.moveTo(points[0].x, points[0].y)

        for (i in 1 until points.size) {
            path.cubicTo(
                cubicPointsFirst[i - 1].x,
                cubicPointsFirst[i - 1].y,
                cubicPointsSecond[i - 1].x,
                cubicPointsSecond[i - 1].y,
                points[i].x,
                points[i].y
            )
        }

        drawPath(path, color = Color.Green, style = Stroke(width = 8f))

        val offset = 20f
        val strokeWidth = 4f

        drawLine(
            start = Offset(x = points[0].x - offset, y = points[0].y + offset),
            end = Offset(x = points.last().x + offset, y = points[0].y + offset),
            color = Color.Black,
            strokeWidth = strokeWidth
        )
        drawLine(
            start = Offset(x = points[0].x - offset, y = points[0].y + offset),
            end = Offset(x = points[0].x - offset, y = max.toFloat() - offset),
            color = Color.Black,
            strokeWidth = strokeWidth
        )
    }
}

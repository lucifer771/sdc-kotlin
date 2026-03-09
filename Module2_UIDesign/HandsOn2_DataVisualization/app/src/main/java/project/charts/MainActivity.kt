package project.charts

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.*
import com.github.mikephil.charting.data.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var dataSpinner: Spinner
    private lateinit var graphSpinner: Spinner
    private lateinit var loadButton: Button

    private lateinit var barChart: BarChart
    private lateinit var lineChart: LineChart
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataSpinner = findViewById(R.id.dataSpinner)
        graphSpinner = findViewById(R.id.graphSpinner)
        loadButton = findViewById(R.id.loadButton)

        barChart = findViewById(R.id.barChart)
        lineChart = findViewById(R.id.lineChart)
        pieChart = findViewById(R.id.pieChart)

        setupSpinners()

        loadButton.setOnClickListener {
            val datasetRawId = getSelectedDataset()
            val graphType = graphSpinner.selectedItem.toString()

            val data = loadJsonData(datasetRawId)

            showSelectedChart(graphType, data)
        }
    }

    private fun setupSpinners() {

        val datasets = listOf("Monthly Sales", "Category Sales", "Student Performance")
        val graphs = listOf("Bar Chart", "Line Chart", "Pie Chart")

        dataSpinner.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item,
            datasets)

        graphSpinner.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item,
            graphs)
    }

    private fun getSelectedDataset(): Int {
        return when (dataSpinner.selectedItem.toString()) {
            "Monthly Sales" -> R.raw.monthly_data
            "Category Sales" -> R.raw.category_data
            "Student Performance" -> R.raw.student_data
            else -> R.raw.monthly_data
        }
    }

    private fun loadJsonData(rawId: Int): List<ChartData> {

        val inputStream = resources.openRawResource(rawId)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val jsonObject = JSONObject(jsonString)
        val salesArray = jsonObject.getJSONArray("sales")

        val list = mutableListOf<ChartData>()

        for (i in 0 until salesArray.length()) {
            val item = salesArray.getJSONObject(i)
            list.add(
                ChartData(
                    item.getString("month"),
                    item.getDouble("value").toFloat()
                )
            )
        }

        return list
    }

    private fun showSelectedChart(type: String, data: List<ChartData>) {

        barChart.visibility = View.GONE
        lineChart.visibility = View.GONE
        pieChart.visibility = View.GONE

        when (type) {

            "Bar Chart" -> {
                barChart.visibility = View.VISIBLE
                setupBarChart(data)
                animateView(barChart)
            }

            "Line Chart" -> {
                lineChart.visibility = View.VISIBLE
                setupLineChart(data)
                animateView(lineChart)
            }

            "Pie Chart" -> {
                pieChart.visibility = View.VISIBLE
                setupPieChart(data)
                animateView(pieChart)
            }
        }
    }

    private fun setupBarChart(data: List<ChartData>) {

        val entries = data.mapIndexed { index, item ->
            BarEntry(index.toFloat(), item.value)
        }

        val dataSet = BarDataSet(entries, "Data")
        dataSet.color = Color.CYAN
        dataSet.valueTextColor = Color.WHITE

        barChart.data = BarData(dataSet)
        barChart.setBackgroundColor(Color.TRANSPARENT)
        barChart.animateY(1000)
        barChart.invalidate()
    }

    private fun setupLineChart(data: List<ChartData>) {

        val entries = data.mapIndexed { index, item ->
            Entry(index.toFloat(), item.value)
        }

        val dataSet = LineDataSet(entries, "Trend")
        dataSet.color = Color.GREEN
        dataSet.valueTextColor = Color.WHITE

        lineChart.data = LineData(dataSet)
        lineChart.setBackgroundColor(Color.TRANSPARENT)
        lineChart.animateX(1000)
        lineChart.invalidate()
    }

    private fun setupPieChart(data: List<ChartData>) {

        val entries = data.map {
            PieEntry(it.value, it.month)
        }

        val dataSet = PieDataSet(entries, "Distribution")
        dataSet.colors = listOf(
            Color.RED, Color.BLUE, Color.GREEN,
            Color.YELLOW, Color.MAGENTA
        )

        pieChart.data = PieData(dataSet)
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.animateY(1000)
        pieChart.invalidate()
    }

    private fun animateView(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 600
        view.startAnimation(animation)
    }
}
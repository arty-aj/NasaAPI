package com.example.apiskotlin

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.apiskotlin.model.ApodResponse
import com.example.apiskotlin.viewmodel.ApodDateViewModel
import com.example.apiskotlin.viewmodel.ApodViewModel
import com.ortiz.touchview.TouchImageView
import java.util.*


class MainActivity : AppCompatActivity()
{
    private lateinit var date: EditText
    private lateinit var apiDate: String
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var apodViewModel: ApodViewModel
    private lateinit var apodDateViewModel: ApodDateViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNasaApi()
        refreshApp()

        date = findViewById(R.id.date_picker)
        date.setOnClickListener {
            // calendar class's instance and get the current date, month, and year from the calendar
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR) // current year
            val mMonth = c.get(Calendar.MONTH) // current month
            val mDay = c.get(Calendar.DAY_OF_MONTH) // current day

            // date picker dialog
            datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    // set day of the month, month, and year value in the edit text
                    date.setText("$year-${monthOfYear + 1}-$dayOfMonth")
                    apiDate = "$year-${monthOfYear + 1}-$dayOfMonth"
                    //Toast.makeText(this, "New date $apiDate", Toast.LENGTH_SHORT).show()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(date.windowToken, 0)
                    newApod()
                },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }
    }


    private fun newApod(){
        apodDateViewModel = ViewModelProvider(this).get(ApodDateViewModel::class.java)

        //call api from view model
        apodDateViewModel.getApiData(apiDate)

        // live data observer
        apodDateViewModel.ApodResponseDataList.observe(this, Observer{
            //initAdapter(it)
            updateUI(it)
        })
    }

    private fun refreshApp(){
        val swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)

        swipeToRefresh.setOnRefreshListener {
            initNasaApi()
            Toast.makeText(this, "Now showing today's APOD!", Toast.LENGTH_SHORT).show()
            swipeToRefresh.isRefreshing = false
        }
    }



    fun initNasaApi(){
        apodViewModel = ViewModelProvider(this).get(ApodViewModel::class.java)

        //call api from view model
        apodViewModel.getApiData()

        // live data observer
        apodViewModel.ApodResponseDataList.observe(this, Observer{
            //initAdapter(it)
            updateUI(it)
        })

    }

    private fun updateUI(data: ApodResponse){
        val imageView = findViewById<TouchImageView>(R.id.zoomImage)
        findViewById<TextView>(R.id.tvTitle).text = data.title
        findViewById<TextView>(R.id.tvDate).text = data.date
        findViewById<TextView>(R.id.tvExplanation).text = data.explanation
        findViewById<TextView>(R.id.tvCopyright).text = data.copyright

        Glide.with(this)
            .load(data.hdurl)
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .into(imageView)
    }
/*
    private fun initAdapter(data: ApodResponse){
        var rvApodList: RecyclerView = findViewById(R.id.rvApodList)
        rvApodList.layoutManager = LinearLayoutManager(this)
        val adapter = ApodAdapter(data)
        rvApodList.adapter = adapter
    }
 */
}
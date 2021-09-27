package com.abrselmantutorials.day1assignment

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val TAG = "SelmanTag"

    val country = arrayOf("India", "USA", "China", "Japan", "Other")
    private lateinit var editText: EditText
    private lateinit var spinner: Spinner
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var radioGroup: RadioGroup
    private lateinit var hikingHobby: CheckBox
    private lateinit var programmingHobby: CheckBox
    private lateinit var otherHobby: CheckBox
    private lateinit var detailsView: TextView
    private lateinit var countrySelected: String
    private lateinit var gender: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.name_edit)
        spinner = findViewById(R.id.country_list)
        spinner.onItemSelectedListener = this
        arrayAdapter =
            ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, country)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        countrySelected = ""
        radioGroup = findViewById(R.id.radio_gender)
        gender = ""
        hikingHobby = findViewById(R.id.hiking_hobby)
        programmingHobby = findViewById(R.id.programming_hobby)
        otherHobby = findViewById(R.id.other_hobby)
        detailsView = findViewById(R.id.details_view)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        countrySelected = country[position]
        Toast.makeText(
            this@MainActivity,
            "The country selected is ${country[position]}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        countrySelected = ""
        Toast.makeText(
            this@MainActivity,
            "Please make sure to select the country",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onclickOfRadioButton(view: View) {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        if (selectedRadioButtonId == -1) {
            gender = ""
            Toast.makeText(this@MainActivity, "Please select gender", Toast.LENGTH_SHORT).show()
        } else {
            val selectedRadioButtonById = findViewById<RadioButton>(selectedRadioButtonId)
            gender = selectedRadioButtonById.text.toString()
            Toast.makeText(
                this@MainActivity,
                "you have selected ${selectedRadioButtonById.text} as gender",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun showDetails(view: View) {
        val detailsString = StringBuilder()
        detailsString.append("Name: ${editText.text}\n")
        detailsString.append("Country: $countrySelected \n")
        detailsString.append("Gender: $gender \n")
        detailsString.append("Hobby: ${getHobby()} \n")
        detailsView.text = detailsString.toString()
    }

    private fun getHobby(): String {
        val hobbyBuilder = StringBuilder()

        if (programmingHobby.isChecked) {
            hobbyBuilder.append("Programming\n")
        }
        if (hikingHobby.isChecked) {
            hobbyBuilder.append("Hiking\n")
        }
        if (otherHobby.isChecked) {
            hobbyBuilder.append("Other")
        }
        return hobbyBuilder.toString()
    }


}
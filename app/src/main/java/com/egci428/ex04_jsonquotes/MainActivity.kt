package com.egci428.ex04_jsonquotes

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.egci428.ex04_jsonquotes.Helper.Helper
import com.egci428.ex04_jsonquotes.Models.Quote
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteBtn.setOnClickListener {
            var asyncTask = object:AsyncTask<String, String, String>(){

                override fun onPreExecute() {
                    Toast.makeText(this@MainActivity,"Please wait...", Toast.LENGTH_SHORT).show()
                }

                override fun doInBackground(vararg p0: String?): String {
                    val helper = Helper()
                    return helper.getHTTPData("https://talaikis.com/api/quotes/random/")
                }

                override fun onPostExecute(result: String?) {
                    val quotetext = Gson().fromJson(result, Quote::class.java)
                    txtQuote.text = quotetext.quote
                    if (txtQuote.visibility == INVISIBLE)
                        txtQuote.visibility = VISIBLE
                }


            }

            asyncTask.execute()
        }
    }
}

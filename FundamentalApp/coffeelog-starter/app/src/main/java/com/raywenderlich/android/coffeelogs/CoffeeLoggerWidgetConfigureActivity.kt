package com.raywenderlich.android.coffeelogs

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class CoffeeLoggerWidgetConfigureActivity : AppCompatActivity() {

    // variable below for to use these fields later to save the limit value for each widget.
    private lateinit var appWidgetText: EditText
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    private val coffeeLoggerPersistence = CoffeeLoggerPersistence(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_logger_widget_configure)
        //1. Find the EditText in the layout.
        appWidgetText = findViewById(R.id.appwidget_text)
        //2. Get the extras from the Intent that launched the Activity.
        val extras: Bundle? = intent.extras
        //3. Extract the appWidgetId of the widget.
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        }
        //4. Make sure that if the user does’nt press the “Save Configuration” button, the widget is not added.
        setResult(Activity.RESULT_CANCELED)

        // Attach this listener to these code, This is a chained instruction that finds the Button object and sets its listener.
        findViewById<View>(R.id.add_button).setOnClickListener(onClickListener)
    }

    private var onClickListener: View.OnClickListener = View.OnClickListener {
        // 1. Get the text input – the coffee limit.
        val widgetText = appWidgetText.text.toString()
        // 2. save the limit to local storage (using the Widget id).
        coffeeLoggerPersistence.saveLimitPref(widgetText.toInt(), appWidgetId)
        updateWidget()
        // 3. Create a new Intent to return to the caller of the Activity and add the id of the Widget you’re configuring.
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        // 4. Tell the operating system that the configuration is OK. Do this by passing an Intent that contains the widget id.
        setResult(RESULT_OK, resultValue)
        // 5. Close the configuration screen
        finish()
    }

    /**
     * This Function
     * The function retrieves the AppWidgetManager and triggers an update to the corresponding widget.
     * Call this function in the OnClickListener after saving the coffee limit to coffeeLoggerPersistence
     */
    private fun updateWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        CoffeeLoggerWidget.updateAppWidget(this, appWidgetManager, appWidgetId)
    }
}
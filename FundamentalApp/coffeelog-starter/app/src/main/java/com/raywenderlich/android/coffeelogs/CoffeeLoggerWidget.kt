package com.raywenderlich.android.coffeelogs

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class CoffeeLoggerWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        // code bellow creates an Intent, puts the Widget ids in the intent, and starts the Service.
        val intent = Intent(context.applicationContext, CoffeeQuotesService::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
        context.startService(intent)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        /**
         * Below Method
         * updateAppWidget() is a convenience method the Android Studio wizard created in order to encapsulate the update logic for a given Widget.
         */
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val coffeeLoggerPersistence = CoffeeLoggerPersistence(context) // adding CoffeeLoggerPersistence
            val widgetText = coffeeLoggerPersistence.loadTitlePref().toString()

            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.coffee_logger_widget)
                // below for every time widget update, you'll see new code
            views.setTextViewText(R.id.appwidget_text, widgetText)

            views.setOnClickPendingIntent(R.id.ristretto_button,
                    getPendingIntent(context, CoffeeTypes.RISTRETTO.grams))
            views.setOnClickPendingIntent(R.id.espresso_button,
                    getPendingIntent(context, CoffeeTypes.ESPRESSO.grams))
            views.setOnClickPendingIntent(R.id.long_button,
                    getPendingIntent(context, CoffeeTypes.LONG.grams))
            views.setTextViewText(R.id.coffee_quote, getRandomQuote(context))

            // 1. First, get the limit saved by the user for that widget.
            val limit = coffeeLoggerPersistence.getLimitPref(appWidgetId)
            // 2. Decide if the user exceeds the limit of coffee and establish one of the two possible backgrounds: pink or blue.
            val background = if (limit <= widgetText.toInt()) R.drawable.background_overlimit
            else R.drawable.background
            // 3. Set the background to the widget's root element.
            views.setInt(R.id.widget_layout, "setBackgroundResource", background)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        // This Kotlin function has the responsibility of creating a PendingIntent
        private fun getPendingIntent (context: Context, value: Int): PendingIntent {
            //1.First you define the Intent to launch as usual using the destination class as argument; in your case it’s the MainActivity class.
            val intent = Intent(context, MainActivity::class.java)
            //2,The MainActivity can be launched in different ways, and you need something that identifies how much to vary the coffee content.
            //  To do this you use an action MainActivity can recognise.
            intent.action = Constants.ADD_COFFEE_INTENT
            //3.You also need to put into the Intent the quantity to add. Remember, MainActivity doesn’t know what button was pressed on the Widget!
            intent.putExtra(Constants.GRAMS_EXTRA, value)
            //4,Create the PendingIntent and return it to the caller of the function
            return PendingIntent.getActivity(context, value, intent, 0)
        }

        private fun getRandomQuote(context: Context): String {
            //1.It takes a quote array from the strings file
            val quotes = context.resources.getStringArray(R.array.coffee_texts)
            //2.It picks a random number
            val rand = Math.random() * quotes.size
            //3.Finally, it returns the string at the random position
            return quotes[rand.toInt()].toString()
        }
    }

}


package com.example.noteapp

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStreamWriter
import java.security.AccessControlContext

object FileHelper {

    private val TAG = FileHelper::class.java.name

    /**
     * Method yang digunakan untuk menuliskan data berupa string menjadi file
     *
     * @param fileModel get data file model
     * @param context  context aplikasi
     */
    fun writeToFile (fileModel: FileModel, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput(fileModel.fileName.toString(), Context.MODE_PRIVATE))
            outputStreamWriter.write(fileModel.data.toString())
            outputStreamWriter.close()
        }catch (e: IOException) {
            Log.e(TAG, "File Write Failed" , e)
        }
    }

    /**
     * Method yang digunakan untuk membaca data dari file
     *
     * @param context  context aplikasi
     * @param filename nama file
     * @return data berupa string
     */
    fun readFromFile (context: Context, fileName: String): FileModel {
        val fileModel = FileModel()
        try {
            val inputStream = context.openFileInput(fileName)
            if (inputStream != null) {
                val receiverString = inputStream.bufferedReader().use(BufferedReader::readText)
                inputStream.close()
                fileModel.data = receiverString
                fileModel.fileName = fileName
            }
        }catch (e: FileNotFoundException) {
            Log.e(TAG, "File Not Found", e)
        }catch (e: IOException) {
            Log.e(TAG, "Cannot Read File", e )
        }
        return fileModel
    }

}
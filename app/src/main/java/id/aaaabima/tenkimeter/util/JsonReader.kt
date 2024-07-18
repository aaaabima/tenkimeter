package id.aaaabima.tenkimeter.util

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @author Abim (aaaabim@gmail.com)
 * @version JsonReader, v 0.1 7/17/2024 8:48 PM by Abim
 */
fun readJsonFromAssets(context: Context, path: String): String {
    try {
        val file = context.assets.open(path)
        val bufferedReader = BufferedReader(InputStreamReader(file))
        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines ->
            lines.forEach { stringBuilder.append(it) }
        }
        val jsonString = stringBuilder.toString()
        return jsonString
    }
    catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}
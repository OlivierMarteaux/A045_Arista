package com.openclassrooms.arista.utils

import android.content.Context
import android.widget.Toast
import com.openclassrooms.arista.data.AristaDatabase
import java.io.File

fun exportRoomDatabase(context: Context) {
    val dbName = "arista.db"
    val db = context.getDatabasePath(dbName)

    val exportedFile = File(context.getExternalFilesDir(null), "exported_arista.db")

    // Close Room DB first to flush all writes (important)
    AristaDatabase.getInstance(context).close()

    // Copy the file
    db.copyTo(exportedFile, overwrite = true)

    Toast.makeText(context, "DB exported to: ${exportedFile.absolutePath}", Toast.LENGTH_LONG).show()
}
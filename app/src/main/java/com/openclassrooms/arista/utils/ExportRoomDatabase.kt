package com.openclassrooms.arista.utils

import android.content.Context
import android.widget.Toast
import com.openclassrooms.arista.data.AristaDatabase
import java.io.File

/**
 * Exports the Room database file to the external files directory of the app.
 *
 * This function closes the database instance to ensure all pending writes are flushed,
 * then copies the database file to a new location accessible to the user.
 * Finally, it shows a Toast message with the export file path.
 *
 * @param context The application context used to access the database and file system.
 */
//fun exportRoomDatabase(context: Context) {
//    val dbName = "arista.db"
//    val db = context.getDatabasePath(dbName)
//
//    val exportedFile = File(context.getExternalFilesDir(null), "exported_arista.db")
//
//    // Close Room DB first to flush all writes (important)
//    AristaDatabase.getInstance(context).close()
//
//    // Copy the file
//    db.copyTo(exportedFile, overwrite = true)
//
//    Toast.makeText(context, "DB exported to: ${exportedFile.absolutePath}", Toast.LENGTH_LONG).show()
//}
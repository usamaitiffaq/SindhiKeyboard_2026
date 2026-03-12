package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.dbClasses

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.DictionaryObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


@SuppressLint("StaticFieldLeak")
object DataBaseCopyOperationsKt : SQLiteOpenHelper(null, "dictionary_74.db", null, 1) {

    private lateinit var myContext: Context

    private const val DATABASE_NAME = "dictionary_74.db"
    private const val DATABASE_VERSION = 1

    private val databasePath: String
        get() = myContext.getDatabasePath(DATABASE_NAME).path

    // Fast-path boolean to prevent checking the file system multiple times
    @Volatile
    private var isDatabaseCopied = false

    fun init(context: Context) {
        if (!::myContext.isInitialized) {
            myContext = context.applicationContext
        } else {
            Log.w("DataBaseCopyOperations", "Context is already initialized.")
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Not needed here since we’re copying the pre-built DB
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
            Log.e("DataBaseCopyOperations", "DB:: Database version higher than old.")
            dbDelete()
        }
    }

    // Synchronize ONLY the creation part so threads don't copy the file twice
    @Synchronized
    fun createDatabaseIfNotExists(myContext: Context) {
        if (isDatabaseCopied) return

        val dbFile = File(databasePath)
        if (!dbFile.exists()) {
            try {
                dbFile.parentFile?.let {
                    if (!it.exists()) {
                        it.mkdirs()
                    }
                }
                // Copy database from assets
                myContext.assets.open(DATABASE_NAME).use { inputStream ->
                    FileOutputStream(dbFile).use { outputStream ->
                        val buffer = ByteArray(1024)
                        var length: Int
                        while (inputStream.read(buffer).also { length = it } > 0) {
                            outputStream.write(buffer, 0, length)
                        }
                        outputStream.flush()
                    }
                }
                Log.d("DatabaseCopy", "Database copied successfully.")
                isDatabaseCopied = true
            } catch (e: Exception) {
                Log.e("DatabaseCopy", "Error copying database: $databasePath", e)
            }
        } else {
            isDatabaseCopied = true
            Log.d("DatabaseCopy", "Database already exists.")
        }
    }

    private fun checkDataBase(): Boolean {
        val dbFile = File(databasePath)
        return dbFile.exists() && dbFile.length() > 0
    }

    @Synchronized
    private fun dbDelete() {
        File(databasePath).takeIf { it.exists() }?.delete()
    }

    // THE ANR FIX: No more synchronized(this) block wrapping the entire operation
    private inline fun <T> executeWithDatabase(action: (SQLiteDatabase) -> T): T {
        ensureInitialized()

        if (!isDatabaseCopied) {
            createDatabaseIfNotExists(myContext)
            if (!checkDataBase()) {
                throw IllegalStateException("Database does not exist after copy attempt.")
            }
        }

        // Open without global synchronization so Main Thread and Background Thread can run concurrently
        SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE).use { db ->
            return action(db)
        }
    }

    private fun ensureInitialized() {
        if (!::myContext.isInitialized) {
            throw IllegalStateException("Context not initialized. Call init(context) first.")
        }
    }

    fun getInAppPurchases(): Int {
        return try {
            executeWithDatabase { db ->
                var value = 0
                val query = "SELECT IN_APP_PURCHASES FROM common WHERE indexing = 1"

                db.rawQuery(query, null).use { cursor ->
                    if (cursor != null && cursor.moveToFirst()) {
                        val columnIndex = cursor.getColumnIndex("IN_APP_PURCHASES")
                        if (columnIndex != -1) {
                            value = cursor.getInt(columnIndex)
                        } else {
                            Log.w("DataBaseCopyOperations", "Column IN_APP_PURCHASES not found")
                        }
                    } else {
                        Log.w("DataBaseCopyOperations", "No data found for indexing = 1")
                    }
                }
                value
            }
        } catch (e: SQLiteException) {
            Log.e("DataBaseCopyOperations", "SQL error getting in-app purchases: ${e.message}")
            0
        } catch (e: IllegalStateException) {
            Log.e("DataBaseCopyOperations", "Database not initialized: ${e.message}")
            0
        } catch (e: Exception) {
            Log.e("DataBaseCopyOperations", "Unexpected error getting in-app purchases: ${e.message}")
            0
        }
    }

    fun updateInAppPurchases(newValue: Int) {
        executeWithDatabase { db ->
            val contentValues = ContentValues().apply {
                put("IN_APP_PURCHASES", newValue)
            }
            db.update("common", contentValues, "indexing = ?", arrayOf("1"))
        }
    }

    fun getRemoteConfigVisibility(): Int {
        return executeWithDatabase { db ->
            var value = 3
            db.rawQuery("SELECT KeyPadAdVisibility FROM common WHERE indexing = 1", null).use { cursor ->
                if (cursor.moveToFirst()) {
                    value = cursor.getInt(cursor.getColumnIndexOrThrow("KeyPadAdVisibility"))
                }
            }
            value
        }
    }

    fun updateRemoteConfigVisibility(newValue: Int) {
        executeWithDatabase { db ->
            val contentValues = ContentValues().apply {
                put("KeyPadAdVisibility", newValue)
            }
            db.update("common", contentValues, "indexing = ?", arrayOf("1"))
        }
    }

    fun getRemoteConfigAdmob(): Int {
        return executeWithDatabase { db ->
            var value = 3
            db.rawQuery("SELECT RemoteConfigAdmob FROM common WHERE indexing = 1", null).use { cursor ->
                if (cursor.moveToFirst()) {
                    value = cursor.getInt(cursor.getColumnIndexOrThrow("RemoteConfigAdmob"))
                }
            }
            value
        }
    }

    fun updateRemoteConfigAdmob(newValue: Int) {
        executeWithDatabase { db ->
            val contentValues = ContentValues().apply {
                put("RemoteConfigAdmob", newValue)
            }
            db.update("common", contentValues, "indexing = ?", arrayOf("1"))
        }
    }

    fun getKeyPadBannerFailedShowTimeAdmob(): String {
        return executeWithDatabase { db ->
            var value = "0L"
            db.rawQuery("SELECT KeyPadBannerFailedShowTimeAdmob FROM common WHERE indexing = 1", null).use { cursor ->
                if (cursor.moveToFirst()) {
                    value = cursor.getString(cursor.getColumnIndexOrThrow("KeyPadBannerFailedShowTimeAdmob"))
                }
            }
            value
        }
    }

    fun updateKeyPadBannerFailedShowTimeAdmob(newValue: String) {
        executeWithDatabase { db ->
            val contentValues = ContentValues().apply {
                put("KeyPadBannerFailedShowTimeAdmob", newValue)
            }
            db.update("common", contentValues, "indexing = ?", arrayOf("1"))
        }
    }

    fun getRemoteConfigMintegral(): Int {
        return executeWithDatabase { db ->
            var value = 3
            db.rawQuery("SELECT RemoteConfigMintegral FROM common WHERE indexing = 1", null).use { cursor ->
                if (cursor.moveToFirst()) {
                    value = cursor.getInt(cursor.getColumnIndexOrThrow("RemoteConfigMintegral"))
                }
            }
            value
        }
    }

    fun updateRemoteConfigMintegral(newValue: Int) {
        executeWithDatabase { db ->
            val contentValues = ContentValues().apply {
                put("RemoteConfigMintegral", newValue)
            }
            db.update("common", contentValues, "indexing = ?", arrayOf("1"))
        }
    }

    fun getKeyPadBannerFailedShowTimeMintegral(): String {
        return executeWithDatabase { db ->
            var value = "0L"
            db.rawQuery("SELECT KeyPadBannerFailedShowTimeMintegral FROM common WHERE indexing = 1", null).use { cursor ->
                if (cursor.moveToFirst()) {
                    value = cursor.getString(cursor.getColumnIndexOrThrow("KeyPadBannerFailedShowTimeMintegral"))
                }
            }
            value
        }
    }

    fun updateKeyPadBannerFailedShowTimeMintegral(newValue: String) {
        executeWithDatabase { db ->
            val contentValues = ContentValues().apply {
                put("KeyPadBannerFailedShowTimeMintegral", newValue)
            }
            db.update("common", contentValues, "indexing = ?", arrayOf("1"))
        }
    }

    suspend fun getAllItems(): List<SuggestionItems> = withContext(Dispatchers.IO) {
        executeWithDatabase { db ->
            val suggestionList = mutableListOf<SuggestionItems>()
            db.rawQuery("SELECT * FROM SuggestionList", null).use { cursor ->
                if (cursor.moveToFirst()) {

                    // PERFORMANCE FIX: Find column indices ONCE outside the loop
                    val idIndex = cursor.getColumnIndexOrThrow("id")
                    val engIndex = cursor.getColumnIndexOrThrow("EngRomanWordsSuggestion")
                    val urduIndex = cursor.getColumnIndexOrThrow("UrduWordsSuggestion")
                    val normIndex = cursor.getColumnIndexOrThrow("NormalSuggestion")
                    val dailyIndex = cursor.getColumnIndexOrThrow("DailyUseWords")

                    do {
                        val suggestionItem = SuggestionItems(
                            id = cursor.getInt(idIndex),
                            engRomanWordsSuggestion = cursor.getString(engIndex),
                            urduWordsSuggestion = cursor.getString(urduIndex),
                            normalSuggestion = cursor.getString(normIndex),
                            dailyUseWords = cursor.getString(dailyIndex)
                        )
                        suggestionList.add(suggestionItem)
                    } while (cursor.moveToNext())
                }
            }
            suggestionList
        }
    }

    fun initializeAndCopy(context: Context) {
        init(context)
        createDatabaseIfNotExists(myContext)
    }
}



package com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.editorUtils

import android.os.AsyncTask
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.zeroturnaround.zip.ZipUtil
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL
import java.net.URLConnection

class DownloadFile(
    private val destinationFolder: String,
    private val folderName: String,
    private val callbackProgress: (String) -> Unit,
    private val callback: (Boolean) -> Unit
) : AsyncTask<String, Int, String>() {

    var myProgress = "Progress 0 %"

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg f_url: String): String {
        try {
            val url = URL(f_url[0])
            val connection: URLConnection = url.openConnection()
            connection.connect()

            val lengthOfFile: Int = connection.contentLength
            Log.e("DownloadWork", "File size: $lengthOfFile bytes")

            val input: InputStream = BufferedInputStream(url.openStream(), 8192)
            val directory = File(destinationFolder)

            if (!directory.exists()) {
                directory.mkdirs()
            }

            val localFile = File(directory, "$folderName.zip")
            if (localFile.exists()) {
                localFile.delete()
            }
            localFile.createNewFile()

            val output: OutputStream = FileOutputStream(localFile)
            val data = ByteArray(1024)
            var total: Long = 0
            var count: Int

            while (input.read(data).also { count = it } != -1) {
                total += count.toLong()
                publishProgress((total * 100 / lengthOfFile).toInt())
                output.write(data, 0, count)
            }

            output.flush()
            output.close()
            input.close()

            return "Downloaded at: $localFile"
        } catch (e: Exception) {
            Log.e("DownloadWork", "Error during download: ${e.message}")
            e.printStackTrace()
            return "Download failed"
        }
    }

    override fun onProgressUpdate(vararg progress: Int?) {
        progress[0]?.let {
            myProgress = "Progress $it %"
            callbackProgress(myProgress)
        }
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        Log.e("DownloadWork", "result: "+result)

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val zipFile = File(destinationFolder, "$folderName.zip")
                    if (zipFile.exists()) {
                        ZipUtil.unpack(zipFile, File(destinationFolder))
                        zipFile.delete()
                        callback(true)
                    } else {
                        callback(false)
                    }
                } catch (e: Exception) {
                    Log.e("DownloadWork", "Error unzipping: ${e.message}")
                    callback(false)
                }
            }
        }
    }
}
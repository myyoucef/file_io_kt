package exa.ycf.file_io_kt

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var edt: EditText
    var FileName = "sortie.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt = findViewById(R.id.edi)
        val bsave = findViewById<Button>(R.id.button1)
        val bload = findViewById<Button>(R.id.button2)

        bsave.setOnClickListener {
            var fos: FileOutputStream? = null
            val text = edt.text.toString()
            try {
                fos = openFileOutput(FileName, MODE_PRIVATE)
                fos.write(text.toByteArray())
                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                edt.text.clear()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (fos != null) {
                    try {
                        fos.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        bload.setOnClickListener {
            var fis: FileInputStream? = null
            try {
                fis = openFileInput(FileName)
                var v: Int
                while (fis.read().also { v = it } != -1) {
                    edt.text.append(v.toChar())
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (fis != null) {
                    try {
                        fis.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

}
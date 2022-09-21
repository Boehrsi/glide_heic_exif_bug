package de.boehrsi.glidebug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import de.boehrsi.glidebug.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val url = "url"
        val result = runBlocking(Dispatchers.IO) {
            val file = Glide.with(this@MainActivity).downloadOnly().load(url).submit()
            file.get()
        }
        firstImage(url)
        secondImage(result)
        thirdImage(result.absolutePath)
    }

    // rotation is missing
    private fun firstImage(url: String) {
        Glide
            .with(this)
            .load(url)
            .into(binding.imageFirst);
    }

    // rotation is missing
    private fun secondImage(file: File) {
        Glide
            .with(this)
            .load(file)
            .into(binding.imageSecond);
    }

    // rotation is correctly displayed
    private fun thirdImage(path: String) {
        Glide
            .with(this)
            .load(path)
            .into(binding.imageThird);
    }
}
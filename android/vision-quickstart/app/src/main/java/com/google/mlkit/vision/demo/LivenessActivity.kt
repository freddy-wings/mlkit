package com.google.mlkit.vision.demo

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.privy.livenessfirebasesdk.LivenessApp
import id.privy.livenessfirebasesdk.entity.LivenessItem
import id.privy.livenessfirebasesdk.listener.PrivyCameraLivenessCallBackListener

class LivenessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liveness)

        val livenessApp = LivenessApp.Builder(this)
                .setDebugMode(false)
                .setMotionInstruction("Lihat ke kiri", "Lihat ke kanan")
                .setSuccessText("Berhasil! Silahkan lihat ke kamera lagi untuk mengambil foto")
                .setInstructions("Lihat ke kamera dan tempatkan wajah pada lingakaran hijau")
                .build()

            livenessApp.start(object : PrivyCameraLivenessCallBackListener {
                override fun success(livenessItem: LivenessItem?) {
                    if (livenessItem != null) {
                        val intent = Intent(this@LivenessActivity, StillImageActivity::class.java)
                        intent.putExtra("ImageLiveness", livenessItem.imageBitmap)
                        startActivity(intent)
                       // test_image.setImageBitmap(livenessItem.imageBitmap)
                    }
                }
                override fun failed(t: Throwable?) {
                }
            })

    }


}
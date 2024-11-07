package net.streamroutes.sreamroutesapp.utils

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

object QrUtils {
    fun generateQRCode(text: String): Bitmap {
        val matrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, 512, 512)

        val bitmap = Bitmap.createBitmap(matrix.width, matrix.height, Bitmap.Config.RGB_565)

        for (y in 0 until matrix.height) {
            for (x in 0 until matrix.width) {
                bitmap.setPixel(x, y,
                    if(matrix.get(x, y))
                        android.graphics.Color.BLACK
                    else
                        android.graphics.Color.WHITE
                )
            }
        }

        return bitmap
    }
}
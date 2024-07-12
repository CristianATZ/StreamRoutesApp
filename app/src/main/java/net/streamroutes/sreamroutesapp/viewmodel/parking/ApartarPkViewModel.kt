package net.streamroutes.sreamroutesapp.viewmodel.parking

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.data.model.parkinModel.ParkingResultItem
import net.streamroutes.sreamroutesapp.data.repository.RemoteRepository

class ApartarPkViewModel(
    private val remoteRepository: RemoteRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ApartarUiState())
    val uiState : StateFlow<ApartarUiState> = _uiState.asStateFlow()

    fun updateInfo(v: Vehiculo, e: ParkingResultItem, hi: Int, mi: Int, total: Int){
        _uiState.value = _uiState.value.copy(
            vehiculo = v,
            estacionamiento = e,
            horaInicio = hi,
            minutoInicio = mi+3,
            horaFinal = hi+1,
            total = total
        )
    }

    fun updateTiempos(h: Int){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                horaFinal = _uiState.value.horaInicio+h,
                total = (h*_uiState.value.estacionamiento!!.price)
            )
        }
    }

    fun udpateQrCode(mapa: Bitmap){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                qrCode = mapa
            )
        }
    }

    fun generateQRCode(text: String){
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

        udpateQrCode(bitmap)
    }
}

data class ApartarUiState(
    val vehiculo: Vehiculo? = null,
    val estacionamiento: ParkingResultItem? = null,
    val qrCode: Bitmap? = null,
    val horaInicio: Int = 0,
    val minutoInicio: Int = 0,
    val horaFinal: Int = 0,
    val total: Int = 0
)

@Suppress("UNCHECKED_CAST")
class ApartarPkViewModelFactory(
    private val remoteRepository: RemoteRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApartarPkViewModel::class.java)) {
            return ApartarPkViewModel(remoteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
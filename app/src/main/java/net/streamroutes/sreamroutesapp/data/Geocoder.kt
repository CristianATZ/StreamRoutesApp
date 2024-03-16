package net.streamroutes.sreamroutesapp.data

import android.content.Context
import android.location.Geocoder

data class AddressInfo(
    val cityName: String?, // ciudad
    val streetName: String?, // calle
    val neighborhood: String?, // colonia
    val postalCode: String? // codigo
)

fun getAddressInfoFromCoordinates(context: Context, latitude: Double, longitude: Double): AddressInfo? {
    val geocoder = Geocoder(context)
    val addressList = geocoder.getFromLocation(latitude, longitude, 1)
    val address = addressList?.get(0)
    if (address != null) {
        return AddressInfo(
            cityName = address.locality,
            streetName = address.thoroughfare,
            neighborhood = address.subLocality,
            postalCode = address.postalCode
        )
    }
    return null
}
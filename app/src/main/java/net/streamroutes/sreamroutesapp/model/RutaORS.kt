package net.streamroutes.sreamroutesapp.model
import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("coordinates") val coordinates: List<List<Double>>
)

data class Feature(
    @SerializedName("geometry") val geometry: Geometry
)

data class Ruta(
    @SerializedName("features") val features: List<Feature>
)
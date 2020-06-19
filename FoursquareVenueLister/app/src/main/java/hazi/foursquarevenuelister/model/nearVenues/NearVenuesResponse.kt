package hazi.foursquarevenuelister.model.nearVenues

data class NearVenuesResponse(
    val meta: Meta,
    val response: Response
)

data class Category(
    val icon: Icon,
    val id: String,
    val name: String,
    val pluralName: String,
    val primary: Boolean,
    val shortName: String
)

data class Icon(
    val prefix: String,
    val suffix: String
)

data class LabeledLatLng(
    val label: String,
    val lat: Double,
    val lng: Double
)

data class Location(
    val address: String,
    val cc: String,
    val city: String,
    val country: String,
    val crossStreet: String,
    val distance: Int,
    val formattedAddress: List<String>,
    val labeledLatLngs: List<LabeledLatLng>,
    val lat: Double,
    val lng: Double,
    val postalCode: String,
    val state: String
)

data class Meta(
    val code: Int,
    val requestId: String
)

data class Response(
    val venues: List<Venue>
)

data class Venue(
    val categories: List<Category>,
    val id: String,
    val location: Location,
    val name: String,
    val venuePage: VenuePage
)

data class VenuePage(
    val id: String
)
package hazi.foursquarevenuelister.model.venuePhotos

data class VenuePhotos(
    val meta: Meta,
    val response: Response
)

data class Checkin(
    val createdAt: Int,
    val id: String,
    val timeZoneOffset: Int,
    val type: String
)

data class Item(
    val checkin: Checkin,
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val source: Source,
    val suffix: String,
    val user: User,
    val visibility: String,
    val width: Int
)

data class Meta(
    val code: Int,
    val requestId: String
)

data class Photo(
    val prefix: String,
    val suffix: String
)

data class Photos(
    val count: Int,
    val dupesRemoved: Int,
    val items: List<Item>
)

data class Response(
    val photos: Photos
)

data class Source(
    val name: String,
    val url: String
)

data class User(
    val firstName: String,
    val id: String,
    val lastName: String,
    val photo: Photo
)
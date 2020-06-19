package hazi.foursquarevenuelister.model

data class Attributes(
    val groups: List<Group>
)

data class BeenHere(
    val count: Int,
    val lastCheckinExpiredAt: Int,
    val marked: Boolean,
    val unconfirmedCount: Int
)


data class BestPhoto(
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val source: Source,
    val suffix: String,
    val visibility: String,
    val width: Int
)

data class Category(
    val icon: Icon,
    val id: String,
    val name: String,
    val pluralName: String,
    val primary: Boolean,
    val shortName: String
)

data class Contact(
    val facebook: String,
    val facebookName: String,
    val facebookUsername: String,
    val formattedPhone: String,
    val instagram: String,
    val phone: String,
    val twitter: String
)

data class DetailsResponse(
    val meta: Meta,
    val response: Response
)

data class Entity(
    val indices: List<Int>,
    val type: String
)

data class Followers(
    val count: Int
)

data class Group(
    val count: Int,
    val items: List<Item>,
    val name: String,
    val summary: String,
    val type: String
)

data class GroupX(
    val count: Int,
    val items: List<Any>,
    val name: String,
    val type: String
)

data class GroupXX(
    val count: Int,
    val items: List<ItemX>,
    val name: String,
    val type: String
)

data class GroupXXX(
    val count: Int,
    val items: List<Any>,
    val type: String
)

data class GroupXXXX(
    val count: Int,
    val items: List<ItemXXXX>,
    val name: String,
    val type: String
)

data class GroupXXXXX(
    val count: Int,
    val items: List<ItemXXXXX>,
    val name: String,
    val type: String
)

data class GroupXXXXXX(
    val count: Int,
    val items: List<Any>,
    val type: String
)

data class HereNow(
    val count: Int,
    val groups: List<GroupX>,
    val summary: String
)

data class Hours(
    val isLocalHoliday: Boolean,
    val isOpen: Boolean,
    val status: String,
    val timeframes: List<Timeframe>
)

data class Icon(
    val prefix: String,
    val suffix: String
)

data class Inbox(
    val count: Int,
    val items: List<Any>
)

data class Item(
    val displayName: String,
    val displayValue: String
)

data class ItemX(
    val canonicalUrl: String,
    val collaborative: Boolean,
    val createdAt: Int,
    val description: String,
    val editable: Boolean,
    val followers: Followers,
    val id: String,
    val listItems: ListItems,
    val name: String,
    val photo: PhotoX,
    val `public`: Boolean,
    val type: String,
    val updatedAt: Int,
    val url: String,
    val user: UserX
)

data class ItemXX(
    val createdAt: Int,
    val id: String,
    val photo: Photo
)

data class ItemXXX(
    val url: String
)
data class ItemXXXX(
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val source: SourceX,
    val suffix: String,
    val user: UserXXX,
    val visibility: String,
    val width: Int
)

data class ItemXXXXX(
    val agreeCount: Int,
    val authorInteractionType: String,
    val canonicalUrl: String,
    val createdAt: Int,
    val disagreeCount: Int,
    val editedAt: Int,
    val id: String,
    val lang: String,
    val likes: LikesX,
    val logView: Boolean,
    val photo: PhotoXXXXX,
    val photourl: String,
    val text: String,
    val todo: Todo,
    val type: String,
    val url: String,
    val user: UserXXXX
)

data class Likes(
    val count: Int,
    val summary: String
)

data class LikesX(
    val count: Int,
    val groups: List<GroupXXXXXX>,
    val summary: String
)

data class Links(
    val count: Int,
    val items: List<ItemXXX>
)

data class Listed(
    val count: Int,
    val groups: List<GroupXX>
)

data class ListItems(
    val count: Int,
    val items: List<ItemXX>
)

data class Lists(
    val groups: List<GroupXXX>
)

data class Location(
    val address: String,
    val cc: String,
    val city: String,
    val country: String,
    val crossStreet: String,
    val formattedAddress: List<String>,
    val lat: Double,
    val lng: Double,
    val postalCode: String,
    val state: String
)

data class Meta(
    val code: Int,
    val requestId: String
)

data class Open(
    val renderedTime: String
)

data class OpenX(
    val renderedTime: String
)

data class Page(
    val pageInfo: PageInfo,
    val user: UserXX
)

data class PageInfo(
    val banner: String,
    val description: String,
    val links: Links
)

data class PageUpdates(
    val count: Int,
    val items: List<Any>
)

data class Photo(
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val suffix: String,
    val visibility: String,
    val width: Int
)

data class Photos(
    val count: Int,
    val groups: List<GroupXXXX>
)

data class PhotoX(
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val suffix: String,
    val user: User,
    val visibility: String,
    val width: Int
)

data class PhotoXX(
    val prefix: String,
    val suffix: String
)

data class PhotoXXX(
    val prefix: String,
    val suffix: String
)

data class PhotoXXXX(
    val prefix: String,
    val suffix: String
)

data class PhotoXXXXX(
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val source: SourceXX,
    val suffix: String,
    val visibility: String,
    val width: Int
)

data class PhotoXXXXXX(
    val prefix: String,
    val suffix: String
)

data class Phrase(
    val count: Int,
    val phrase: String,
    val sample: Sample
)

data class Popular(
    val isLocalHoliday: Boolean,
    val isOpen: Boolean,
    val status: String,
    val timeframes: List<TimeframeX>
)

data class Response(
    val venue: Venue
)

data class Sample(
    val entities: List<Entity>,
    val text: String
)

data class Source(
    val name: String,
    val url: String
)

data class SourceX(
    val name: String,
    val url: String
)

data class SourceXX(
    val name: String,
    val url: String
)

data class Stats(
    val checkinsCount: Int,
    val tipCount: Int,
    val usersCount: Int,
    val visitsCount: Int
)

data class Timeframe(
    val days: String,
    val includesToday: Boolean,
    val `open`: List<Open>,
    val segments: List<Any>
)

data class TimeframeX(
    val days: String,
    val `open`: List<OpenX>,
    val segments: List<Any>
)

data class Tips(
    val count: Int
)

data class TipsX(
    val count: Int,
    val groups: List<GroupXXXXX>
)

data class Todo(
    val count: Int
)

data class User(
    val firstName: String,
    val id: String,
    val photo: PhotoXX,
    val type: String
)

data class UserX(
    val firstName: String,
    val id: String,
    val lastName: String,
    val photo: PhotoXXX
)

data class UserXX(
    val bio: String,
    val firstName: String,
    val id: String,
    val lists: Lists,
    val photo: PhotoXXXX,
    val tips: Tips,
    val type: String
)

data class UserXXX(
    val firstName: String,
    val id: String,
    val lastName: String
)

data class UserXXXX(
    val firstName: String,
    val id: String,
    val photo: PhotoXXXXXX,
    val type: String
)

data class Venue(
    val attributes: Attributes,
    val beenHere: BeenHere,
    val bestPhoto: BestPhoto,
    val canonicalUrl: String,
    val categories: List<Category>,
    val contact: Contact,
    val createdAt: Int,
    val description: String,
    val hereNow: HereNow,
    val hours: Hours,
    val id: String,
    val inbox: Inbox,
    val likes: Likes,
    val listed: Listed,
    val location: Location,
    val name: String,
    val page: Page,
    val pageUpdates: PageUpdates,
    val photos: Photos,
    val phrases: List<Phrase>,
    val popular: Popular,
    val rating: Double,
    val ratingColor: String,
    val ratingSignals: Int,
    val shortUrl: String,
    val stats: Stats,
    val storeId: String,
    val timeZone: String,
    val tips: TipsX,
    val url: String,
    val venueChains: List<Any>,
    val verified: Boolean
)





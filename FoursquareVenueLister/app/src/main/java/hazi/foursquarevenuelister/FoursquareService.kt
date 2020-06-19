package hazi.foursquarevenuelister

import hazi.foursquarevenuelister.BuildConfig.CLIENT_ID
import hazi.foursquarevenuelister.BuildConfig.CLIENT_SECRET
import hazi.foursquarevenuelister.model.DetailsResponse
import hazi.foursquarevenuelister.model.nearVenues.NearVenuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoursquareService {

    @GET("/v2/venues/search")
    fun searchNearVenues(
        @Query("query") query: String,
        @Query("near") near: String,
        @Query("limit") limit: Int,
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = CLIENT_SECRET,
        @Query("v") version: String = API_CLIENT_VERSION
    ): Call<NearVenuesResponse>

    @GET("/v2/venues/{id}")
    fun detailsOfVenue(
        @Path("id") id: String,
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = CLIENT_SECRET,
        @Query("v") version: String = API_CLIENT_VERSION
    ): Call<DetailsResponse>

    companion object {
        const val API_CLIENT_URL = "https://api.foursquare.com"
        const val API_CLIENT_VERSION = "20180401"
    }
}
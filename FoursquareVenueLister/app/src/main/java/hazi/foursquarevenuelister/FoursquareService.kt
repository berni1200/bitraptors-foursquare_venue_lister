package hazi.foursquarevenuelister

import hazi.foursquarevenuelister.model.DetailsResponse
import hazi.foursquarevenuelister.model.nearVenues.NearVenuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoursquareService {

    @GET("/v2/venues/search")
    fun searchNearVenues(
        @Query("query") query: String = "tacos",
        //@Query("near") near: String, //ha valaminek a közelében akarjuk nézni
        @Query("ll") ll: String, //ha konkrét koordinátákat akarunk
        @Query("limit") limit: Int = 250,
        @Query("client_id") clientId: String = "M5M31S51C0KAHBUIXOQHW3WDJ3OKDLMGL2YMQTARQJLIJYAF",
        @Query("client_secret") clientSecret: String = "ZS1V4T05RHCWXEGGMHNYFFLDKVAW2AZSAFXAV4TBNTGQTYOX",
        @Query("v") version: String = API_CLIENT_VERSION
    ): Call<NearVenuesResponse>

    @GET("/v2/venues/{id}")
    fun detailsOfVenue(
        @Path("id") id: String,
        @Query("client_id") clientId: String = "M5M31S51C0KAHBUIXOQHW3WDJ3OKDLMGL2YMQTARQJLIJYAF",
        @Query("client_secret") clientSecret: String = "ZS1V4T05RHCWXEGGMHNYFFLDKVAW2AZSAFXAV4TBNTGQTYOX",
        @Query("v") version: String = API_CLIENT_VERSION
    ): Call<DetailsResponse>

    companion object {
        const val API_CLIENT_URL = "https://api.foursquare.com"
        const val API_CLIENT_VERSION = "20180401"
    }
}
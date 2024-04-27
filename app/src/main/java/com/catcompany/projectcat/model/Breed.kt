package com.catcompany.projectcat.model

import android.os.Parcelable
import com.catcompany.breedlist.model.CatBreed
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    @SerializedName("weight") var weight: Weight? = Weight(),
    @SerializedName("id") var id: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("cfa_url") var cfaUrl: String?,
    @SerializedName("vetstreet_url") var vetstreetUrl: String?,
    @SerializedName("vcahospitals_url") var vcahospitalsUrl: String?,
    @SerializedName("temperament") var temperament: String?,
    @SerializedName("origin") var origin: String?,
    @SerializedName("country_codes") var countryCodes: String?,
    @SerializedName("country_code") var countryCode: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("life_span") var lifeSpan: String?,
    @SerializedName("indoor") var indoor: Int?,
    @SerializedName("lap") var lap: Int?,
    @SerializedName("alt_names") var altNames: String?,
    @SerializedName("adaptability") var adaptability: Int?,
    @SerializedName("affection_level") var affectionLevel: Int?,
    @SerializedName("child_friendly") var childFriendly: Int?,
    @SerializedName("dog_friendly") var dogFriendly: Int?,
    @SerializedName("energy_level") var energyLevel: Int?,
    @SerializedName("grooming") var grooming: Int?,
    @SerializedName("health_issues") var healthIssues: Int?,
    @SerializedName("intelligence") var intelligence: Int?,
    @SerializedName("shedding_level") var sheddingLevel: Int?,
    @SerializedName("social_needs") var socialNeeds: Int?,
    @SerializedName("stranger_friendly") var strangerFriendly: Int?,
    @SerializedName("vocalisation") var vocalisation: Int?,
    @SerializedName("experimental") var experimental: Int?,
    @SerializedName("hairless") var hairless: Int?,
    @SerializedName("natural") var natural: Int?,
    @SerializedName("rare") var rare: Int?,
    @SerializedName("rex") var rex: Int?,
    @SerializedName("suppressed_tail") var suppressedTail: Int?,
    @SerializedName("short_legs") var shortLegs: Int?,
    @SerializedName("wikipedia_url") var wikipediaUrl: String?,
    @SerializedName("hypoallergenic") var hypoallergenic: Int?,
    @SerializedName("reference_image_id") var referenceImageId: String?,
    @SerializedName("image") var image: Image?
) : Parcelable {
    fun createCatBreed(): CatBreed {
        return CatBreed(id, name, description, image?.url, origin)
    }
}
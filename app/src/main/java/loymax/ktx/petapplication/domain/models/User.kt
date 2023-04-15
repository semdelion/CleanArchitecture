package loymax.ktx.petapplication.domain.models
import kotlinx.serialization.Serializable

@Serializable
data class User(val firstName: String, val lastName: String) {
}
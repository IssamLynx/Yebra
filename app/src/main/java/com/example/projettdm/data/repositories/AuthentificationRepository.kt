import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.projettdm.data.api.ServiceProvider
import com.example.projettdm.data.model.Medecin
import com.example.projettdm.data.model.SubscriptionData
import com.example.projettdm.data.model.authModel
import com.example.projettdm.data.notificationPush.MyFirebaseMessagingService
import com.example.projettdm.data.repositories.Pref
import com.example.projettdm.utils.type
import com.sil1.autolibdz_rental.data.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthentificationRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

fun subscribe(subscriptionData: SubscriptionData){
    val call = api.subscribe(subscriptionData)
    call.enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {

        }

        override fun onFailure(call: Call<String>, t: Throwable) {

        }
    })
}
        fun authentifier(
            TAG: String?,
            locataire: authModel,
            context: Context
        ) {
            var call = api.authentifier(locataire) // fonction de modification dans l'api

            call.enqueue(object : Callback<Medecin> {
                override fun onResponse(
                    call: Call<Medecin>,
                    response: Response<Medecin>
                ) {
                    if (!response.isSuccessful) {
                        Toast.makeText(context, "Erreur d'authentification", Toast.LENGTH_LONG).show()
                        Log.i(TAG, "CODE:" + response.code().toString())

                    } else {
                        var message = response.body()
                        Pref.save(message?.type,message?.id)
                        MyFirebaseMessagingService.getToken(message?.id!!)
                        type.value="changed"

                    }
                }

                override fun onFailure(call: Call<Medecin>, t: Throwable) {
                    Toast.makeText(context, "Erreur d'authentification", Toast.LENGTH_LONG).show()
                    Log.i(TAG, "error CODE:" + t.message)

                }

            })
        }


    }}
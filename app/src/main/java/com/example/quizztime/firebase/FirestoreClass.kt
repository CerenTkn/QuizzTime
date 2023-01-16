import android.util.Log
import com.example.quizztime.MainActivity
import com.example.quizztime.SignUpActivity
import com.example.quizztime.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User

class FirestoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: User){
        mFireStore.collection(Constants.USERS).document(getCurrentUserId()).set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }.addOnFailureListener{
                    e ->
                Log.e(activity.javaClass.simpleName, "Error writing document")
            }
    }

    fun signInUser(activity: MainActivity){
        mFireStore.collection(Constants.USERS).document(getCurrentUserId()).get()
            .addOnSuccessListener { document ->
                val loggedUser = document.toObject(User::class.java)
                if (loggedUser != null)
                    activity.signInSuccess(loggedUser )
            }.addOnFailureListener{
                    e ->
                Log.e("SignInUser", "Error writing document", e)
            }

    }

    fun getCurrentUserId(): String {

        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID

    }
}
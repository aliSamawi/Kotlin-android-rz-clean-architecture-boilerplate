package ir.r3za13.kotlin_rz_boilerplate.data

import com.orhanobut.hawk.Hawk

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountManager @Inject constructor(){

    //fake user class
    data class User(var token: String){

    }

    companion object {
        val USER_TOKEN    = "token"
        val USER_PROFILE  = "user_profile"
        var token: String?=null
    }

    fun saveUserToken(token: String)
    {
        Hawk.put(USER_TOKEN,token)
    }

    fun saveUserProfile(profile: User)
    {
        Hawk.put(USER_PROFILE,profile)
    }

    fun deleteUserToken()
    {
        Hawk.delete(USER_TOKEN)
        Hawk.delete(USER_PROFILE)
        token = null
    }

    fun getToken(): String?
    {
        if( token==null || token.equals("") )
        {
            if(Hawk.contains(USER_TOKEN))
                token = Hawk.get<String>(USER_TOKEN)
            else
                token = ""
        }
        return token
    }

    fun getUser(): User?
    {
//        var user = User("","","","","","")
//        if( token==null || token.equals("") )
//        {
//            if(Hawk.contains(USER_PROFILE))
//                user = Hawk.get<User>(USER_PROFILE)
//
//        }
//        return user
        return User("")
    }
}
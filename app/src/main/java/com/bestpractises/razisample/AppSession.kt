package com.bestpractises.razisample

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.hawk.Hawk
import java.lang.reflect.Type

class AppSession {

    companion object {
        private const val TOKEN = "token"
        private const val PHONE = "phone"
        private const val PHONE_LIST = "phoneList"
        private const val PASS = "pass"

        private const val FIREBASE_CLASS_NAME = "className"
        private const val FIREBASE_CONTENT_ID = "contentId"
        private const val FIREBASE_NOTIFICATION_ID = "notificationID"
        private const val UUID = "uuid"

        private const val PERSON_INFO_COMPLETED = "personInfoCompleted"

        private const val IS_LOGGED_IN = "isLoggedIn"
        private const val IS_ADMIN = "isAdmin"
        private const val IS_DARK_THEME = "darkTheme"
        private const val PERMISSIONS = "permissions"

        private const val IS_NEEDED_SELECT_PERSON = "isNeededSelectPerson"
        private const val IS_OWNER = "isOwner"
        private const val IS_SYSADMIN = "isSysAdmin"
        private const val IS_VIEWER = "isViewer"
        private const val IS_BUYER = "isBuyer"
        private const val IS_SELLER = "isSeller"
        private const val PERSON_ID = "personID"
        private const val USER_ID = "userId"
        private const val PERSON_TITLE = "personTitle"

        private const val CHANGE_ACCOUNT_BY_SHAKE = "byShake"


        /*Getter*/
        fun getToken(): String = Hawk.get(TOKEN, "")
        fun getPhone(): String = Hawk.get(PHONE, "")
        fun getListPhone(): MutableList<String?>? {
            val json = Hawk.get(PHONE_LIST, "")
            val type: Type = object : TypeToken<java.util.ArrayList<String?>?>() {}.type
            val gson = Gson()
            return gson.fromJson(json, type)
        }

        fun getPass(): String = Hawk.get(PASS, "")


        fun getFirebaseClassName(): String = Hawk.get(FIREBASE_CLASS_NAME, "")
        fun getFirebaseContentId(): String = Hawk.get(FIREBASE_CONTENT_ID, "")
        fun getFirebaseNotificationId(): String = Hawk.get(FIREBASE_NOTIFICATION_ID, "")
        fun getUuid(): String = Hawk.get(UUID, java.util.UUID.randomUUID().toString())

        fun isCompletedPersonInfo(): Boolean = Hawk.get(PERSON_INFO_COMPLETED, false)
        fun isLoggedIn(): Boolean = Hawk.get(IS_LOGGED_IN, false)
        fun isAdmin(): Boolean = Hawk.get(IS_ADMIN, false)
        fun getDarkTheme(): String = Hawk.get(IS_DARK_THEME, "")
        fun getPermissions(): List<Int> = Hawk.get(PERMISSIONS)
        fun isNeededSelectPerson(): Boolean = Hawk.get(IS_NEEDED_SELECT_PERSON, true)

        fun isOwner(): Boolean = Hawk.get(IS_OWNER, false)
        fun isSysAdmin(): Boolean = Hawk.get(IS_SYSADMIN, false)
        fun isViewer(): Boolean = Hawk.get(IS_VIEWER, true)
        fun isBuyer(): Boolean = Hawk.get(IS_BUYER, false)
        fun isSeller(): Boolean = Hawk.get(IS_SELLER, false)
        fun getPersonId(): Int = Hawk.get(PERSON_ID, -1)
        fun getUserId(): Int = Hawk.get(USER_ID, -1)
        fun getPersonTitle(): String = Hawk.get(PERSON_TITLE, "")

        fun doesChangeAccountByShake(): Boolean = Hawk.get(CHANGE_ACCOUNT_BY_SHAKE, true)

        /**
         *  @param permissionId new
         * Id: 5, Name: ثبت پیشنهاد
         *
         * "Id": 1,"Name": ثبت تخلیه
         * "Id": 2,"Name": ثبت گزارش کیفی
         * "Id": 3,"Name": دسترسی به کیف پول
         * "Id": 4,"Name": ثبت  پیشنهاد

         * @return has Access certain permission
         */

        fun hasAccess(permissionId: Int): Boolean {
            return (getPermissions().find { it == permissionId }) != null
//           return  false
        }

        fun deleteAll() {
            val phoneList = getListPhone()
            val darkTheme = getDarkTheme()
            Hawk.deleteAll()
            phoneList?.let {
                saveListPhone(phone = null, phoneList = it)
            }
            saveDarkTheme(darkTheme)

        }


        /*Setter*/
        fun saveToken(token: String?): Boolean {
            setLoggedIn(true)
            return Hawk.put(TOKEN, token)
        }

        fun savePhone(phone: String?) = Hawk.put(PHONE, phone)
        fun saveListPhone(phone: String?, phoneList: MutableList<String?>?) {
            val gson = Gson()
            val json = if (phone.isNullOrEmpty()) {
                gson.toJson(phoneList)
            } else {
                val phones = getListPhone()
                if (phones.isNullOrEmpty()) {
                    val newpPhoneList = mutableListOf<String>()
                    newpPhoneList.add(phone)
                    gson.toJson(newpPhoneList)
                } else {
                    phones.add(phone)
                    gson.toJson(phones)
                }

            }
            Hawk.put(PHONE_LIST, json)
        }

        fun savePass(pass: String?) = Hawk.put(PASS, pass)
        fun saveFirebaseClassName(className: String?) = Hawk.put(FIREBASE_CLASS_NAME, className)
        fun saveFirebaseContentId(classId: String?) = Hawk.put(FIREBASE_CONTENT_ID, classId)

        fun saveFirebaseNotificationId(notificationID: String?) =
            Hawk.put(FIREBASE_NOTIFICATION_ID, notificationID)

        fun saveIsCompletedPersonInfo(isCompletedPersonInfo: Boolean?) =
            Hawk.put(PERSON_INFO_COMPLETED, isCompletedPersonInfo)


        fun saveIsAdmin(isAdmin: Boolean?) = Hawk.put(IS_ADMIN, isAdmin)
        fun saveDarkTheme(isDarkTheme: String?) = Hawk.put(IS_DARK_THEME, isDarkTheme)
        fun savePermissions(permissionList: List<Int>) = Hawk.put(PERMISSIONS, permissionList)


        fun setLoggedIn(isLoggedIn: Boolean?) = Hawk.put(IS_LOGGED_IN, isLoggedIn)

        fun saveUuid() {
            val a = java.util.UUID.randomUUID().toString()
            Hawk.put(UUID, a)
        }


        fun saveIsNeededSelectPerson(isNeededSelectPerson: Boolean?) = Hawk.put(
            IS_NEEDED_SELECT_PERSON, isNeededSelectPerson
        )

        fun saveIsOwner(isOwner: Boolean?) = Hawk.put(IS_OWNER, isOwner)
        fun saveIsSysAdmin(isSysAdmin: Boolean?) = Hawk.put(IS_SYSADMIN, isSysAdmin)
        fun saveIsViewer(isViewer: Boolean?) = Hawk.put(IS_VIEWER, isViewer)
        fun saveIsBuyer(isBuyer: Boolean?) = Hawk.put(IS_BUYER, isBuyer)
        fun saveIsSeller(isSeller: Boolean?) = Hawk.put(IS_SELLER, isSeller)
        fun savePersonID(personID: Int?) = Hawk.put(PERSON_ID, personID)
        fun saveUserID(userId: Int) = Hawk.put(USER_ID, userId)
        fun savePersonTitle(personTitle: String) = Hawk.put(PERSON_TITLE, personTitle)

        fun saveDoesChangeAccountByShake(doesChange: Boolean?) = Hawk.put(CHANGE_ACCOUNT_BY_SHAKE, doesChange)

    }


}
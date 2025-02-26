package com.example.lpg.android.util

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

const val prefs = "lpg_app_prefs"
const val dbKey = "db_key"

object SecurePrefs {

    fun getPassphrase(context: Context): ByteArray {

        val masterKeyAlias = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val sharedPreferences = EncryptedSharedPreferences.create(
            context,
            prefs,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        // Check if passphrase is already stored
        var passphrase = sharedPreferences.getString(dbKey, null)
        if (passphrase == null) {
            passphrase = generatePassphrase()
            sharedPreferences.edit().putString(dbKey, passphrase).apply()
        }

        return net.sqlcipher.database.SQLiteDatabase.getBytes(passphrase.toCharArray())
    }

    // Generate a random passphrase securely
    private fun generatePassphrase(): String {
        return java.util.UUID.randomUUID().toString()
    }

}
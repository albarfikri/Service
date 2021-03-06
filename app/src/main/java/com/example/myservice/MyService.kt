package com.example.myservice

import android.app.Service
import android.content.Intent
import android.nfc.Tag
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import java.lang.UnsupportedOperationException

class MyService : Service() {

    companion object{
        internal val TAG = MyService::class.java.simpleName
    }

    //mematikan/menghentikan service
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "service dijalankan..")
        serviceScope.launch{
            delay(3000)
            // berfungsi untuk memberhentikan atau mematikan MyService
            stopSelf()
            Log.d(TAG, "Service dihentikan")
        }
        //start_sticky maksudnya bila service tersebut dimatikan karena kekurangan memori. maka akan diciptakan kembali
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d(TAG,"onDestroy: ")
    }
}
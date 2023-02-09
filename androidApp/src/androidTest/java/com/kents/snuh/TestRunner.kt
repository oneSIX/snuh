package com.kents.snuh

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import com.kents.core.commons.Log.addLogger
import com.kents.snuh.utils.AndroidLogger

class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        addLogger(AndroidLogger())
        return super.newApplication(cl, HiltTestApplication::class.java.canonicalName, context)
    }
}

package com.example.a712assignment2

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Before
import org.junit.Assert.assertTrue

private const val LAUNCH_TIMEOUT = 5000L
private const val PACKAGE_NAME = "com.example.a712assignment2"

@RunWith(AndroidJUnit4::class)
class UiAutomatorTest {

    private lateinit var device: UiDevice

    @Before
    fun startFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        device.pressHome()

        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT)

        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear out any previous instances
        }
        context.startActivity(intent)

        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT)
    }

    @Test
    fun testLaunchSecondActivityAndVerifyChallenge() {
        // Click the "Start Activity Explicitly" button
        device.wait(Until.hasObject(By.text("Start Activity Explicitly")), 5000)
        val button = device.findObject(By.text("Start Activity Explicitly"))
        button.click()

        // Wait for second activity to appear
        device.wait(Until.hasObject(By.textContains("Mobile Software Engineering Challenges")), 3000)

        val challengeText = device.findObject(By.textContains("Providing accessibility to multiple platforms"))
        assertTrue("Challenge text not found", challengeText != null)
    }
}
package com.samir.ecommerceapp.chat

import android.content.Context
import android.content.Intent
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

object ChatModuleLauncher {

    private const val CHAT_MODULE_NAME = "chat"

    fun launchChat(context: Context, onError: (String) -> Unit = {}) {
        val splitInstallManager = SplitInstallManagerFactory.create(context)

        if (splitInstallManager.installedModules.contains(CHAT_MODULE_NAME)) {
            openChatActivity(context)
            return
        }

        val request = SplitInstallRequest.newBuilder()
            .addModule(CHAT_MODULE_NAME)
            .build()

        val listener = SplitInstallStateUpdatedListener { state ->
            when (state.status()) {
                SplitInstallSessionStatus.INSTALLED -> {
                    openChatActivity(context)
                }

                SplitInstallSessionStatus.FAILED -> onError("Chat module failed to install")
                else -> {}
            }
        }

        splitInstallManager.registerListener(listener)
        splitInstallManager.startInstall(request)
    }

    private fun openChatActivity(context: Context) {
        val intent = Intent().setClassName(context.packageName, "com.samir.chat.ChatActivity")
        context.startActivity(intent)
    }
}
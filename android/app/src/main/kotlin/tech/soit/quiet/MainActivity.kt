package tech.soit.quiet

import android.content.Intent
import android.os.Bundle
import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant
import tech.soit.quiet.service.NeteaseCrypto
import tech.soit.quiet.service.QuietPlayerChannel

class MainActivity : FlutterActivity() {

    companion object {

        /**
         * 网易云音乐加密
         */
        const val CHANNEL_NETEASE_CRYPTO = "tech.soit.netease/crypto"


        const val KEY_DESTINATION = "destination"

        const val DESTINATION_PLAYING_PAGE = "action_playing_page"

    }

    private lateinit var playerChannel: QuietPlayerChannel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)

        NeteaseCrypto.init(flutterView)
        playerChannel = QuietPlayerChannel.registerWith(registrarFor("tech.soit.quiet.service.QuietPlayerChannel"))
    }

    override fun onDestroy() {
        playerChannel.destroy()
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        when (intent.getStringExtra(KEY_DESTINATION)) {
            DESTINATION_PLAYING_PAGE -> {
                flutterView.pushRoute("/playing")
            }
        }
    }

}

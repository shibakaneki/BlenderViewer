package ch.shibastudio.blenderviewer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class MainActivity : AndroidApplication() {

    // NOTE: To display the libgdx part within a view: https://stackoverflow.com/a/10900054/2520326

    val viewer = Viewer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        initialize(viewer, config)
    }
}

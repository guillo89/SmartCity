package es.pamplona.smartcity.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import es.pamplona.smartcity.R
import es.pamplona.smartcity.ui.ToolbarManager
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class Home : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initHomeToolbar()
        toolbarTitle = getString(R.string.toolbar_home_title)

        button.setOnClickListener {
            startActivity<BikeRent>()
        }
    }
}

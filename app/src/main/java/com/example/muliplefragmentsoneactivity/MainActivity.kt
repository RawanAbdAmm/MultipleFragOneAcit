import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.muliplefragmentsoneactivity.R
import androidx.navigation.fragment.NavHostFragment
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = NavHostFragment.create(R.navigation.nav_graph)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()
    }
}

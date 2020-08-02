package net.larntech.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_apple.*

class AppleActivity : AppCompatActivity() {

    var itemModal: ItemModal? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apple)

        itemModal = intent.getSerializableExtra("data") as ItemModal;

        imageView.setImageResource(itemModal!!.image)
        tvName.text = itemModal!!.name


    }
}
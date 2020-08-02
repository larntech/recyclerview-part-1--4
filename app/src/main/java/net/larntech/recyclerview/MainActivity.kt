package net.larntech.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemAdapter.ClickedItem {
    var itemListModal = arrayOf(
        ItemModal(R.drawable.apple,"Apple", "apple desc"),
        ItemModal(R.drawable.oranges,"Oranges", "oranges desc"),
        ItemModal(R.drawable.banana,"Banana", "banana desc"),
        ItemModal(R.drawable.kiwi,"Kiwi", "kiwi desc"),
        ItemModal(R.drawable.watermelon,"Watermelon", "watermelon desc")

    )

    var itemModalList = ArrayList<ItemModal>();

    var itemAdapter: ItemAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(item in itemListModal){
            itemModalList.add(item)
        }

        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true)

        itemAdapter = ItemAdapter(this);
        itemAdapter!!.setData(itemModalList)
        recyclerView.adapter = itemAdapter
    }

    override fun clickedItem(itemModal: ItemModal) {
        var itemModal1 = itemModal;
        var name = itemModal1.name;

        when(name){
            "Apple"->
                startActivity(Intent(this@MainActivity,AppleActivity::class.java).putExtra("data",itemModal1))
            "Kiwi"->
                startActivity(Intent(this@MainActivity, KiwiActivity::class.java).putExtra("data",itemModal1))
            else -> {
                Toast.makeText(this@MainActivity, "No Action", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu);

        var menuItem = menu!!.findItem(R.id.searchView);

        var searchView = menuItem.actionView as SearchView

        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return  true;
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               itemAdapter!!.filter.filter(p0);
             return true
            }

        })



        return true;
    }
}
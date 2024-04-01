package com.example.pizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import com.example.pizza.PizzaDetailActivity
import com.example.pizza.adapter.PizzaAdapter
import com.example.pizza.classes.Pizza
import com.example.pizza.service.PizzaService

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var fs: PizzaService
    private lateinit var liste: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fs = PizzaService.instance!!
        liste = findViewById(R.id.listview)
        liste.adapter = PizzaAdapter(this, fs.findAll())
        liste.onItemClickListener = this
        Log.d("ListSize", "Size: ${fs.findAll().size}")
        if(fs.findAll().isEmpty()){
            fs.create(
                Pizza(
                    "Pizza Margherita",
                    5,
                    R.drawable.pizza_sliders,
                    30,
                    "Tomato, Mozzarella, Basil",
                    "Classic Margherita Pizza",
                    "Preheat the oven to 475°F (245°C). Place the pizza in the oven and bake until the crust is golden and the cheese is bubbly and browned."
                )
            )

            fs.create(
                Pizza(
                    "Pepperoni Pizza",
                    7,
                    R.drawable.pizza_pepperoni,
                    25,
                    "Tomato, Mozzarella, Pepperoni",
                    "Spicy Pepperoni Pizza",
                    "Preheat the oven to 450°F (230°C). Top with pepperoni and bake until the crust is golden and the cheese is melted and slightly browned."
                )
            )
            fs.create(
                Pizza(
                    "Pepper Pizza",
                    7,
                    R.drawable.pizza_pepper,
                    20,
                    "Tomato, Mozzarella, Pepper",
                    "Spicy Pepperoni Pizza",
                    "Preheat the oven to 450°F (230°C). Top with pepperoni and bake until the crust is golden and the cheese is melted and slightly browned."
                )
            )
            fs.create(
                Pizza(
                    "Chicken bbq Pizza",
                    7,
                    R.drawable.pizza_bbq_chicken,
                    20,
                    "bbq, Mozzarella, Chicken",
                    "Delicious Chicken Pizza",
                    "Preheat the oven to 450°F (230°C). Top with pepperoni and bake until the crust is golden and the cheese is melted and slightly browned."
                )
            )
            fs.create(
                Pizza(
                    "Mexican Pizza",
                    7,
                    R.drawable.pizza_mexican,
                    35,
                    "Tomato, Mozzarella, Pepperoni",
                    "Spicy Pepperoni Pizza",
                    "Preheat the oven to 450°F (230°C). Top with pepperoni and bake until the crust is golden and the cheese is melted and slightly browned."
                )
            )
            fs.create(
                Pizza(
                    "Mexican Pizza",
                    7,
                    R.drawable.pizza_garlic_cheese,
                    35,
                    "Tomato, Mozzarella, Pepperoni",
                    "Spicy Pepperoni Pizza",
                    "Preheat the oven to 450°F (230°C). Top with pepperoni and bake until the crust is golden and the cheese is melted and slightly browned."
                )
            )
            fs.create(
                Pizza(
                    "Mexican Pizza",
                    7,
                    R.drawable.pizza_veggie,
                    35,
                    "Tomato, Mozzarella, Pepperoni",
                    "Spicy Pepperoni Pizza",
                    "Preheat the oven to 450°F (230°C). Top with pepperoni and bake until the crust is golden and the cheese is melted and slightly browned."
                )
            )
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out these cool recipes!!!")
        intent.putExtra(Intent.EXTRA_TEXT, "The apps link's here....")
        startActivity(Intent.createChooser(intent, "Share via"))
        return super.onOptionsItemSelected(item)
    }
    override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
        val id = view.findViewById<TextView>(R.id.id)
        val intent = Intent(this, PizzaDetailActivity::class.java)
//intent.putExtra("Description",id.getText());
        intent.putExtra("id", id.text)
        startActivity(intent)
    }
}
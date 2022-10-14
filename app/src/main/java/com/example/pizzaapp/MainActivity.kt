package com.example.pizzaapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun onClick(view: View){
        lateinit var send : Button
        lateinit var txtFirstname : EditText
        lateinit var txtLastname : EditText
        lateinit var txtAddress : EditText

       //get all checkboxes toppings value
        lateinit var chkPepperoni : CheckBox
        lateinit var chkMushroom : CheckBox
        lateinit var chkBacon : CheckBox
        lateinit var chkExtraCheese : CheckBox
        lateinit var chTomatoAndBasil : CheckBox
        lateinit var chkPesto : CheckBox

        //get all checkboxes size value
        lateinit var chkSmall : CheckBox
        lateinit var chkMedium : CheckBox
        lateinit var chkLarge : CheckBox
        var size = ""

        // get all user's info
        txtFirstname = findViewById(R.id.firstname_edit_text)
        var firstname = txtFirstname.text.toString()
        txtLastname = findViewById(R.id.lastname_edit_text)
        var lastname = txtLastname.text.toString()
        txtAddress = findViewById(R.id.address_edit_text)
        var address = txtAddress.text.toString()

        // get the checkboxes values using ids from xml file 
        chkPepperoni = findViewById(R.id.pepperoni_checkbox)
        chkMushroom = findViewById(R.id.mushroom_checkbox)
        chkBacon = findViewById(R.id.bacon_checkbox)
        chkExtraCheese = findViewById(R.id.extra_cheese_checkbox)
        chTomatoAndBasil = findViewById(R.id.tomato_and_basil_checkbox)
        chkPesto = findViewById(R.id.pesto_checkbox)

        chkSmall = findViewById(R.id.small_checkbox)
        chkMedium = findViewById(R.id.medium_checkbox)
        chkLarge = findViewById(R.id.large_checkbox)

        // get all defined checkboxes values from user
        var pepperoni = chkPepperoni.isChecked
        var mushroom = chkMushroom.isChecked
        var bacon = chkBacon.isChecked
        var extraCheese = chkExtraCheese.isChecked
        var tomatoAndBasil = chTomatoAndBasil.isChecked
        var pesto = chkPesto.isChecked

        var small = chkSmall.isChecked
        var medium = chkMedium.isChecked
        var large = chkLarge.isChecked

        // get the checked size
        if(small){
            size = "Small"
        }else if(medium){
            size = "Medium"
        }else if(large){
            size = "Large"
        }

        //get all toppings values and add them into an array of toppings
        var toppings = arrayOf(pepperoni, mushroom, bacon, extraCheese, tomatoAndBasil, pesto)
        var toppingsName = arrayOf("Pepperoni", "Mushroom", "Bacon", "Extra Cheese", "Tomato and Basil", "Pesto")
        var toppingsList = ""
        for(i in toppings.indices){
            if(toppings[i]){
                toppingsList += toppingsName[i] + ", "
            }
        }

        // send email to the seller
       val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "bouhamedfarioula@gmail.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pizza Order")
        intent.putExtra(Intent.EXTRA_TEXT, "Hello, I am $firstname $lastname and I would like to order a $size pizza with $toppingsList. My address is $address")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

        //send sms to the seller
        if (firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || size.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }else {
            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.putExtra(
                "sms_body",
                "Hello my name is $firstname $lastname. I would like to order a $size pizza with $toppingsList. My delivery address is:  $address. Thank you."
            )
            smsIntent.type = "vnd.android-dir/mms-sms"
            startActivity(smsIntent)
            Toast.makeText(this, "Order Placed", Toast.LENGTH_LONG).show()

        }


       /* var number = "23214235"
        checkPermissions()
        val smsManager: SmsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("$number",null,"test",null,null)*/
        //setUp sms intent
        /*val smsIntent = Intent(Intent.ACTION_VIEW)
        smsIntent.data = Uri.parse("sms:$number")*/
    }

    // to ask for runtime permissions
    private fun checkPermissions(){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),101)
        }
    }
}
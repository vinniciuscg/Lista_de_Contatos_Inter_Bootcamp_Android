package com.example.listadecontatos

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity(){
    protected fun setupToolBar(title: String, navgationBack: Boolean){
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(navgationBack)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        /**when(item.itemId){
            R.id.home -> {
                this.onBackPressed()
                return true
            }
        }**/
        //return super.onOptionsItemSelected(item)
        this.onBackPressed()
        return true
    }
}
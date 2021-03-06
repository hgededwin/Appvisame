package com.systemboy.appvisame.appvisame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.pkmmte.view.CircularImageView;
import com.systemboy.appvisame.activity.about.DataMannager;
import com.systemboy.appvisame.activity.about.DetailsActivity;
import com.systemboy.appvisame.activity.about.Integrantes;
import com.systemboy.appvisame.activity.about.RecyclerListener;


public class AcercaActivity extends ActionBarActivity {

   @Override
    public void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_acercade);

       if(Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 22){
           FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
           ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
           layoutParams.height = 0;
       }


       Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
       toolbar.setTitle("");
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       CircularImageView circularImageView = (CircularImageView)findViewById(R.id.image_circular);
       circularImageView.setBorderColor(getResources().getColor(R.color.white_color));
       circularImageView.setBorderWidth(4);
       circularImageView.addShadow();

       RecyclerView rv = (RecyclerView) findViewById(R.id.rv); // layout reference

       LinearLayoutManager llm = new LinearLayoutManager(this);
       rv.setLayoutManager(llm);
       rv.setHasFixedSize(true); // to improve performance

       rv.setAdapter(new DataMannager()); // the data manager is assigner to the RV
       rv.addOnItemTouchListener( // and the click is handled
               new RecyclerListener(this, new RecyclerListener.OnItemClickListener() {
                   @Override public void onItemClick(View view, int position) {
                       Intent intent = new Intent(AcercaActivity.this, DetailsActivity.class);
                       intent.putExtra(DetailsActivity.ID, Integrantes.CONTACTS[position].getId());

                       ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                               // the context of the activity
                               AcercaActivity.this,

                               // For each shared element, add to this method a new Pair item,
                               // which contains the reference of the view we are transitioning *from*,
                               // and the value of the transitionName attribute
                               new Pair<View, String>(view.findViewById(R.id.CONTACT_circle),
                                       getString(R.string.transition_name_circle)),
                               new Pair<View, String>(view.findViewById(R.id.CONTACT_name),
                                       getString(R.string.transition_name_name)),
                               new Pair<View, String>(view.findViewById(R.id.CONTACT_phone),
                                       getString(R.string.transition_name_phone))
                       );
                       ActivityCompat.startActivity(AcercaActivity.this, intent, options.toBundle());
                   }
               }));
   }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }
}

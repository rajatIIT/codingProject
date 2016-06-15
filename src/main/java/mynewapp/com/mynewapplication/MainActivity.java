package mynewapp.com.mynewapplication;


import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.xml.sax.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import prettify.PrettifyParser;


// just single Activity
public class MainActivity extends AppCompatActivity {

    /*
    String[] items;

    // represents the drawer
    private DrawerLayout drawerLayout;

    // drawer has a list and a listview attached to it.
    private ListView mDrawerList;

    private ArrayAdapter<String> mAdapter;


    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
*/


    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    //TextView simpleTV;
    WebView simpleWV;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create the action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);



        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,myToolbar, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                getSupportActionBar().setTitle("<== Click here to open list!");
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                getSupportActionBar().setTitle("<== Click here to open list!");
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());



        /*
        InputStream kruskalsStream = getResources().openRawResource(R.raw.kruskals);

        String code = "package mynewapp.com.mynewapplication;\n" +
                "\n" +
                "\n" +
                "import android.app.FragmentManager;\n" +
                "import android.os.Bundle;";

        try {
            code = IOUtils.toString(kruskalsStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

//        simpleTV = (TextView) findViewById(R.id.myTV);


  //      PrettifyHighlighter highlighter = new PrettifyHighlighter();




//        String highlighted = highlighter.highlight("java", code);


// just fire the intent
        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
        startActivity(intent);

        //simpleTV.setText(Html.fromHtml(highlighted));

     //   simpleTV.setText(((Html.fromHtml(highlighted))));

     //   String sample = "<html><body>" + "blah blah" + "</body></html>";











        //    simpleWV.getSettings().setJavaScriptEnabled(true);
    //    simpleWV.loadData(sample,"text/html",null);
//        simpleWV.loadData(highlighted,"text/html",null);
//simpleWV.loadUrl("http://www.google.com");


       /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referring to drawerlist from the view
        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();
        drawerLayout.openDrawer(drawerLayout);
        setupDrawer();


       Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        */

      //  getSupportActionBar().setTitle("My Title");
     //   getSupportActionBar().setHomeButtonEnabled(true);
    //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/*
        setContentView(R.layout.activity_main_1);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
       // setSupportActionBar(toolbar);
        //ActionBar ab  = getSupportActionBar();
        //ab.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        //ab.setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();


        ListView simpleListView = (ListView)findViewById(R.id.new_list);

        // the adapter is related ot the source.
        ListAdapter adapter = simpleListView.getAdapter();


setupDrawer();

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);




      //  mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, items));

     //   mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //ab.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO);
        // set display show home

        */

    }



    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    //    simpleTV.setText(position + "");


    }


    private void addDrawerItems() {

        /*
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        // create a simple list adapter
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        */
    }




/*
    public void setupDrawer() {

        items = getResources().getStringArray(R.array.items);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);


        /*
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {


            public void onDrawerOpened(View drawerView) {
            }


            public void onDrawerClosed(View view) {
            }
        };

       /



    //    mDrawerList.setAdapter(adapter);


    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                //super.onBackPressed();
                drawerLayout.openDrawer(Gravity.RIGHT);
                return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

      */
}

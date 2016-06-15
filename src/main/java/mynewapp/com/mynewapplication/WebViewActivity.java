package mynewapp.com.mynewapplication;

/**
 * Created by rajatpawar on 5/25/16.
 */
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class WebViewActivity extends AppCompatActivity{


    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    //TextView simpleTV;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    WebView simpleWV;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doDrawerStuff();

        simpleWV = (WebView)findViewById(R.id.myWV);
        simpleWV.setWebViewClient(new WebViewClient());
        simpleWV.getSettings().setBuiltInZoomControls(true);
        simpleWV.getSettings().setUseWideViewPort(true);


        //simpleWV.setInitialScale();
        //String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        //simpleWV.getSettings().setUserAgentString(newUA);



        simpleWV.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        if (Build.VERSION.SDK_INT >= 19) {
             // chromium, enable hardware acceleration
            simpleWV.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            // simpleWV.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }


        simpleWV.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        String sample = "<html><body>" + "Welcome to " + getResources().getString(R.string.app_name) + "</body></html>";
        simpleWV.getSettings().setJavaScriptEnabled(true);
        simpleWV.loadData(sample,"text/html",null);









     //   simpleWV = (WebView)findViewById(R.id.myWV);

        simpleWV.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);


        if (Build.VERSION.SDK_INT >= 19) {
            // chromium, enable hardware acceleration
            simpleWV.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            simpleWV.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }


        simpleWV.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        simpleWV.getSettings().setJavaScriptEnabled(true);

//        getSupportActionBar().setTitle("<= Open menu and select topic!");

    //    fetchAndLoadData(R.raw.depth_first_search,1);


      //



        // simpleWV.loadUrl("http://www.google.com");
//simpleWV.zoomOut();simpleWV.zoomOut();
  //      simpleWV.zoomOut();simpleWV.zoomOut();
        /*


        if (Build.VERSION.SDK_INT >= 19) {
            // chromium, enable hardware acceleration
            simpleWV.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            simpleWV.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }


        simpleWV
                .getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);



        String sample = "<html><body>" + "blah blah" + "</body></html>";
            simpleWV.getSettings().setJavaScriptEnabled(true);
            simpleWV.loadData(sample,"text/html",null);


      //   webView = (WebView) findViewById(R.id.myWV);
      //   webView.getSettings().setJavaScriptEnabled(true);
      //  webView.loadUrl("http://www.google.com");\

        */

    }


    public void doDrawerStuff() {

        // create the action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,myToolbar, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
              //  getSupportActionBar().setTitle(mTitle);

                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            //    getSupportActionBar().setTitle(mDrawerTitle);
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





    }


    public void fetchAndLoadData(int resourceName, int index) {



        // fetch the data
        InputStream kruskalsStream = getResources().openRawResource(resourceName);

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





        // display the data
        PrettifyHighlighter highlighter = new PrettifyHighlighter();
        String highlighted = highlighter.highlight("java", code);

        //  String sample = "<html><body>" + "blah blah" + "</body></html>";


        simpleWV.loadData(highlighted,"text/html",null);

//        getSupportActionBar().setTitle(getResources().getStringArray(R.array.planets_array)[index]);
      //  getSupportActionBar().setTitle(mTitle);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

            if(position==0){
                // we got Kruskals.

                fetchAndLoadData(R.raw.kruskals,position);
            } else if (position==1){
                fetchAndLoadData(R.raw.depth_first_search,position);
            } else if (position==2){
                fetchAndLoadData(R.raw.binary_tree,position);
            }else if (position==3){
                fetchAndLoadData(R.raw.binary_search_tree,position);
            }else if (position==4){
                fetchAndLoadData(R.raw.tree_operations,position);
            }else if (position==5){
                fetchAndLoadData(R.raw.utils,position);
            } else if (position==6){
                fetchAndLoadData(R.raw.bubble_sort,position);
            } else if (position==7){
                fetchAndLoadData(R.raw.insertion_sort,position);
            } else if (position==8){
                fetchAndLoadData(R.raw.lcs,position);
            } else if (position==9){
                fetchAndLoadData(R.raw.matrix_multiplication,position);
            } else if (position==10){
                fetchAndLoadData(R.raw.rod_cutting,position);
            } else if (position==11){
                fetchAndLoadData(R.raw.heap_sort,position);
            } else if (position==12){
                fetchAndLoadData(R.raw.dijkstra,position);
            } else if (position==13){
                fetchAndLoadData(R.raw.simple_vertex,position);
            }
            else if (position==14){
                fetchAndLoadData(R.raw.dijkstra_vertex,position);
            } else if (position==15){
                fetchAndLoadData(R.raw.dijkstra_weight_comparator,position);
            } else if (position==16){
                fetchAndLoadData(R.raw.graph_main,position);
            } else if (position==17){
                fetchAndLoadData(R.raw.simple_vertex,position);
            }
            else if (position==18){
                fetchAndLoadData(R.raw.graph_vertex,position);
            } else if (position==19){
                fetchAndLoadData(R.raw.graph_edge,position);
            } else if (position==20){
                fetchAndLoadData(R.raw.graph_edge_comparator,position);
            } else if (position==21){
                fetchAndLoadData(R.raw.graph_utils,position);
            }  else if (position==22){
                fetchAndLoadData(R.raw.merge_sort,position);
            }  else if (position==23){

                //simpleWV.loadDataWithBaseURL(highlighted,"text/html",null);
                //simpleWV.lo
                simpleWV.loadUrl("file:///android_asset/merge_sort_wiki.html");
              //  fetchAndLoadData(R.drawable.merge_sort_wiki);
            } else if (position==24){
                fetchAndLoadData(R.drawable.merge_sort_wiki_graph,position);
            }  else if (position==24){
                fetchAndLoadData(R.raw.merge_sort_sample_run,position);
            }


        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
       // setTitle(mPlanetTitles[position]);
        setTitle(getResources().getStringArray(R.array.planets_array)[position]);
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





}

package ysdesigns.moneytrackerapp.DrawerPackage;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import ysdesigns.moneytrackerapp.DashboardPackage.DashBoardFragment;
import ysdesigns.moneytrackerapp.DataPackage.Expense;
import ysdesigns.moneytrackerapp.NewTransactionPackage.NewExpenseActivity;
import ysdesigns.moneytrackerapp.R;
import ysdesigns.moneytrackerapp.UtilPackage.ImageUtil;
import ysdesigns.moneytrackerapp.UtilPackage.Utils;

import static ysdesigns.moneytrackerapp.UtilPackage.Utils.initializeImageLoader;

public class DrawerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private FragmentManager fragmentManager;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        initializeImageLoader(this);
        setUpToolBar(Utils.getString(this, R.string.app_name));
        setupFloatingButton();
        setUpNavigationDrawerListView();
    }

    private void setUpToolBar(String toolbarTitle) {
        // Initialize & Setting up the toolbar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(toolbarTitle);
    }


    private void setupFloatingButton() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewExpenseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpNavigationDrawerListView() {

        // Initializing Required View Component
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_view);

        // Setting Up The HeaderView for DrawerList
        View headerView = getLayoutInflater().inflate(
                R.layout.header_navigation_drawer_social, mDrawerList, false);

        ImageView imageView = (ImageView) headerView.findViewById(R.id.image);
        ImageUtil.displayRoundImage(
                imageView,
                "http://pengaja.com/uiapptemplate/newphotos/profileimages/0.jpg",
                null);
        mDrawerList.addHeaderView(headerView);

        // Adding Starting Fragment
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, new DashBoardFragment())
                .commit();


        // Setting DrawerLayout & DrawerList
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
        mDrawerList.setBackgroundColor(Utils.getSampleColor(this));
        mDrawerList.getLayoutParams().width = (int) getResources()
                .getDimension(R.dimen.drawer_width_social);

        // Configuring DrawerListView with Adapter & Listener
        mDrawerList.setAdapter(new DrawerListAdapter(this));
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
//                getSupportActionBar().setTitle(mTitle);
//                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
//                getSupportActionBar().setTitle(mDrawerTitle);
//                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    @Override
    public void setTitle(CharSequence title) {
//        mTitle = title;
//        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

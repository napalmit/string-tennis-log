package it.napalm.stringtennislog;

import it.napalm.stringtennislog.utils.NMMenuAdapter;
import it.napalm.stringtennislog.utils.NMMenuItemModel;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView mDrawerList;
	private DrawerLayout mDrawer;
	private CustomActionBarDrawerToggle mDrawerToggle;
	private String[] menuItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// enable ActionBar app icon to behave as action to toggle nav drawer
				getActionBar().setDisplayHomeAsUpEnabled(true);
				 

				mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

				// set a custom shadow that overlays the main content when the drawer
				// opens
				mDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

				_initMenu();
				mDrawerToggle = new CustomActionBarDrawerToggle(this, mDrawer);
				mDrawer.setDrawerListener(mDrawerToggle);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
	}
	
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawer.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_save).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * The action bar home/up should open or close the drawer.
		 * ActionBarDrawerToggle will take care of this.
		 */
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// Handle your other action bar items...
		return super.onOptionsItemSelected(item);
	}
	
	private void _initMenu() {
		NMMenuAdapter mAdapter = new NMMenuAdapter(this);

		// Add Header
		mAdapter.addHeader(R.string.ns_menu_main_header);

		// Add first block

		menuItems = getResources().getStringArray(
				R.array.ns_menu_items);
		String[] menuItemsIcon = getResources().getStringArray(
				R.array.ns_menu_items_icon);

		int res = 0;
		for (String item : menuItems) {

			int id_title = getResources().getIdentifier(item, "string",
					this.getPackageName());
			int id_icon = getResources().getIdentifier(menuItemsIcon[res],
					"drawable", this.getPackageName());

			NMMenuItemModel mItem = new NMMenuItemModel(id_title, id_icon);
			mAdapter.addItem(mItem);
			res++;
		}

		//mAdapter.addHeader(R.string.ns_menu_main_header2);

		mDrawerList = (ListView) findViewById(R.id.drawer);
		if (mDrawerList != null)
			mDrawerList.setAdapter(mAdapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {


		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			// Highlight the selected item, update the title, and close the drawer
			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
		    String text= "menu click... should be implemented";
			Toast.makeText(MainActivity.this, text , Toast.LENGTH_LONG).show();
			mDrawer.closeDrawer(mDrawerList);
		}

	}
	
	private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {

		public CustomActionBarDrawerToggle(Activity mActivity,DrawerLayout mDrawerLayout){
			super(
			    mActivity,
			    mDrawerLayout, 
			    R.drawable.ic_drawer,
			    R.string.ns_menu_open, 
			    R.string.ns_menu_close);
		}

		@Override
		public void onDrawerClosed(View view) {
			getActionBar().setTitle(getString(R.string.ns_menu_close));
			invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
		}

		@Override
		public void onDrawerOpened(View drawerView) {
			getActionBar().setTitle(getString(R.string.ns_menu_open));
			invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
		}
	}

}

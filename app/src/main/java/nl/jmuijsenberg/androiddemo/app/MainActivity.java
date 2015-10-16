package nl.jmuijsenberg.androiddemo.app;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.adapters.PageAdapter;
import nl.jmuijsenberg.androiddemo.app.dialogs.DatePickerFragment;
import nl.jmuijsenberg.androiddemo.app.fragments.Fragment1;
import nl.jmuijsenberg.androiddemo.app.fragments.Fragment2;
import nl.jmuijsenberg.androiddemo.control.MainController;
import nl.jmuijsenberg.androiddemo.devices.android.NativeDevice;
import nl.jmuijsenberg.androiddemo.repository.sqlite.RepositorySqlite;
import nl.jmuijsenberg.androiddemo.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener {
    public MainViewModel mMainViewModel;

    @Bind(R.id.viewPager)
    public ViewPager mViewPager;

    @Bind(R.id.pagerTabStrip)
    public PagerTabStrip mPagerTabStrip;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mActionBar = getSupportActionBar();
        if( mActionBar != null) {
            mActionBar.setLogo(R.drawable.ic_3d_rotation_24dp);
            mActionBar.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO |
                                         ActionBar.DISPLAY_SHOW_HOME |
                                         ActionBar.DISPLAY_HOME_AS_UP |
                                         ActionBar.DISPLAY_SHOW_TITLE |
                                         ActionBar.DISPLAY_SHOW_CUSTOM);
        }

        //setSupportActionBar(); When using new ToolBar class

        mPagerTabStrip.setTextSpacing(10);
        mPagerTabStrip.setDrawFullUnderline(false);

        mMainViewModel = new MainViewModel();
        mMainViewModel.mMainController = new MainController();
        mMainViewModel.mMainController.mRepositiory = new RepositorySqlite();
        mMainViewModel.mMainController.mDevice = new NativeDevice();

        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mViewPager.setCurrentItem(position, true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @OnClick(R.id.updateDateButton)
    public void updateDate(ImageButton button) {
        android.support.v4.app.DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}

package nl.jmuijsenberg.androiddemo.app;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.adapters.PageAdapter;
import nl.jmuijsenberg.androiddemo.app.dialogs.LoginDialogFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, DatePickerDialog.OnDateSetListener {
    @Bind(R.id.fab)
    public FloatingActionButton mFloatingActionButton;

    @Bind(R.id.toolbar)
    public Toolbar mToolbar;

    @Bind(R.id.viewPager)
    public ViewPager mViewPager;

    @Bind(R.id.pagerTabStrip)
    public PagerTabStrip mPagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mPagerTabStrip.setTextSpacing(10);
        mPagerTabStrip.setDrawFullUnderline(false);

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

    @OnClick(R.id.fab)
    public void updateDate(FloatingActionButton button) {
        LoginDialogFragment datePicker = new LoginDialogFragment();
        datePicker.show(getSupportFragmentManager(), "login");
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
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
}

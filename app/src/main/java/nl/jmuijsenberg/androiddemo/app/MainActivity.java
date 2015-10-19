package nl.jmuijsenberg.androiddemo.app;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.DatePicker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.adapters.PageAdapter;
import nl.jmuijsenberg.androiddemo.app.dialogs.LoginDialogFragment;
import nl.jmuijsenberg.androiddemo.app.fragments.Fragment2;
import nl.jmuijsenberg.androiddemo.app.fragments.PersonFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, PersonFragment.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener, DatePickerDialog.OnDateSetListener {
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
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
}

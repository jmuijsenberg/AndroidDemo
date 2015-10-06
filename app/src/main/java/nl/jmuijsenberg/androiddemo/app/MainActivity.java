package nl.jmuijsenberg.androiddemo.app;

import butterknife.Bind;
import butterknife.ButterKnife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.control.MainController;
import nl.jmuijsenberg.androiddemo.devices.android.NativeDevice;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.repository.sqlite.RepositorySqlite;
import nl.jmuijsenberg.androiddemo.viewmodels.MainViewModel;

public class MainActivity extends Activity {
    public MainViewModel mMainViewModel;

    @Bind(R.id.textView)
    public TextView mTextView;
    
    @Bind(R.id.viewPager)
    public ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMainViewModel = new MainViewModel();
        mMainViewModel.mMainController = new MainController();
        mMainViewModel.mMainController.mRepositiory = new RepositorySqlite();
        mMainViewModel.mMainController.mDevice = new NativeDevice();

        mTextView.setText("Programmaticllay set text");
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
}

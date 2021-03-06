package nl.jmuijsenberg.androiddemo.app.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import nl.jmuijsenberg.androiddemo.app.fragments.StudentFragment;
import nl.jmuijsenberg.androiddemo.app.fragments.Fragment2;

public class PageAdapter extends FragmentStatePagerAdapter {
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return StudentFragment.newInstance();
            case 1:
                return Fragment2.newInstance();
            case 2:
                return StudentFragment.newInstance();
            case 3:
                return Fragment2.newInstance();
            case 4:
                return StudentFragment.newInstance();
            case 5:
                return Fragment2.newInstance();
            default:
                assert(false);
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tab One";
            case 1:
                return "Tab Two";
            case 2:
                return "Tab Three";
            case 3:
                return "Tab Four";
            case 4:
                return "Tab Five";
            case 5:
                return "Tab Six";
            default:
                assert(false);
        }

        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}

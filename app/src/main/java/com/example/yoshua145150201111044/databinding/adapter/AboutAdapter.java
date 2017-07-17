package com.example.yoshua145150201111044.databinding.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.yoshua145150201111044.databinding.view.AboutTab1;
import com.example.yoshua145150201111044.databinding.view.AboutTab2;
import com.example.yoshua145150201111044.databinding.view.AboutTab3;
import com.example.yoshua145150201111044.databinding.view.AboutTab4;

/**
 * Created by iboy on 07/07/17.
 */

public class AboutAdapter extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public AboutAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            return new AboutTab1();
        }
        else if(position == 1)
        {
            return new AboutTab2();
        }
        else if(position == 2)
        {
            return new AboutTab3();
        }
        else
        {
            return new AboutTab4();
        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override//wajib
    public int getCount() {
        return NumbOfTabs;
    }
}

package com.lihoy21gmail.mywidget;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class Statistics_Activity extends FragmentActivity implements
        TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
    // static String TAG = "myLog";
    //ListView lvData;
    //SimpleCursorAdapter scAdapter;
    ViewPager viewPager;
    TabHost tab_Host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);
        initViewPager();
        initTabHost();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new OneTabFragment());
        listFragment.add(new TwoTabFragment());

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), listFragment);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }


    private void initTabHost() {
        tab_Host = (TabHost) findViewById(R.id.tabHost);
        tab_Host.setup();

        TabHost.TabSpec tabSpec;
        tabSpec = tab_Host.newTabSpec("tag1");
        tabSpec.setIndicator(getString(R.string.name_of_tab_1));
        tabSpec.setContent(new EmptyContent(getApplicationContext()));
        tab_Host.addTab(tabSpec);

        tabSpec = tab_Host.newTabSpec("tag2");
        tabSpec.setIndicator(getString(R.string.name_of_tab_2));
        tabSpec.setContent(new EmptyContent(getApplicationContext()));
        tab_Host.addTab(tabSpec);

        tab_Host.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tabId) {
        int selectedItem = tab_Host.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);

        HorizontalScrollView hScrollview = (HorizontalScrollView) findViewById(R.id.h_scroll_view);
        View tabView = tab_Host.getCurrentTabView();
        int scrollPos = tabView.getLeft() - (hScrollview.getWidth() - tabView.getWidth()) / 2;
        hScrollview.smoothScrollTo(scrollPos, 0);
    }


    public class EmptyContent implements TabHost.TabContentFactory {
        Context context;

        public EmptyContent(Context mContext) {
            context = mContext;
        }

        @Override
        public View createTabContent(String tag) {
            View emptyView = new View(context);
            emptyView.setMinimumHeight(0);
            emptyView.setMinimumWidth(0);
            return emptyView;
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int selectedItem) {
        tab_Host.setCurrentTab(selectedItem);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }


}

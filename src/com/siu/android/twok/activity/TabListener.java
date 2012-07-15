package com.siu.android.twok.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.siu.android.twok.R;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 16/05/12
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */

//http://developer.android.com/guide/topics/ui/actionbar.html
public class TabListener<T extends SherlockFragment> implements ActionBar.TabListener {
    private Fragment mFragment;
    private final SherlockFragmentActivity mActivity;
    private final String mTag;
    private final Class<T> mClass;
    private final Bundle mArgs;

    /** Constructor used each time a new tab is created.
     * @param activity  The host Activity, used to instantiate the fragment
     * @param tag  The identifier tag for the fragment
     * @param clz  The fragment's Class, used to instantiate the fragment
     */
    public TabListener(SherlockFragmentActivity activity, String tag, Class<T> clz) {
        this(activity, tag, clz, null);
    }

    public TabListener(SherlockFragmentActivity activity, String tag, Class<T> clz, Bundle args) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mArgs = args;

        mFragment = mActivity.getSupportFragmentManager().findFragmentByTag(mTag);
    }

    /* The following are each of the ActionBar.TabListener callbacks */

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Check if the fragment is already initialized
        if (mFragment == null) {
            // If not, instantiate and add it to the activity
            mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
            ft.add(android.R.id.content, mFragment, mTag);
        } else {
            // If it exists, simply attach it in order to show it
            ft.attach(mFragment);
        }
    }



    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            // Detach the fragment, because another one is being attached
            ft.detach(mFragment);
        }
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // User selected the already selected tab. Usually do nothing.
    }
}
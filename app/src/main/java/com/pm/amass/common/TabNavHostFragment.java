package com.pm.amass.common;

import androidx.annotation.NonNull;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

/**
 * @author pm
 * @date 2019/11/29
 * @email puming@zdsoft.cn
 */
public class TabNavHostFragment extends NavHostFragment {
    @NonNull
    @Override
    protected Navigator<? extends FragmentNavigator.Destination> createFragmentNavigator() {
        return new TabNavigator(getContext(),getChildFragmentManager(),getId());
    }
}

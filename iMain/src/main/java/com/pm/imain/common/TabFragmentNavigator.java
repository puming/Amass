package com.pm.imain.common;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;

import java.lang.reflect.Field;
import java.util.ArrayDeque;

/**
 * @author pm
 * @date 2019/11/29
 * @email puming@zdsoft.cn
 */
public class TabFragmentNavigator extends FragmentNavigator {
    FragmentManager mFragmentManager;

    public TabFragmentNavigator(@NonNull Context context, @NonNull FragmentManager manager, int containerId) {
        super(context, manager, containerId);
        mFragmentManager = manager;
    }

    @Nullable
    @Override
    public NavDestination navigate(@NonNull Destination destination, @Nullable Bundle args, @Nullable NavOptions navOptions, @Nullable Navigator.Extras navigatorExtras) {
        try {
            //反射获取mBackStack mIsPendingBackStackOperation
            Class<? extends TabFragmentNavigator> clazz = getClass();
//            val mBackStackField = FragmentNavigator::class.java.getDeclaredField("mBackStack");
            Field mBackStackField = clazz.getDeclaredField("mBackStack");
//            mBackStackField.isAccessible = true;
            mBackStackField.setAccessible(true);
//            var mBackStack: ArrayDeque<Int> = mBackStackField.get(this) as ArrayDeque<Int>
            ArrayDeque<Integer> mBackStack = mBackStack = (ArrayDeque<Integer>) mBackStackField.get(this);

                   /* val mIsPendingBackStackOperationField =
                    FragmentNavigator::class.java.getDeclaredField("mIsPendingBackStackOperation")
            mIsPendingBackStackOperationField.isAccessible = true
            var mIsPendingBackStackOperation: Boolean = mIsPendingBackStackOperationField.get(this) as Boolean*/

            Field mIsPendingBackStackOperationField = clazz.getDeclaredField("mIsPendingBackStackOperation");
            mIsPendingBackStackOperationField.setAccessible(true);
            boolean mIsPendingBackStackOperation = (boolean) mIsPendingBackStackOperationField.get(this);

            if (mFragmentManager.isStateSaved()) {
                //Log.i("TAG", "Ignoring navigate() call: FragmentManager has already" + " saved its state")
                return null;
            }
           /* var className = destination.className
            if (className[0] == '.') {
                className = mContext.packageName + className
            }

            val ft = mFragmentManager.beginTransaction()

            var enterAnim = navOptions?.enterAnim ?: -1
            var exitAnim = navOptions?.exitAnim ?: -1
            var popEnterAnim = navOptions?.popEnterAnim ?: -1
            var popExitAnim = navOptions?.popExitAnim ?: -1
            if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
                enterAnim = if (enterAnim != -1) enterAnim else 0
                exitAnim = if (exitAnim != -1) exitAnim else 0
                popEnterAnim = if (popEnterAnim != -1) popEnterAnim else 0
                popExitAnim = if (popExitAnim != -1) popExitAnim else 0
                ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
            }

            val tag = destination.id.toString()
            //ft.replace(mContainerId, frag)

            val currentFragment = mFragmentManager.primaryNavigationFragment
            if (currentFragment != null) {
                ft.hide(currentFragment)
            }

            var frag = mFragmentManager.findFragmentByTag(tag)
            if (frag == null) {
                frag = instantiateFragment(
                        mContext, mFragmentManager,
                        className, args
                )
                frag.arguments = args
                ft.add(mContainerId, frag, tag)
            } else {
                ft.show(frag)
            }

            ft.setPrimaryNavigationFragment(frag)

            @IdRes val destId = destination.id
            val initialNavigation = mBackStack.isEmpty()
            // TODO Build first class singleTop behavior for fragments
            val isSingleTopReplacement = (navOptions != null && !initialNavigation
                    && navOptions.shouldLaunchSingleTop()
                    && mBackStack.peekLast().toInt() == destId)

            val isAdded: Boolean
            if (initialNavigation) {
                isAdded = true
            } else if (isSingleTopReplacement) {
                // Single Top means we only want one instance on the back stack
                if (mBackStack.size > 1) {
                    // If the Fragment to be replaced is on the FragmentManager's
                    // back stack, a simple replace() isn't enough so we
                    // remove it from the back stack and put our replacement
                    // on the back stack in its place
                    mFragmentManager.popBackStack(
                            generateMyBackStackName(mBackStack.size, mBackStack.peekLast()),
                            FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                    ft.addToBackStack(generateMyBackStackName(mBackStack.size, destId))
                    mIsPendingBackStackOperation = true
                    mIsPendingBackStackOperationField.set(this, true)
                }
                isAdded = false
            } else {
                ft.addToBackStack(generateMyBackStackName(mBackStack.size + 1, destId))
                mIsPendingBackStackOperation = true
                mIsPendingBackStackOperationField.set(this, true)
                isAdded = true
            }
            if (navigatorExtras is Extras) {
                val extras = navigatorExtras as Extras?
                for ((key, value) in extras!!.sharedElements) {
                    ft.addSharedElement(key, value)
                }
            }
            ft.setReorderingAllowed(true)
            ft.commit()
            // The commit succeeded, update our view of the world
            return if (isAdded) {
                mBackStack.add(destId)
                destination
            } else {
                null
            }*/
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}

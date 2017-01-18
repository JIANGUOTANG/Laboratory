package com.jian.util;

import android.support.v4.app.Fragment;

import com.jian.ftagment.TimeFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂类，利用工厂模式，结合fragmetn，防止fragment被重复创建
 * Created by jian on 2016/9/18.
 */
public class FragmentFactory {
    private static Map<Integer, Fragment> mFragments = new HashMap<>();
    public static Fragment createFragment(int position)
    {
        Fragment fragment = null;
        fragment = mFragments.get(position);  //在集合中取出来Fragment
        if(fragment == null)   //如果在集合中没有取出来，需要重新创建
        {
              fragment = new TimeFragment();
        }
        if(fragment != null)
        {
            mFragments.put(position, fragment);
        }
        return fragment;

    }
}

package com.jian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jian.util.FragmentFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jian on 2016/9/24.
 */

public class MyViewpagerAdapter extends FragmentPagerAdapter {
private int count;
    Date d;
    public MyViewpagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
        d=new Date();
    }
    @Override
    public int getCount() {
        return count;
    }
    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);//利用工厂类来创建frg
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return  getData(position);
    }

    /**
     * ]
     * @param position 代表距离当天多少天
     */
  private  String getData(int position){
      SimpleDateFormat df=new SimpleDateFormat("MM-dd");
      String str =  df.format(new Date(d.getTime() + (long)position * 24 * 60 * 60 * 1000));
      return  str;
  }

}

package com.wu.ftpfile.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 此工具类用来退出整个应用程序。
 * Created by Administrator on 2015/3/27.
 */
public class ExitApplication {
    private ExitApplication(){};
    private static ExitApplication instance;
    /**
     * 放activity的容器。
     */
    private List<Activity> activities =new ArrayList<Activity>();
    /**
     * 单例模式保证获得的都是一个对象
     * @return ExitApplication实例。
     */
    public static ExitApplication getInstance(){
        if (instance==null){
            instance =new ExitApplication();
        }
        return instance;
    }

    /**
     * 添加activity对象到集合中。
     * @param activity
     */
    public void addToList(Activity activity){
        if (activity!=null){
            activities.add(activity);
        }
    }

    /**
     * 遍历集合，循环杀死activity。退出应用。
     */
    public void exitApplication(){
        if (activities.size()>0){ //集合有元素才结束该应用。
            for (Activity activity:activities){
                activity.finish();
            }
        }
    }
}

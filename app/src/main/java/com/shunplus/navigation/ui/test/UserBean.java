package com.shunplus.navigation.ui.test;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.shunplus.navigation.BR;

/**
 * @author xushun on  2020/5/12 17:36.
 * Email：shunplus@163.com
 * Des： ViewModel
 */
public class UserBean extends BaseObservable {
    private String name = "232323";
    private String password;

    public UserBean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

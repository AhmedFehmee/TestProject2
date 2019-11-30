package com.fahmy.testproject.di.component;

import com.fahmy.testproject.di.PerActivity;
import com.fahmy.testproject.di.module.ActivityModule;
import com.fahmy.testproject.ui.main.MainActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}



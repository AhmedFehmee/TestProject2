package com.fahmy.testproject.di.component;

import com.fahmy.testproject.di.module.ApplicationTestModule;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {
}

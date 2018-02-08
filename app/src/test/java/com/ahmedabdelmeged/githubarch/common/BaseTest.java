package com.ahmedabdelmeged.githubarch.common;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

/**
 * Created by Ahmed Abd-Elmeged on 2/6/2018.
 */
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public abstract class BaseTest {

    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public final RxSchedulerOverrideRule overrideSchedulersRule = new RxSchedulerOverrideRule();

}

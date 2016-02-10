package com.example.martin.myapplication;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.nullValue;

@RunWith(AndroidJUnit4.class)
public class SimpleUnitTest {

    MyUnit myUnit;

    MyDependency myDependency;

    boolean dependencyWasCalled;

    @Before
    public void setUp() throws Exception {
        dependencyWasCalled = false;
        myDependency = new MyDependency() {
            @Override
            public Object compute(Object input) {
                dependencyWasCalled = true;
                return super.compute(input);
            }
        };
        myUnit = new MyUnit(myDependency);
    }

    @Test
    public void callWithNullReturnsNull() throws Exception {
        assertThat(myUnit.someComputation(null), is(nullValue()));
    }

    @Test
    public void callWithObjectReturnsObject() throws Exception {
        assertThat(myUnit.someComputation(new Object()), is(not(nullValue())));
    }

    @Test
    public void callWithObjectCallsDependency() throws Exception {
        myUnit.someComputation(new Object());
        assertThat(dependencyWasCalled, is(true));
    }

    @Test
    public void callWithoutObjectDoesNotCallDependency() throws Exception {
        myUnit.someComputation(null);
        assertThat(dependencyWasCalled, is(false));
    }
}

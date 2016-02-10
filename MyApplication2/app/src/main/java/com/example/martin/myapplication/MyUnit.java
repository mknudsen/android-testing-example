package com.example.martin.myapplication;

public class MyUnit {

private final MyDependency dependency;

    public MyUnit(MyDependency dependency) {
        this.dependency = dependency;
    }

    public Object someComputation(Object input){
        if(input == null){
            return null;
        } else {
            return dependency.compute(input);
        }
    }
}

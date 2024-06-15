package main;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import testcases.*;

@Suite
@SelectClasses( { LoginTest.class, AddToCartTest.class, AddToCartTestSingleItems.class } )
public class RunAllTestSuites {

}

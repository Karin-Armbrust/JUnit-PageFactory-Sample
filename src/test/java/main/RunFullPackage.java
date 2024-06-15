package main;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import testcases.*;

@SelectPackages("testcases")
@Suite
public class RunFullPackage {

}
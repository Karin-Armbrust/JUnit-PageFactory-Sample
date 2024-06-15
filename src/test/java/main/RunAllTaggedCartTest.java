package main;

import org.junit.platform.suite.api.*;
import testcases.*;

// This run will only run tests that are Tagged "CartTests"

@SelectPackages("testcases")
@IncludeTags("CartTests")
@Suite
public class RunAllTaggedCartTest {

}


package com.org.test.coop.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.org.test.coop.master.junit.ModuleTest;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan(basePackages = "com.org.test.coop")
//@SpringApplicationConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
@RunWith(Suite.class)
@Suite.SuiteClasses({
	AdminSvcSuiteInit.class,
    ModuleTest.class
})
public class AdminSvcTestSuite {
}
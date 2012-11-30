package org.missile.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.missile.model.base.BaseTest;
import org.missile.model.base.BaseTrackerTest;
import org.missile.model.city.CityTest;
import org.missile.model.explosion.ExplosionTest;
import org.missile.model.explosion.ExplosionTrackerTest;
import org.missile.model.missile.MissileTest;
import org.missile.model.missile.MissileTrackerTest;

@RunWith(Suite.class)
@SuiteClasses({ BaseTest.class, BaseTrackerTest.class, CityTest.class,
		ExplosionTest.class, ExplosionTrackerTest.class,
		MissileTest.class, MissileTrackerTest.class })
public class AllTests {

}

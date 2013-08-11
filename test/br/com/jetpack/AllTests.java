package br.com.jetpack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.jetpack.config.pkg.reader.PkgInfoJsonReaderTest;
import br.com.jetpack.packages.system.LogicalPackageManagerTest;

@RunWith(Suite.class)
@SuiteClasses({ PkgInfoJsonReaderTest.class, //
		LogicalPackageManagerTest.class //
})
public class AllTests {

}

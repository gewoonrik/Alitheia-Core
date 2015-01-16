package eu.sqooss.service.cache.test;

import eu.sqooss.core.AlitheiaCore;
import eu.sqooss.service.cache.Activator;
import eu.sqooss.service.cache.CacheService;
import eu.sqooss.service.cache.InMemoryCache;
import eu.sqooss.service.cache.OnDiskCache;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;

import static org.mockito.Mockito.*;

public class ActivatorTest {

	AlitheiaCore alitheiaCore = mock(AlitheiaCore.class);
	 BundleContext bc = mock(BundleContext.class);
	@Before
	public void setUp() {
		AlitheiaCore.setInstance(alitheiaCore);
	}

	@Test
	public void testStart() throws Exception {
		Activator activator = new Activator();
		activator.start(bc);

		verify(alitheiaCore, times(1)).registerService(CacheService.class, OnDiskCache.class);
	}

	@Test
	public void testStartWithProperty() throws Exception {
		when(bc.getProperty("eu.sqooss.service.cache.impl")).thenReturn("eu.sqooss.service.cache.InMemoryCache");

		Activator activator = new Activator();
		activator.start(bc);

		verify(alitheiaCore, times(1)).registerService(CacheService.class, InMemoryCache.class);
	}

}

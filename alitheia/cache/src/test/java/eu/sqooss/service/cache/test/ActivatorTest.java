package eu.sqooss.service.cache.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import eu.sqooss.core.AlitheiaCore;
import eu.sqooss.service.cache.Activator;
import eu.sqooss.service.cache.CacheService;
import eu.sqooss.service.cache.InMemoryCache;
import eu.sqooss.service.cache.OnDiskCache;
import org.junit.Before;
import org.junit.Test;

public class ActivatorTest {

	AlitheiaCore alitheiaCore = mock(AlitheiaCore.class);

	@Before
	public void setUp() {
		AlitheiaCore.setInstance(alitheiaCore);
	}

	@Test
	public void testStart() throws Exception {
		Activator activator = new Activator();
		activator.start(null);

		verify(alitheiaCore, times(1)).registerService(CacheService.class, OnDiskCache.class);
	}

	@Test
	public void testStartWithProperty() throws Exception {
		System.setProperty("eu.sqooss.service.cache.impl", "eu.sqooss.service.cache.InMemoryCache");

		Activator activator = new Activator();
		activator.start(null);

		verify(alitheiaCore, times(1)).registerService(CacheService.class, InMemoryCache.class);
	}

}

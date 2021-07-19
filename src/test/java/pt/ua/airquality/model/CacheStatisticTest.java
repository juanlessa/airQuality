package pt.ua.airquality.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheStatisticTest {
    @Test
    public void testStartEmpty(){
        CacheStatistic cacheStatistic = new CacheStatistic();

        assertEquals(0, cacheStatistic.getNotFounds());
        assertEquals(0, cacheStatistic.getFounds());
        assertEquals(0, cacheStatistic.getRequests());
        assertEquals(0, cacheStatistic.getTotalStored());
    }

    @Test
    public void testAddFoundsShouldBeNotEmpty(){
        CacheStatistic cacheStatistic = new CacheStatistic();
        cacheStatistic.addFounds();

        assertEquals(0, cacheStatistic.getNotFounds());
        assertEquals(1, cacheStatistic.getFounds());
        assertEquals(1, cacheStatistic.getRequests());
    }


    @Test
    public void testAddNotFoundsShouldBeNotEmpty(){
        CacheStatistic cacheStatistic = new CacheStatistic();
        cacheStatistic.addNotFounds();
        cacheStatistic.addNotFounds();

        assertEquals(2, cacheStatistic.getNotFounds());
        assertEquals(0, cacheStatistic.getFounds());
        assertEquals(2, cacheStatistic.getRequests());
    }
    @Test
    public void testRequestsShouldBeSumOfFoundsAndNotFounds(){
        CacheStatistic cacheStatistic = new CacheStatistic();
        cacheStatistic.addNotFounds();
        cacheStatistic.addFounds();

        assertEquals(1, cacheStatistic.getNotFounds());
        assertEquals(1, cacheStatistic.getFounds());
        assertEquals(2, cacheStatistic.getRequests());
    }

    @Test
    public void testTotalStore(){
        CacheStatistic cacheStatistic = new CacheStatistic();
        cacheStatistic.addTotalStored();
        cacheStatistic.addTotalStored();

        assertEquals(2, cacheStatistic.getTotalStored());

    }
}

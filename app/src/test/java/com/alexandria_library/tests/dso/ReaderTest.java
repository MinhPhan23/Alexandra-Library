package com.alexandria_library.tests.dso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.alexandria_library.R;
import com.alexandria_library.dso.Reader;

public class ReaderTest {
    private Reader reader;

    @Before
    public void setUp(){
        reader = new Reader("testReader", "testPass", 1);
        assertNotNull(reader);
        System.out.println("Starting unit tests for Reader");
    }
    @Test
    public void testLoginInformation(){
        assertEquals("testReader", reader.getUserName());
        assertEquals("testPass", reader.getPassword());
    }

    @Test
    public void testBooklist() {
        assertEquals(0, reader.getAllBooksList().size());
        assertEquals(0, reader.getFinishedList().size());
        assertEquals(0, reader.getInProgressList().size());
    }
    @Test
    public void testEdgeReader(){
        Reader edgeReader;
        edgeReader = new Reader(" ", " ", 0);
        assertNotNull(edgeReader);
        assertEquals(" ", edgeReader.getUserName());
        assertEquals(" ", edgeReader.getPassword());
        assertEquals(0, edgeReader.getId());
        assertEquals(0, edgeReader.getAllBooksList().size());
        assertEquals(0, edgeReader.getFinishedList().size());
        assertEquals(0, edgeReader.getInProgressList().size());
    }

    @Test
    public void testGetID(){
        assertNotNull(reader);
        assertTrue(reader.getId() == 1);
    }
    @Test
    public void testGetID2(){
        Reader newReader = new Reader("dd", "33333", 3);
        assertTrue(newReader.getId() == 3);
    }
}

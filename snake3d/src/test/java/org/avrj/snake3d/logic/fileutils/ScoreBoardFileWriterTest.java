package org.avrj.snake3d.logic.fileutils;

import java.io.File;
import java.io.IOException;
import org.avrj.snake3d.fileutils.ScoreBoardFileWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author avrj
 */
public class ScoreBoardFileWriterTest {

    private final File scoresFilePath, scoresDirectoryPath;

    public ScoreBoardFileWriterTest() {
        scoresFilePath = new File("savedScores/scores.txt");
        scoresDirectoryPath = new File("savedScores");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        if (scoresFilePath.exists()) {
            scoresFilePath.delete();
        }

        if (scoresDirectoryPath.exists()) {
            scoresDirectoryPath.delete();
        }
    }

    @After
    public void tearDown() {
        if (scoresFilePath.exists()) {
            scoresFilePath.delete();
        }

        if (scoresDirectoryPath.exists()) {
            scoresDirectoryPath.delete();
        }
    }

    @Test
    public void nonExistingFileCannotBeWritten() {
        ScoreBoardFileWriter fileWriter = new ScoreBoardFileWriter(scoresFilePath);

        assertFalse(fileWriter.writeLine("Hello|world"));
    }

    @Test
    public void existingFileCanBeWritten() {
        scoresDirectoryPath.mkdir();

        try {
            scoresFilePath.createNewFile();
        } catch (IOException ex) {
            Assert.fail("Can't create new file.");
        }

        ScoreBoardFileWriter fileWriter = new ScoreBoardFileWriter(scoresFilePath);

        assertTrue(fileWriter.writeLine("Hello|world"));
    }
}

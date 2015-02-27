package org.avrj.snake3d.logic.fileutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.avrj.snake3d.fileutils.ScoreBoardFileReader;
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
public class ScoreBoardFileReaderTest {

    private final File scoresFilePath, scoresDirectoryPath;

    public ScoreBoardFileReaderTest() {
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
    public void nonExistingFileCannotBeRead() {

        ScoreBoardFileReader fileReader = new ScoreBoardFileReader(scoresFilePath.toString());
        assertEquals(fileReader.getLines(10).size(), 0);
    }

    @Test
    public void existingFileCanBeRead() {

        scoresDirectoryPath.mkdir();

        try {
            scoresFilePath.createNewFile();
        } catch (IOException ex) {
            Assert.fail("Can't create new file.");
        }

        String text = "Hello|world";

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(scoresFilePath));
            output.write(text);
            output.close();
        } catch (IOException e) {
            Assert.fail("Can't write to file.");
        }

        ScoreBoardFileReader fileReader = new ScoreBoardFileReader(scoresFilePath.toString());

        assertEquals(fileReader.getLines(10).size(), 1);
    }
}

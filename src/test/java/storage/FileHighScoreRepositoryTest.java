package storage;

import org.json.simple.parser.ParseException;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.IOException;

public class FileHighScoreRepositoryTest {
    @Test
    public void should_read_file() throws IOException, ParseException {

        assertEquals(new DatabaseReader().readDatabase().size(), 2);
    }
}

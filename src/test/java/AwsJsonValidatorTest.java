import exceptions.MissingElementInJsonException;
import exceptions.WrongValueJsonException;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class AwsJsonValidatorTest {

    private final AwsJsonValidator jsonValidator;

    private static String jsonWithSingleAsterisk;
    private static String jsonWithWrongStructure;
    private static String jsonWithMissingElement;
    private static String jsonWithoutSingleAsterisk;


    public AwsJsonValidatorTest() {
        jsonValidator = new AwsJsonValidator();

    }

    @BeforeAll
    public static void init() throws IOException {
        jsonWithSingleAsterisk = Files.readString(Path.of("src/test/resources/singleAsterisk.json"));
        jsonWithWrongStructure = Files.readString(Path.of("src/test/resources/wrongStructure.json"));
        jsonWithMissingElement = Files.readString(Path.of("src/test/resources/missingElement.json"));
        jsonWithoutSingleAsterisk = Files.readString(Path.of("src/test/resources/withoutSingleAsterisk.json"));
    }


    @Test
    public void shouldReturnFalseIfFindSingleAsteriskInFieldResource() throws MissingElementInJsonException, WrongValueJsonException {
        boolean value = jsonValidator.validateJson(jsonWithSingleAsterisk);
        Assertions.assertFalse(value);
    }

    @Test
    public void shouldReturnTrueIfNotSingleAsteriskInFieldResource() throws MissingElementInJsonException, WrongValueJsonException {
        boolean value = jsonValidator.validateJson(jsonWithoutSingleAsterisk);
        Assertions.assertTrue(value);
    }

    @Test
    public void shouldThrowJSONExceptionIfWrongStructureJsonProvided() {
        Assertions.assertThrows(JSONException.class, () -> jsonValidator.validateJson(jsonWithWrongStructure));

    }

    @Test
    public void shouldThrowMissingElementInJsonExceptionIfMissingElement() {
        Assertions.assertThrows(MissingElementInJsonException.class, () -> jsonValidator.validateJson(jsonWithMissingElement));

    }


}




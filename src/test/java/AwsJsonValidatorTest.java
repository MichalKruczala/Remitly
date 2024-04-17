import exceptions.MissingElementInJsonException;
import exceptions.WrongValueJsonException;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AwsJsonValidatorTest {


    @Test
    public void shouldReturnFalseIfFindSingleAsteriskInFieldResource() throws MissingElementInJsonException, WrongValueJsonException {
        AwsJsonValidator jV = new AwsJsonValidator();
        boolean value = jV.validateJson(jsonStringWithSingleAsterisk);
        Assertions.assertFalse(value);
    }

    @Test
    public void shouldReturnTrueIfNotSingleAsteriskInFieldResource() throws MissingElementInJsonException, WrongValueJsonException{
        AwsJsonValidator jV = new AwsJsonValidator();
        boolean value = jV.validateJson(jsonStringWithoutSingleAsterisk);
        Assertions.assertTrue(value);
    }

    @Test
    public void shouldThrowJSONExceptionIfWrongStructureJsonProvided() {
        AwsJsonValidator jV = new AwsJsonValidator();
        Assertions.assertThrows(JSONException.class, () -> jV.validateJson(wrongStructureJsonString));

    }

    @Test
    public void shouldThrowMissingElementInJsonExceptionIfMissingElement() {
        AwsJsonValidator jV = new AwsJsonValidator();
        Assertions.assertThrows(MissingElementInJsonException.class, () -> jV.validateJson(jsonStringWithMissingElement));

    }

    private final String jsonStringWithSingleAsterisk = "{\n" +
            "    \"PolicyName\": \"root\",\n" +
            "    \"PolicyDocument\": {\n" +
            "        \"Version\": \"2012-10-17\",\n" +
            "        \"Statement\": [\n" +
            "            {\n" +
            "                \"Sid\": \"IamListAccess\",\n" +
            "                \"Effect\": \"Allow\",\n" +
            "                \"Action\": [\n" +
            "                    \"iam:ListRoles\",\n" +
            "                    \"iam:ListUsers\"\n" +
            "                ],\n" +
            "                \"Resource\": \"*\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    private final String jsonStringWithoutSingleAsterisk = "{\n" +
            "    \"PolicyName\": \"root\",\n" +
            "    \"PolicyDocument\": {\n" +
            "        \"Version\": \"2012-10-17\",\n" +
            "        \"Statement\": [\n" +
            "            {\n" +
            "                \"Sid\": \"IamListAccess\",\n" +
            "                \"Effect\": \"Allow\",\n" +
            "                \"Action\": [\n" +
            "                    \"iam:ListRoles\",\n" +
            "                    \"iam:ListUsers\"\n" +
            "                ],\n" +
            "                \"Resource\": \"**\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    private final String wrongStructureJsonString = "\n" +
            "    \"PolicyName\": \"root\",\n" +
            "    \"PolicyDocument\": {\n" +
            "        \"Version\": \"2012-10-17\",\n" +
            "        \"Statement\": [\n" +
            "            {\n" +
            "                \"Sid\": \"IamListAccess\",\n" +
            "                \"Effect\": \"Allow\",\n" +
            "                \"Action\": [\n" +
            "                    \"iam:ListRoles\",\n" +
            "                    \"iam:ListUsers\"\n" +
            "                ],\n" +
            "                \"Resource\": \"**\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
    private final String jsonStringWithMissingElement = "{\n" +
            "    \"PolicyName\": \"root\",\n" +
            "    \"PolicyDocument\": {\n" +
            "        \"Version\": \"2012-10-17\",\n" +
            "        \"Statement\": [\n" +
            "            {\n" +
            "                \"Sid\": \"IamListAccess\",\n" +
            "                \"Effect\": \"Allow\",\n" +
            "                \"Action\": [\n" +
            "                    \"iam:ListRoles\",\n" +
            "                    \"iam:ListUsers\"\n" +
            "                ],\n" +
            "                \n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
}



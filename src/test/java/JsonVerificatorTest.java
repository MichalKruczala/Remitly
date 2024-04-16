import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonVerificatorTest {

    @Test
    public void shouldReturnFalseIfFindSingleAsteriskInFieldResource() {
        JsonVerificator jV = new JsonVerificator();
        boolean value = jV.validateJson(jsonWithSingleAsterisk);
        Assertions.assertFalse(value);
    }
    @Test
    public void shouldReturnTrueIfNotSingleAsteriskInFieldResource(){
        JsonVerificator jV = new JsonVerificator();
        boolean value = jV.validateJson(jsonWithoutSingleAsterisk);
        Assertions.assertTrue(value);
    }
    private final String jsonWithSingleAsterisk = "{\n" +
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

    private final String jsonWithoutSingleAsterisk = "{\n" +
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



}




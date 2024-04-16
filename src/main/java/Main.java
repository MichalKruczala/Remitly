import exceptions.MissingElementInJsonException;
import exceptions.SingleAsteriskException;
import exceptions.WrongValueJsonException;
import org.json.JSONException;

public class Main {
    public static void main(String[] args) {

        JsonVerificator jsonVerificator = new JsonVerificator();
        FileReaderService frs = new FileReaderService();
        String jsonAsString = frs.readFileToString("src/main/resources/JSON.txt");
        try {
            jsonVerificator.validateJson(jsonAsString);
        } catch (JSONException e) {
            System.out.println("Wrong format JSON data provided");
        } catch (WrongValueJsonException w) {
            w.getMessage();
        } catch (SingleAsteriskException e) {
            e.getMessage();
        } catch (MissingElementInJsonException e) {
            e.getMessage();
        }
    }

    static String getJson1 = "{\n" +
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
            "}\n";
    static String getJson2 = "{\n" +
            "  \"Version\": \"2012-10-17\",\n" +
            "  \"Statement\": [\n" +
            "    {\n" +
            "      \"Sid\": \"FirstStatement\",\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Action\": [\"iam:ChangePassword\"],\n" +
            "      \"Resource\": \"*\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Sid\": \"SecondStatement\",\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Action\": \"s3:ListAllMyBuckets\",\n" +
            "      \"Resource\": \"*\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Sid\": \"ThirdStatement\",\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Action\": [\n" +
            "        \"s3:List*\",\n" +
            "        \"s3:Get*\"\n" +
            "      ],\n" +
            "      \"Resource\": [\n" +
            "        \"arn:aws:s3:::confidential-data\",\n" +
            "        \"arn:aws:s3:::confidential-data/*\"\n" +
            "      ],\n" +
            "      \"Condition\": {\"Bool\": {\"aws:MultiFactorAuthPresent\": \"true\"}}\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}



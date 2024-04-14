// Zapytaj o ściezkę do pliku
// otwórz plik Stream streamToJsonFile = read stream
// przeczytaj całość jako string
// String jsonAsString;
public class Main {
    public static void main(String[] args) {

        JsonWerificator jsonWerificator = new JsonWerificator();

        jsonWerificator.validateJson(jsonAsString);

    }
    static String jsonAsString = "{\n" +
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
}


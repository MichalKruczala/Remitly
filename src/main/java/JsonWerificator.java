import org.json.JSONArray;
import org.json.JSONObject;

public class JsonWerificator {

    DataValidator dataValidator = new DataValidator();
    boolean hasPolicyName;
    boolean hasPolicyDocument = false;
    boolean hasVersion = false;
    boolean hasStatement = false;

    public boolean validateJson(String jsonAsString) {
        JSONObject json = new JSONObject(jsonAsString);
        hasPolicyName = json.has("PolicyName");
        if (hasPolicyName) {
            String policyName = json.getString("PolicyName");
            verifyPolicyName(policyName);
        }
        hasPolicyName =json.has("PolicyDocument");
        if (hasPolicyDocument) {
            JSONObject policyDocument = json.getJSONObject("PolicyDocument");
            hasVersion = policyDocument.has("Version");
            if (hasVersion) {
                String version = policyDocument.getString("Version");
                verifyVersion(version);
            }
            hasStatement = policyDocument.has("Statement");
            if (hasStatement) {
                try {
                    JSONArray statementArray = policyDocument.getJSONArray("Statement");
                    if (!statementArray.isEmpty()) {
                        for (int i = 0; i < statementArray.length(); i++) {
                            JSONObject singleStatement = statementArray.getJSONObject(i);
                            boolean hasSid = singleStatement.has("Sid");
                            boolean hasEffect = singleStatement.has("Effect");
                            boolean hasAction = singleStatement.has("Action");
                            if (hasAction) {
                                JSONArray action = singleStatement.getJSONArray("Action");
                                if (!action.isEmpty()) {
                                    System.out.println(action); // Elementy z listy Action
                                }
                            }
                            boolean hasResource = singleStatement.has("Resource");

                            if (hasResource) {
                                String resource = singleStatement.getString("Resource");
                                if (resource.equals("*")) {
                                    throw new SingleAsteriskException();
                                }
                            }

                            System.out.println(singleStatement);
                            if (hasSid && hasEffect && hasAction && hasResource) {
                                System.out.println("Statement zawiera wszystkie oczekiwane pola.");
                            } else {
                                System.out.println("Statement nie zawiera wszystkich oczekiwanych pól.");
                            }

                        }

                    } else {
                        System.out.println(" nie ma żadnego statementu w JSONIE");
                    }
                } catch (SingleAsteriskException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    private void verifyVersion(String version) {
        try {
            if (!dataValidator.isValidVersion(version)) {
                throw new WrongInputDataJsonException();
            }
        } catch (WrongInputDataJsonException e) {
            System.out.println(e.getMessage("Wrong data in Version"));
        }
    }

    private void verifyPolicyName(String policyName) {
        try {
            if (!dataValidator.isValidPolicyName(policyName)) {
                throw new WrongInputDataJsonException();
            }
        } catch (WrongInputDataJsonException e) {
            System.out.println(e.getMessage("Wrong data in PolicyName"));
        }
    }
}
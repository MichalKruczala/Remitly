import exceptions.MissingElementInJsonException;
import exceptions.WrongValueJsonException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.function.Consumer;

public class AwsJsonValidator {

    DataValidator dataValidator = new DataValidator();

    public boolean validateJson(String jsonAsString) throws MissingElementInJsonException, WrongValueJsonException {

        JSONObject json = new JSONObject(jsonAsString);
        hasRequiredField(json, "PolicyName");
        dataValidator.validatePolicyName(json.getString("PolicyName"));

        hasRequiredField(json, "PolicyDocument");
        JSONObject policyDocument = json.getJSONObject("PolicyDocument");

        hasRequiredField(policyDocument, "Version");
        dataValidator.validateVersion(policyDocument.getString("Version"));

        hasRequiredField(policyDocument, "Statement");
        JSONArray statementArrayNew = policyDocument.getJSONArray("Statement");

        for (int i = 0; i < statementArrayNew.length(); i++) {
            JSONObject singleStatement = statementArrayNew.getJSONObject(i);
            hasRequiredField(singleStatement, "Sid");
            hasRequiredField(singleStatement, "Effect");
            dataValidator.validateEffect(singleStatement.getString("Effect"));
            hasRequiredField(singleStatement, "Action");
            hasRequiredField(singleStatement, "Resource");
            String resourceValue = singleStatement.getString("Resource");
            if (resourceValue.equals("*")) {
                return false;
            }

        }
        return true;
        /*if (hasPolicyDocument) {
            JSONObject policyDocument1 = json.getJSONObject("PolicyDocument");
            dataValidator.validatePolicyDocument(policyDocument.toString());
            hasVersion = policyDocument.has("Version");
            if (hasVersion) {
                String version = policyDocument.getString("Version");
                dataValidator.validateVersion(version);

            } else throw new MissingElementInJsonException("Missing Vesion");
            hasStatement = policyDocument.has("Statement");
            if (hasStatement) {
                JSONArray statementArray = policyDocument.getJSONArray("Statement");
                if (!statementArray.isEmpty()) {
                    for (int i = 0; i < statementArray.length(); i++) {
                        JSONObject singleStatement = statementArray.getJSONObject(i);
                        hasSid = singleStatement.has("Sid");
                        singleStatement.getString("Sid");
                        hasEffect = singleStatement.has("Effect");
                        if (hasEffect) {
                            String effect = singleStatement.getString("Effect");
                            dataValidator.validateEffect(effect);
                        } else throw new MissingElementInJsonException("Missing Effect");
                        hasAction = singleStatement.has("Action");
                        if (hasAction) {
                            singleStatement.getJSONArray("Action");
                        } else throw new MissingElementInJsonException("Missing Action");
                        hasResource = singleStatement.has("Resource");
                        if (hasResource) {
                            String resource = singleStatement.getString("Resource");
                            if (resource.equals("*")) {
                                return false;
                            }
                        } else throw new MissingElementInJsonException("Missing Resource");
                    }
                } else throw new MissingElementInJsonException("missing statement list of Objects");
            }
        } else throw new MissingElementInJsonException("Missing Policy Document");
        return true;*/
    }

    private void hasRequiredField(JSONObject json, String fieldName) throws MissingElementInJsonException {
        if (!json.has(fieldName)) {
            throw new MissingElementInJsonException("Luck of Field -" + fieldName);
        }
    }
}


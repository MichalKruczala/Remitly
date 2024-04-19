import exceptions.MissingElementInJsonException;
import exceptions.WrongValueJsonException;
import org.json.JSONArray;
import org.json.JSONObject;

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
    }

    private void hasRequiredField(JSONObject json, String fieldName) throws MissingElementInJsonException {
        if (!json.has(fieldName)) {
            throw new MissingElementInJsonException("Missing Field -" + fieldName);
        }
    }
}


import exceptions.MissingElementInJsonException;
import exceptions.SingleAsteriskException;
import exceptions.WrongValueJsonException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonVerificator {

    boolean hasPolicyName = false;
    boolean hasPolicyDocument = false;
    boolean hasVersion = false;
    boolean hasSid = false;
    boolean hasStatement = false;
    boolean hasEffect = false;
    boolean hasAction = false;
    boolean hasResource = false;
    DataValidator dataValidator = new DataValidator();

 //   Pozostałe pola, takie jak "Effect", "Action", "Resource", "Condition" (warunek),
     //       "Principal" (podmiot) itp., są opcjonalne i zależą od konkretnego zastosowania. Na przykład:
//Polityka IAM to dokument JSON z kluczami Sid, Effect, Principal, Action i Resource.


    public boolean validateJson(String jsonAsString) {
        try {
            JSONObject json = new JSONObject(jsonAsString);
            hasPolicyName = json.has("PolicyName");
            if (hasPolicyName) {
                String policyName = json.getString("PolicyName");
                verifyPolicyName(policyName);
            } else throw new MissingElementInJsonException("Luck of Policy Name");
            hasPolicyDocument = json.has("PolicyDocument");
            if (hasPolicyDocument) {
                JSONObject policyDocument = json.getJSONObject("PolicyDocument");
                hasVersion = policyDocument.has("Version");
                if (hasVersion) {
                    String version = policyDocument.getString("Version");
                    verifyVersion(version);
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
                                verifyEffect(effect);
                            } else throw new MissingElementInJsonException("Missing Effect");
                            hasAction = singleStatement.has("Action");
                            if (hasAction) {
                                singleStatement.getJSONArray("Action");
                            } else throw new MissingElementInJsonException("Missing Action");
                            hasResource = singleStatement.has("Resource");
                            if (hasResource) {
                                // JSONObject resourceObj = singleStatement.getJSONObject("Resource");
                                String resource = singleStatement.getString("Resource");
                                if (resource.equals("*")) {
                                    throw new SingleAsteriskException();
                                }
                            } else throw new MissingElementInJsonException("Missing Resource");
                        }
                    } else throw new MissingElementInJsonException("missing statement list of Objects");
                }
            } else throw new MissingElementInJsonException("Missing Policy Document");
            return true;
        } catch (JSONException e) {
            System.out.println("Wrong format JSON data provided");
        } catch (WrongValueJsonException w) {
            w.getMessage();
            //return?????
        } catch (SingleAsteriskException e) {
            e.getMessage();
            return false;
        } catch (MissingElementInJsonException e) {
            e.getMessage();
          // return???
        }
    }

    private void verifyVersion(String version) throws WrongValueJsonException {

        if (!dataValidator.isValidVersion(version)) {
            throw new WrongValueJsonException("Wrong data in Version");
        }
    }

    private void verifyPolicyName(String policyName) throws WrongValueJsonException {
        if (!dataValidator.isValidPolicyName(policyName)) {
            throw new WrongValueJsonException("Wrong data in PolicyName");
        }
    }

    private void verifyEffect(String effect) throws WrongValueJsonException {
        if (!dataValidator.isValidEffect(effect)) {
            throw new WrongValueJsonException("Wrong data in Effect");
        }
    }
}


import exceptions.MissingElementInJsonException;
import exceptions.WrongValueJsonException;
import org.json.JSONException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        try {
            args = new String[]{"/Users/michalkruczala/Documents/Projekty Java/Remitly/src/main/resources/JSON.json"};
            if (args.length != 1) {
                System.out.println("Path to file required");
                return;
            }
            String content = Files.readString(Path.of(args[0]));
            AwsJsonValidator awsJsonValidator = new AwsJsonValidator();

            System.out.println(awsJsonValidator.validateJson(content));
        } catch (JSONException e) {
            System.out.println("Wrong format JSON data provided");
        } catch (WrongValueJsonException e) {
            System.out.println("Wrong value" + e.getMessage());
        } catch (MissingElementInJsonException e) {
            System.out.println("Missing element" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



import java.io.*;

public class FileReaderService {
    public String readFileToString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                throw new IOException("Wrong path provided.");
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return contentBuilder.toString();
    }
}

package az.khayal.databaseexporter;

public class Exporter {

    public void backup(String dumpPath, String username, String pass, String dbName, String sqlPath) {
        Process p = null;

        try {
            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(
                    dumpPath + " -u" + username + " -p" + pass + " --add-drop-database -B " + dbName + " -r" + sqlPath);
            int completed = p.waitFor();
            if (completed == 0) {
                System.out.println("success export -- " + dbName);
            } else {
                System.out.println("failed -- " + dbName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

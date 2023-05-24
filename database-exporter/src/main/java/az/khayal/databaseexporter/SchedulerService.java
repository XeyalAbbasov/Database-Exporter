package az.khayal.databaseexporter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@EnableScheduling
@Service
public class SchedulerService {

    String username = "root";
    String pass = "1234";
    String env = "mysql";

    Exporter exporter = new Exporter();
    ReadDb readDatabases = new ReadDb();

    int counter = 0;

    @Scheduled(fixedDelay = 5000)
    public void backupDatabase() {
        List<String> dbNames = readDatabases.readDbNames(username, pass);

        dbNames.remove("sys");
        dbNames.remove("mysql");
        dbNames.remove("information_schema");
        dbNames.remove("performance_schema");

        System.out.println("this databases will be exported");
        System.out.println("---------------");
        System.out.println(dbNames);

        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        String dbFolderName = env + "_db_" + year + "_" + month + "_" + day + "_" + hour + "_" + minute + "_" + second;

        System.out.println("db export folder --- "+dbFolderName);
        System.out.println("---------------");

        String dumpPath = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe";

        File folder = new File("C:\\Exports\\" + dbFolderName);
        folder.mkdir();

        for (String s : dbNames) {
            exporter.backup(dumpPath, username, pass, s,
                    "C:\\Exports\\" + dbFolderName + "\\" + s + ".sql");
        }
        counter++;
        System.out.println("" + counter + " -  export has finished");
        System.out.println("---------------");
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

public class ReportsReader {
    final String filesDirectoryPath = "./resources";

    void readMonthlyReport(String path, MonthlyReport monthlyReport) {
        List<String> fileContents = readFileContents(path);
        //Вырезаем номер месяца из названия отчета, например: m.2021 (01) .csv
        String monthNumber = path.substring(path.indexOf("m.") + 6, path.length() - 4);
        monthlyReport.monthNumber = Integer.parseInt(monthNumber);
        monthlyReport.monthlyExpenses = new HashMap<>();
        monthlyReport.monthlyProfits = new HashMap<>();

        for (int line = 1; line < fileContents.size(); line++) {
            String[] lineContents = fileContents.get(line).split(",");
            String itemName = lineContents[0];
            String isExpense = lineContents[1];
            int quantity = Integer.parseInt(lineContents[2]);
            int price = Integer.parseInt(lineContents[3]);
            int[] quantityAndPrice = new int[2];
            quantityAndPrice[0] = quantity;
            quantityAndPrice[1] = price;

            if (isExpense.equals("TRUE")) {
                monthlyReport.monthlyExpenses.put(itemName, quantityAndPrice);
            } else {
                monthlyReport.monthlyProfits.put(itemName, quantityAndPrice);
            }
        }
    }

    void readYearlyReport(String path, YearlyReport yearlyReport) {
        List<String> fileContents = readFileContents(path);
        //Вырезаем номер года: y. (2021) .csv
        String reportDate = path.substring(path.indexOf("y.") + 2, path.length() - 4);
        yearlyReport.yearNumber = Short.parseShort(reportDate);
        yearlyReport.yearlyExpenses = new int[12];
        yearlyReport.yearlyProfits = new int[12];

        for (int line = 1; line < fileContents.size(); line++) {
            String[] lineContents = fileContents.get(line).split(",");
            byte month = Byte.parseByte(lineContents[0]);
            int amount = Integer.parseInt(lineContents[1]);
            boolean isExpense = Boolean.parseBoolean(lineContents[2]);

            if (isExpense) {
                yearlyReport.yearlyExpenses[month-1] = amount;
            } else {
                yearlyReport.yearlyProfits[month-1] = amount;
            }
        }
    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}

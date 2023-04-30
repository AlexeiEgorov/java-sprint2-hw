import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехаль!
        System.out.println("Бухгалтер Парка Развлечений");
        Scanner scanner = new Scanner(System.in);
        ReportsReader reportsReader = new ReportsReader();
        ParkAccountant parkAccountant = new ParkAccountant(reportsReader);

        while (true) {
            printMenu();
            String command = scanner.nextLine().trim();

            switch (command) {
                case ("1"):
                    parkAccountant.enterAllMonthlyReports();
                    break;
                case ("2"):
                    reportsReader.readYearlyReport(reportsReader.filesDirectoryPath + "/y.2021.csv", parkAccountant.yearlyReport);
                    break;
                case ("3"):
                    parkAccountant.checkMonthlyAndYearlyReports();
                    break;
                case ("4"):
                    parkAccountant.printAllMonthsStatistics();
                    break;
                case ("5"):
                    parkAccountant.printYearlyStatistics();
                    break;
                case ("Programmus-terminatus!"):
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверно набрана команда, повторите.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
    }
}


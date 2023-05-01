import java.io.File;

public class ParkAccountant {
    ReportsReader reportsReader;
    MonthlyReport[] monthlyReports = new MonthlyReport[3];
    YearlyReport yearlyReport;

    ParkAccountant(ReportsReader reportsReader) {
        this.reportsReader = reportsReader;
        yearlyReport = new YearlyReport();

        for (int i = 0; i < 3; i++) {
            monthlyReports[i] = new MonthlyReport();
        }
    }

    void enterAllMonthlyReports() {
        for (int i = 1; i <= 3; i++) {
            String fileName = "m.20210" + i + ".csv";
            String filePath = reportsReader.filesDirectoryPath + File.separator + fileName;
            reportsReader.readMonthlyReport(filePath, monthlyReports[i - 1]);
        }
    }

    void checkMonthlyAndYearlyReports() {
        if ((monthlyReports[0].monthlyProfits == null) || (yearlyReport.yearlyProfits == null)) {
            System.out.println("Ошибка: месячные или годовой отчёты ещё не были считаны.");
            return;
        }

        for (MonthlyReport month : monthlyReports) {

            if ( (month.sumExpenses() != yearlyReport.yearlyExpenses[month.monthNumber - 1]) ||
                  (month.sumProfits() != yearlyReport.yearlyProfits[month.monthNumber - 1]) ) {
                System.out.println("Ошибка: несоответствие между годовым и месячным отчётами, месяц: "
                        + month.monthNumber);
                return;
            }
        }

        System.out.println("Сверка месячных и годовых отчётов успешно завершена.");
    }

    void printAllMonthsStatistics() {
        for (MonthlyReport monthlyReport : monthlyReports) {
            if (monthlyReport.monthlyExpenses == null) {
                System.out.println("Ошибка: месячные отчёты ещё не были считаны.");
                return;
            }
            String mostExpensiveItemName = monthlyReport.mostExpensiveItem();
            String mostProfitableItemName = monthlyReport.mostProfitableItem();
            int mostExpensiveItemSum =
                    monthlyReport.getItemsPrice(monthlyReport.monthlyExpenses.get(mostExpensiveItemName));
            int mostProfitableItemSum =
                    monthlyReport.getItemsPrice(monthlyReport.monthlyProfits.get(mostProfitableItemName));

            System.out.println("Месяц " + monthlyReport.monthNumber);
            System.out.println("  Самый прибыльный товар: " + mostProfitableItemName + ", на сумму: "
                    + mostProfitableItemSum);
            System.out.println("  Самый большая трата за товар: " + mostExpensiveItemName + ", на сумму: "
                    + mostExpensiveItemSum);
        }
    }

    void printYearlyStatistics() {
        if (yearlyReport.yearlyProfits == null) {
            System.out.println("Ошибка: годовые отчёты ещё не были считаны.");
            return;
        }
        String averageExpenseForMonthsInYear = String.format("%.2f", yearlyReport.getAverageExpenseForMonthsInYear());
        String averageIncomeForMonthsInYear = String.format("%.2f", yearlyReport.getAverageIncomeForMonthsInYear());


        System.out.println("Год " + yearlyReport.yearNumber);
        for (int i = 1; i <= 12; i++) {
            System.out.println("  Прибыль за месяц " + i + " составляет: " + yearlyReport.getProfitPerMonth(i));
        }
        System.out.println("Средний расход за все месяцы в году: " + averageExpenseForMonthsInYear);
        System.out.println("Средний доход за все месяцы в году: " + averageIncomeForMonthsInYear);
    }
}

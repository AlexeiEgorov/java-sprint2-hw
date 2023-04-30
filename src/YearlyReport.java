public class YearlyReport {
    short yearNumber;
    int[] yearlyExpenses;
    int[] yearlyProfits;

    int getProfitPerMonth(int month) {
        int profit = yearlyProfits[month-1] - yearlyExpenses[month-1];

        return profit;
    }

    double getAverageExpenseForMonthsInYear() {
        double averageExpenseForMonthsInYear = 0;

        for (int expense : yearlyExpenses) {
            averageExpenseForMonthsInYear += expense;
        }

        averageExpenseForMonthsInYear /= 12;

        return averageExpenseForMonthsInYear;
    }

    double getAverageIncomeForMonthsInYear() {
        double averageIncomeForMonthsInYear = 0;

        for (int income : yearlyProfits) {
            averageIncomeForMonthsInYear += income;
        }

        averageIncomeForMonthsInYear /= 12;

        return averageIncomeForMonthsInYear;
    }


}

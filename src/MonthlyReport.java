import java.util.HashMap;

public class MonthlyReport {
    int monthNumber;
    HashMap<String, int[]> monthlyExpenses;
    HashMap<String, int[]> monthlyProfits;

    int getItemsPrice(int[] quantityAndPrice) {
        return quantityAndPrice[0] * quantityAndPrice[1];
    }

    int sumExpenses() {
        int sumExpenses = 0;
        for (int[] expenseQuantityAndPrice : monthlyExpenses.values()) {
            sumExpenses += getItemsPrice(expenseQuantityAndPrice);
        }

        return sumExpenses;
    }

    int sumProfits() {
        int sumProfits = 0;
        for (int[] profitQuantityAndPrice : monthlyProfits.values()) {
            sumProfits += getItemsPrice(profitQuantityAndPrice);
        }

        return sumProfits;
    }

    String mostExpensiveItem() {
        String mostExpensiveItem = null;
        int maxExpense = 0;
        int currentExpense;

        for (String expenseItem : monthlyExpenses.keySet()) {
            currentExpense = getItemsPrice(monthlyExpenses.get(expenseItem));

            if (currentExpense > maxExpense) {
                maxExpense = currentExpense;
                mostExpensiveItem = expenseItem;
            }
        }

        return mostExpensiveItem;
    }

    String mostProfitableItem() {
        String mostProfitableItem = null;
        int maxProfit = 0;
        int currentProfit;

        for (String profitItem : monthlyProfits.keySet()) {
            currentProfit = getItemsPrice(monthlyProfits.get(profitItem));

            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
                mostProfitableItem = profitItem;
            }
        }

        return mostProfitableItem;
    }

}

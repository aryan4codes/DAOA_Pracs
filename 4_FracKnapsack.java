import java.util.*;

class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

class FractionalKnapsack {
    public static double getMaxValue(int[] weights, int[] values, int capacity) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) {
            items.add(new Item(weights[i], values[i]));
        }

        // Sort items by value per unit weight in non-ascending order
        Collections.sort(items, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        double totalValue = 0;
        int remainingCapacity = capacity;

        for (Item item : items) {
            if (remainingCapacity >= item.weight) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                // Take a fraction of the item
                double fraction = (double) remainingCapacity / item.weight;
                totalValue += item.value * fraction;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;
        double maxValue = getMaxValue(weights, values, capacity);
        System.out.println("Maximum value that can be obtained = " + maxValue);
    }
}

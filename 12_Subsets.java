import java.util.ArrayList;
import java.util.List;

class SumOfSubsets {

    public static void findSubsets(int[] nums, int target) {
        List<Integer> currentSubset = new ArrayList<>();
        findSubsets(nums, target, 0, currentSubset);
    }

    private static void findSubsets(int[] nums, int target, int index, List<Integer> currentSubset) {
        if (target == 0) {
            System.out.println(currentSubset);
            return;
        }

        if (target < 0 || index == nums.length) {
            return;
        }

        // Include the current element
        currentSubset.add(nums[index]);
        findSubsets(nums, target - nums[index], index + 1, currentSubset);

        // Exclude the current element
        currentSubset.remove(currentSubset.size() - 1);
        findSubsets(nums, target, index + 1, currentSubset);
    }

    public static void main(String[] args) {
        int[] nums = {10, 7, 5, 18, 12, 20, 15};
        int target = 35;
        System.out.println("Subsets that sum up to " + target + " are:");
        findSubsets(nums, target);
    }
}

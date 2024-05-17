
import java.util.Arrays;
import java.util.Comparator;

class Job {
    int id;      // Job ID
    int deadline; // Deadline of job
    int profit;  // Profit if job is completed before or on deadline

    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class JobScheduling {
    
    // Function to schedule jobs and maximize profit
    public static void jobScheduling(Job[] jobs, int n) {
        // Sort jobs according to descending order of profit
        Arrays.sort(jobs, new Comparator<Job>() {
            public int compare(Job a, Job b) {
                return b.profit - a.profit;
            }
        });

        // To keep track of free time slots
        boolean[] result = new boolean[n];
        // To store result (Sequence of jobs)
        int[] jobSequence = new int[n];

        // Initialize all time slots to be free
        for (int i = 0; i < n; i++) {
            result[i] = false;
        }

        // Iterate through all given jobs
        for (int i = 0; i < jobs.length; i++) {
            // Find a free slot for this job (note that we start from the last possible slot)
            for (int j = Math.min(n - 1, jobs[i].deadline - 1); j >= 0; j--) {
                // Free slot found
                if (!result[j]) {
                    result[j] = true;
                    jobSequence[j] = i;
                    break;
                }
            }
        }

        // Print the result
        System.out.println("Following is the maximum profit sequence of jobs:");
        int totalProfit = 0;
        for (int i = 0; i < n; i++) {
            if (result[i]) {
                System.out.print("Job" + jobs[jobSequence[i]].id + " ");
                totalProfit += jobs[jobSequence[i]].profit;
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job(1, 4, 20),
            new Job(2, 1, 10),
            new Job(3, 1, 40),
            new Job(4, 1, 30)
        };

        int n = 4; // Number of time slots
        jobScheduling(jobs, n);
    }
}

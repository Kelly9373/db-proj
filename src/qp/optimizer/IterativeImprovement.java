package qp.optimizer;

import qp.operators.Operator;
import qp.utils.SQLQuery;

public class IterativeImprovement extends RandomOptimizer{
    /**
     * Constructor
     *
     * @param sqlQuery is the SQL query to be optimized.
     */
    public IterativeImprovement(SQLQuery sqlQuery) {
        super(sqlQuery);
    }

    /**
     * Implements iterative improvement algorithm
     *
     * @return an optimized plan
     */
    @Override
    public Operator getOptimizedPlan() {
        RandomInitialPlan rip = new RandomInitialPlan(sqlquery);
        numJoin = rip.getNumJoins();
        Operator finalPlan = null;
        int finalCost = Integer.MAX_VALUE;
        int optimizationNum = 0;

        if (numJoin == 0) {
            finalPlan = rip.prepareInitialPlan();
            printPlanCost("Final Plan", finalPlan);
            return finalPlan;
        }

        for (int j = 0; j < 3 * numJoin && optimizationNum < 10; j++) {
            Operator initPlan = rip.prepareInitialPlan();
            RandomOptimizer.modifySchema(initPlan);
            int initCost = printPlanCost("Initial Plan", initPlan);
            boolean notMin = true;

            while (notMin) {
                Operator minNeighborPlan = initPlan;
                int minNeighborCost = initCost;
                System.out.println("---------------while---------------");

                for (int i = 0; i < 2 * numJoin; i++) {
                    Operator initPlanCopy = (Operator) initPlan.clone();
                    Operator neighbor = getNeighbor(initPlanCopy);
                    int neighborCost = printPlanCost("Neighbor", neighbor);

                    if (neighborCost < minNeighborCost) {
                        minNeighborPlan = neighbor;
                        minNeighborCost = neighborCost;
                    }
                }

                if (minNeighborCost < initCost) {
                    initPlan = minNeighborPlan;
                    initCost = minNeighborCost;
                } else {
                    notMin = false;
                }
            }

            printPlanCost("Local Minimum", initPlan, initCost);
            if (initCost < finalCost) {
                finalPlan = initPlan;
                finalCost = initCost;
                optimizationNum++;
            }
        }

        printPlanCost("Final Plan from Iterative Improvement", finalPlan, finalCost);
        return finalPlan;
    }

    ///**
    //* Implementation of Iterative Improvement Algorithm for Randomized optimization of Query Plan
    //**/
    /*
    public Operator getOptimizedPlan() {
        ///** get an initial plan for the given sql query **/
    /*
        RandomInitialPlan rip = new RandomInitialPlan(sqlquery);
        numJoin = rip.getNumJoins();
        long MINCOST = Long.MAX_VALUE;
        Operator finalPlan = null;

        ///** NUMITER is number of times random restart **/
    /*
        int NUMITER;
        if (numJoin != 0) {
            NUMITER = 2 * numJoin;
        } else {
            NUMITER = 1;
        }

        ///** Randomly restart the gradient descent until
         //*  the maximum specified number of random restarts (NUMITER)
         //*  has satisfied
         //**/
    /*
        for (int j = 0; j < NUMITER; ++j) {
            Operator initPlan = rip.prepareInitialPlan();
            modifySchema(initPlan);
            System.out.println("-----------initial Plan-------------");
            Debug.PPrint(initPlan);
            PlanCost pc = new PlanCost();
            long initCost = pc.getCost(initPlan);
            System.out.println(initCost);

            boolean flag = true;
            long minNeighborCost = initCost;   //just initialization purpose;
            Operator minNeighbor = initPlan;  //just initialization purpose;
            if (numJoin != 0) {
                while (flag) {  // flag = false when local minimum is reached
                    System.out.println("---------------while--------");
                    Operator initPlanCopy = (Operator) initPlan.clone();
                    minNeighbor = getNeighbor(initPlanCopy);

                    System.out.println("--------------------------neighbor---------------");
                    Debug.PPrint(minNeighbor);
                    pc = new PlanCost();
                    minNeighborCost = pc.getCost(minNeighbor);
                    System.out.println("  " + minNeighborCost);

                    ///** In this loop we consider from the
                     //** possible neighbors (randomly selected)
                     //** and take the minimum among for next step
                     //**/
    /*
                    for (int i = 1; i < 2 * numJoin; ++i) {
                        initPlanCopy = (Operator) initPlan.clone();
                        Operator neighbor = getNeighbor(initPlanCopy);
                        System.out.println("------------------neighbor--------------");
                        Debug.PPrint(neighbor);
                        pc = new PlanCost();
                        long neighborCost = 0;
                        try {
                            neighborCost = pc.getCost(neighbor);
                        } catch (Exception e) {
                            System.out.println("fatal error.");
                            System.exit(0);
                        }
                        System.out.println(neighborCost);

                        if (neighborCost < minNeighborCost) {
                            minNeighbor = neighbor;
                            minNeighborCost = neighborCost;
                        }
                    }
                    if (minNeighborCost < initCost) {
                        initPlan = minNeighbor;
                        initCost = minNeighborCost;
                    } else {
                        minNeighbor = initPlan;
                        minNeighborCost = initCost;
                        flag = false;  // local minimum reached
                    }
                }
                System.out.println("------------------local minimum--------------");
                Debug.PPrint(minNeighbor);
                System.out.println(" " + minNeighborCost);
            }
            if (minNeighborCost < MINCOST) {
                MINCOST = minNeighborCost;
                finalPlan = minNeighbor;
            }
        }
        System.out.println("\n\n\n");
        System.out.println("---------------------------Final Plan----------------");
        Debug.PPrint(finalPlan);
        System.out.println("  " + MINCOST);
        return finalPlan;
    }*/
}

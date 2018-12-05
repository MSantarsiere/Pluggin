/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.prioritization.runner;

import it.unisa.prioritization.algorithm.AdditionalGreedyPrioritization;
import it.unisa.prioritization.criterion.CoverageMatrix;
import it.unisa.prioritization.criterion.CumulativeCoverage;
import it.unisa.prioritization.criterion.ExecutionCostVector;
import it.unisa.prioritization.problems.GenericPrioritizationProblem;
import it.unisa.prioritization.problems.SingleObjectivePrioritizationProblem;
import it.unisa.prioritization.qualityIndicators.AFDPc;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.solution.PermutationSolution;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;

/**
 *
 * @author Rembor
 */
public class AS {
     public static void matrixce() throws InterruptedException, IOException{
          String outputFolder ="C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto";
     String file ="C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\matrice.csv";
     String file1= "C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\matricecosti.csv";
     String file2= "C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\matricefault.csv";
     CoverageMatrix a= new CoverageMatrix(file,false);
         CumulativeCoverage b=new CumulativeCoverage(a);
    System.out.println(     b.getMaxCoverage());
    ExecutionCostVector c=new ExecutionCostVector(file1);
     System.out.println(   c.getMaxCost());
     Algorithm algorithm = null;
                             List<String> coverageFilenames = new ArrayList<>();
                             coverageFilenames.add("C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\matrice.csv");
    GenericPrioritizationProblem problem =new SingleObjectivePrioritizationProblem(
                        coverageFilenames,
                      file1,
                       file2,
                        false);
    

                algorithm = new AdditionalGreedyPrioritization(problem);
                algorithm.run();
                PermutationSolution<Integer>asd=(PermutationSolution<Integer>) algorithm.getResult();
                System.out.print(asd);
                 long startTime = new Date().getTime();

                Thread algorithmThread = new Thread(algorithm);
                algorithmThread.start();

                algorithmThread.join();

                List<PermutationSolution<Integer>> population = new ArrayList<>();

                if (algorithm.getResult() instanceof PermutationSolution) {
                    population.add((PermutationSolution<Integer>) algorithm.getResult());
                } else if (algorithm.getResult() instanceof List) {
                    population.addAll((List<PermutationSolution<Integer>>) algorithm.getResult());
                }

                long finishTime = new Date().getTime();

                long computingTime = finishTime - startTime;

                JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

             

                new SolutionListOutput(population)
                        .setSeparator(",")
                        .setVarFileOutputContext(new DefaultFileOutputContext(outputFolder + "\\VAR" +".txt"))
                        .setFunFileOutputContext(new DefaultFileOutputContext(outputFolder + "\\FUN.1.tsv"))
                        .print();

                printAFDPc(problem, population, outputFolder, 1);
                printTime(computingTime, outputFolder, 1);
            }
  private static void printAFDPc(GenericPrioritizationProblem problem, List<PermutationSolution<Integer>> population,
                                   String outputFolder, int run) throws IOException {
        BufferedWriter afdpcBW = new DefaultFileOutputContext(outputFolder + "AFDP." + run).getFileWriter();
        List<Double> afdpcList = new AFDPc(problem).evaluate(population);

        for (double afdpc : afdpcList) {
            afdpcBW.append(String.valueOf(afdpc)).append("\n");
        }
        afdpcBW.close();
    }

    private static void printTime(double time, String outputFolder, int run) throws IOException {
        BufferedWriter timeBW = new DefaultFileOutputContext(outputFolder + "TIME." + run).getFileWriter();
        timeBW.append(String.valueOf(time));
        timeBW.close();
    }

    
     }
     


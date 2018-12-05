/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.plug.dado;
import cern.colt.matrix.impl.SparseDoubleMatrix1D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import cern.jet.math.PlusMult;

import javax.management.JMException;
import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dario Di Nucci
 */
public class MatrixCompactionUtility {

    public static void main(String[] args) throws JMException, IOException {
        String coverageFilename = "C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\matrice.csv";
        String path = "C:\\Users\\Rembor\\Documents\\NetBeansProjects\\progetto\\matrice.csv";
        MatrixCompactionUtility mcu = new MatrixCompactionUtility();
        SparseDoubleMatrix2D coltMatrix = mcu.readMatrixData(coverageFilename);
        int originalColumnsNum = coltMatrix.columns();
        coltMatrix = mcu.compactSparseMatrix(coltMatrix);
        int compactedColumnsNum = coltMatrix.columns();
        mcu.saveMatrix(coltMatrix, originalColumnsNum, compactedColumnsNum, path);
    }

    /**
     * This method reads the coverage matrix from ASCII file
     *
     * @param coverageFilename name (+ full path) of the file containing the
     *                         coverage matrix
     * @return return a {@link SparseDoubleMatrix2D} encapsulating the coverage
     * matrix
     * @throws IOException
     */
    private SparseDoubleMatrix2D readMatrixData(String coverageFilename) throws IOException {
        if (coverageFilename == null) {
            throw new IllegalArgumentException();
        }
        BufferedReader coverageBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(coverageFilename)));
        int row = 0;
        int column = 0;
        String line;
        while ((line = coverageBufferedReader.readLine()) != null) {
            row++;
            column = line.split(" ").length;
        }
        coverageBufferedReader.close();
        double[][] coverageMatrix = new double[row][column];

        coverageBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(coverageFilename)));

        row = 0;
        while ((line = coverageBufferedReader.readLine()) != null) {
            column = 0;
            StringTokenizer st = new StringTokenizer(line, " ");

            while (st.hasMoreTokens()) {
                coverageMatrix[row][column] = Integer.parseInt(st.nextToken());
                column++;
            }
            row++;
        }

        return new SparseDoubleMatrix2D(coverageMatrix);
    }

    /**
     * This methods applied a compacting algorithm to the coverage matrix
     *
     * @param matrix
     * @return compacted matrix
     */
    private SparseDoubleMatrix2D compactSparseMatrix(SparseDoubleMatrix2D matrix) {
        int i = 0;
        int new_column = 0;
        boolean[] remove = new boolean[matrix.columns()];
        for (int index = 0; index < remove.length; index++) {
            remove[index] = false;
        }
        while (i < matrix.columns()) {
            if (!remove[i]) {
                SparseDoubleMatrix1D cc = new SparseDoubleMatrix1D(matrix.rows());
                cc.assign(matrix.viewColumn(i));
                SparseDoubleMatrix1D matrixi = (SparseDoubleMatrix1D) matrix.viewColumn(i);
                for (int j = i + 1; j < matrix.columns(); j++) {
                    SparseDoubleMatrix1D matrixj = (SparseDoubleMatrix1D) matrix.viewColumn(j);
                    if (matrixi.equals(matrixj)) {
                        cc = (SparseDoubleMatrix1D) cc.assign(matrixj, PlusMult.plusMult(1));
                        remove[j] = true;
                    }
                }
                matrix.viewColumn(i).assign(cc);
                new_column++;
            }
            i++;
        }
        SparseDoubleMatrix2D compacted = new SparseDoubleMatrix2D(matrix.rows(), new_column);
        i = 0;
        for (int index = 0; index < remove.length; index++) {
            if (!remove[index]) {
                compacted.viewColumn(i).assign(matrix.viewColumn(index));
                i++;
            }
        }
        return compacted;
    }

    private void saveMatrix(SparseDoubleMatrix2D coltMatrix, int originalColumnsNum, int compactedColumnsNum, String path) {
        PrintWriter pw = null;
        try {
            File f = new File(path);
            pw = new PrintWriter(f);
            pw.write(originalColumnsNum + "\n");
            pw.write(compactedColumnsNum + "\n");
            for (int i = 0; i < coltMatrix.rows(); i++) {
                for (int j = 0; j < coltMatrix.columns(); j++) {
                    pw.write((int) coltMatrix.get(i, j) + " ");
                }
                pw.write("\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MatrixCompactionUtility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

}

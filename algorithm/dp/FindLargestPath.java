package Algorithms.algorithm.dp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FindLargestPath {
    public static void main(String[] args) throws IOException {
        /*
         * Example matrix:
            ----------
            | 2 | -3 |
            ----------
            | 0 |  1 |
            ----------
             
            Inputmatrix file:
             
            2 -3
            0 1
             
            Output:
             
            -3
            2 -> 0 -> 1
         * */
        int[][] matrix = new int[2][2];
        matrix[0][0] = 2;
        matrix[0][1] = -3;
        matrix[1][0] = 0;
        matrix[1][1] = 1;
        
        int[][] matrix2 = new int[3][3];
        matrix2[0][0] = 2;
        matrix2[0][1] = -3;
        matrix2[0][2] = 9;
        
        matrix2[1][0] = 8;
        matrix2[1][1] = 0;
        matrix2[1][2] = 300;
        
        matrix2[2][0] = 3;
        matrix2[2][1] = 9;
        matrix2[2][2] = 2;
        
        findLargstPath(matrix);
        System.out.println();
        findLargstPath(matrix2);
        findLargstPathMain("input.txt");
        
        LinkedList<Integer> list = new LinkedList<Integer>();
    }
    
    /*
     * The first question.
     * */
    public static void findLargstPathMain(String fileName) throws IOException {
        // This will reference one line at a time
        String line = null;

        List<String> inputStrings = new ArrayList<String>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                inputStrings.add(line);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            // Or we could just do this:
            ex.printStackTrace();
        }
        
        System.out.println( inputStrings);

    }
    
    public static void findLargstPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int[][] D = new int[rows][cols];
        
        ArrayList<ArrayList<ArrayList<Integer>>> path = new ArrayList<ArrayList<ArrayList<Integer>>>();
        
        for (int i = 0; i < rows; i++) {
            ArrayList<ArrayList<Integer>> row = new ArrayList<ArrayList<Integer>>();
            path.add(row);
            for (int j = 0; j < cols; j++) {
                ArrayList<Integer> element = new ArrayList<Integer>();
                row.add(element);
            }
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                D[i][j] = Integer.MIN_VALUE;
                if (matrix[i][j] < 0) {
                    // can't go through.
                    // don't do the path.
                    continue;
                }
                
                // The path from the last line;
                int upPath = i == 0 ? Integer.MIN_VALUE: D[i - 1][j];
                
                // The path from the last column;
                int leftPath = j == 0 ? Integer.MIN_VALUE: D[i][j - 1];
                
                int sum = Math.max(upPath, leftPath);
                
                if (i == 0 && j == 0) {
                    ArrayList<Integer> node = new ArrayList<Integer>();
                    node.add(matrix[i][j]);
                    path.get(i).set(j, node);
                } else if (sum != Integer.MIN_VALUE) {
                    if (upPath > leftPath) {
                        // come from the up side.
                        ArrayList<Integer> node = new ArrayList<Integer>(path.get(i - 1).get(j));
                        node.add(matrix[i][j]);
                        path.get(i).set(j, node);
                    } else {
                        // come from the left side.
                        ArrayList<Integer> node = new ArrayList<Integer>(path.get(i).get(j - 1));
                        node.add(matrix[i][j]);
                        path.get(i).set(j, node);
                    }
                }
                
                if (i == 0 && j == 0) {
                    sum = matrix[i][j];
                } else {
                    if (sum == Integer.MIN_VALUE) {
                        // can't go through.
                        continue;
                    }
                    
                    sum += matrix[i][j];
                }
                
                // add the up negative.
                if (i > 0 && matrix[i - 1][j] < 0) {
                    sum += matrix[i - 1][j];
                }
                
                // add the down negative.
                if (i < rows - 1 && matrix[i + 1][j] < 0) {
                    sum += matrix[i + 1][j];
                }
                
                // add the left negative.
                if (j > 0 && matrix[i][j - 1] < 0) {
                    sum += matrix[i][j - 1];
                }
                
                // add the right negative.
                if (j < cols - 1 && matrix[i][j + 1] < 0) {
                    sum += matrix[i][j + 1];
                }
                
                D[i][j] = sum;
            }
        }
        
        System.out.println(D[rows - 1][cols - 1]);
        
        ArrayList<Integer> ret = path.get(rows - 1).get(cols - 1);
        for (int num: ret) {
            System.out.print(num + " ");
        }
    }
    
    /*
     * The bonus question:
     * */
    public static void findLargstPathBonux(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int[][] D = new int[rows][cols];
        
        ArrayList<ArrayList<ArrayList<Integer>>> path = new ArrayList<ArrayList<ArrayList<Integer>>>();
        
        for (int i = 0; i < rows; i++) {
            ArrayList<ArrayList<Integer>> row = new ArrayList<ArrayList<Integer>>();
            path.add(row);
            for (int j = 0; j < cols; j++) {
                ArrayList<Integer> element = new ArrayList<Integer>();
                row.add(element);
            }
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                D[i][j] = Integer.MIN_VALUE;
                if (matrix[i][j] < 0) {
                    // can't go through.
                    // don't do the path.
                    continue;
                }
                
                // The path from the last line;
                int upPath = i == 0 ? Integer.MIN_VALUE: D[i - 1][j];
                
                // The path from the last column;
                int leftPath = j == 0 ? Integer.MIN_VALUE: D[i][j - 1];
                
                int sum = Math.max(upPath, leftPath);
                
                if (i == 0 && j == 0) {
                    ArrayList<Integer> node = new ArrayList<Integer>();
                    node.add(matrix[i][j]);
                    path.get(i).set(j, node);
                } else if (sum != Integer.MIN_VALUE) {
                    if (upPath > leftPath) {
                        // come from the up side.
                        ArrayList<Integer> node = new ArrayList<Integer>(path.get(i - 1).get(j));
                        node.add(matrix[i][j]);
                        path.get(i).set(j, node);
                    } else {
                        // come from the left side.
                        ArrayList<Integer> node = new ArrayList<Integer>(path.get(i).get(j - 1));
                        node.add(matrix[i][j]);
                        path.get(i).set(j, node);
                    }
                }
                
                if (i == 0 && j == 0) {
                    sum = matrix[i][j];
                } else {
                    if (sum == Integer.MIN_VALUE) {
                        // can't go through.
                        continue;
                    }
                    
                    sum += matrix[i][j];
                }
                
                // add the up negative.
                if (i > 0 && matrix[i - 1][j] < 0) {
                    sum += matrix[i - 1][j];
                }
                
                // add the down negative.
                if (i < rows - 1 && matrix[i + 1][j] < 0) {
                    sum += matrix[i + 1][j];
                }
                
                // add the left negative.
                if (j > 0 && matrix[i][j - 1] < 0) {
                    sum += matrix[i][j - 1];
                }
                
                // add the right negative.
                if (j < cols - 1 && matrix[i][j + 1] < 0) {
                    sum += matrix[i][j + 1];
                }
                
                D[i][j] = sum;
            }
        }
        
        System.out.println(D[rows - 1][cols - 1]);
        
        ArrayList<Integer> ret = path.get(rows - 1).get(cols - 1);
        for (int num: ret) {
            System.out.print(num + " ");
        }
    }
}

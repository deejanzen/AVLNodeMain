/**
 * Dustin Janzen
 * sp16 345
 * project 3
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AVLNodeMain {

    public static void main(String [] args){
        //TODO swap these two
        //File inputFile = new File(args[0]);
        File inputFile = new File("input");
        //String inputFile = "input";

        Scanner fileScan;

        AVLNode root = new AVLNode(null);
        int dot = 0;
        FileWriter writer;
        try {
            fileScan = new Scanner(inputFile);


            while(fileScan.hasNext()){
                //p = inO P = preO
                String cmd = fileScan.next();
                switch(cmd){
                    case "p": root.inOrderTraversal();break;
                    case "P": root.preOrderTraversal();break;
                    case "s": String toFind = fileScan.next();
                              if (root.find(toFind) != null)
                                  System.out.println("\'" + toFind +"\' found.");
                              else
                                  System.out.println("\'" + toFind +"\' NOT found.");
                              break;
                    case "i": String toInsert = fileScan.next();
                              int fnd = root.insert(toInsert);
                              if (fnd > -1) {
                                  System.out.println("\'" + toInsert + "\' inserted.");
                                  String dotFileNameI = String.format("AVL.%04d", dot);
                                  dot+=1;
                                  try {
                                      writer = new FileWriter(new File(dotFileNameI));
                                      writer.write(root.toDotFile(true));
                                      writer.close();

                                  }catch (IOException e){
                                      System.out.print("No FileWriter file");
                                  }
                              }
                              else
                                  System.out.println("\'" + toInsert +"\' could not be inserted, because it was already in the AVL tree.");

                              break;
                    case "d": String toDelete = fileScan.next();
                              int del = root.remove(toDelete);
                              if (del > 0) {
                                  System.out.println("\'" + toDelete + "\' deleted.");
                                  String dotFileNameD = String.format("AVL.%04d", dot);
                                  dot+=1;
                                  try {
                                      writer = new FileWriter(new File(dotFileNameD));
                                      writer.write(root.toDotFile(true));
                                      writer.close();

                                  }catch (IOException e){
                                      System.out.print("No FileWriter file");
                                  }
                              }
                              else
                                  System.out.println("\'" + toDelete +"\' could not be deleted, because it was not in the AVL tree.");

                              break;
                    default:System.out.println("NOP");
                }
            }

        }catch(Exception io){
            io.printStackTrace();
        }




    }//end main
}


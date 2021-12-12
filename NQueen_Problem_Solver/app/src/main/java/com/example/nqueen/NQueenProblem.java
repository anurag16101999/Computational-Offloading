package com.example.nqueen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class NQueenProblem {
    int N;
    Context context;

    public NQueenProblem(int parseInt, Context context) {
        N = parseInt;
        this.context = context;
    }

    void printSolution(int[][] board)
    {
        TextView t;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j]!=0){
                    int id = Integer.parseInt(String.valueOf(i+1)+String.valueOf(j+1));
                    t = ((Activity)context).findViewById(id);

//                    ColorDrawable cd = (ColorDrawable) t.getBackground();
//                    int colorCode = cd.getColor();
//
//                    if(colorCode==Color.WHITE){
//                        t.setCompoundDrawablesWithIntrinsicBounds(
//                                ContextCompat.getDrawable(context, R.drawable.blackqueen),
//                                null,
//                                null,
//                                null);
//                    }
//                    else{
//                        t.setCompoundDrawablesWithIntrinsicBounds(
//                                ContextCompat.getDrawable(context, R.drawable.whitequeen),
//                                null,
//                                null,
//                                null);
//                    }

                    t.setBackgroundColor(Color.parseColor("#FF00FF"));


                    //the suitable place for queen is shown by yellow color
                }
            }
        }
        endTime = System.nanoTime();
        Toast.makeText(context, "Time taken = "+(endTime - startTime)/1000000 +"ms", Toast.LENGTH_LONG).show();
    }

    /* A utility function to check if a queen can
       be placed on board[row][col]. Note that this
       function is called when "col" queens are already
       placeed in columns from 0 to col -1. So we need
       to check only left side for attacking queens */
    boolean isSafe(int[][] board, int row, int col)
    {
        int i, j;

        /* Check this row on left side */
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    //A recursive utility function to solve N Queen problem
    boolean solveNQUtil(int[][] board, int col)
    {
        /* base case: If all queens are placed
           then return true */
        if (col >= N)
            return true;

        /* Consider this column and try placing
           this queen in all rows one by one */
        for (int i = 0; i < N; i++)
        {
            /* Check if the queen can be placed on
               board[i][col] */
            if (isSafe(board, i, col))
            {
                /* Place this queen in board[i][col] */
                board[i][col] = 1;

                /* recur to place rest of the queens */
                if (solveNQUtil(board, col + 1))
                    return true;

                /* If placing queen in board[i][col]
                   doesn't lead to a solution then
                   remove queen from board[i][col] */
                board[i][col] = 0; // BACKTRACK
            }
        }

        /* If the queen can not be placed in any row in
           this colum col, then return false */
        return false;
    }

    /* This function solves the N Queen problem using
       Backtracking.  It mainly uses solveNQUtil () to
       solve the problem. It returns false if queens
       cannot be placed, otherwise, return true and
       prints placement of queens in the form of 1s.
       Please note that there may be more than one
       solutions, this function prints one of the
       feasible solutions.*/
    long startTime;
    long endTime;

    boolean solveNQ()
    {
        startTime = System.nanoTime();
        int[][] board = new int[N][N];
        for (int a = 0;a<N;a++){
            for (int b=0;b<N;b++){
                board[a][b]=0;
            }
        }

        if (!solveNQUtil(board, 0)) {
            return false;
        }
        else{
            printSolution(board);
        }
        return true;
    }
}
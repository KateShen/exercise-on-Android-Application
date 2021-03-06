package com.example.u5778016.calculator;

/**
 * Author:Yu Shen (u5778016)
 * File: ReStack.java
 * Description: push and pop the data in stack
 */
public class ReStack {
    private int maxsize;
    private char[] charnum;
    private int thefirstone;

    // create a stack constructor
    public ReStack(int s){
        maxsize = s;
        charnum = new char[s];
        thefirstone = -1;
    }

    public ReStack(){
        charnum = new char[maxsize];
        thefirstone = -1;
    }
    //push the first number in
    public void push(char c){
        charnum[++thefirstone] = c;
    }
    //pop the first number out

    public char pop(){
        return charnum[thefirstone--];
    }

    public char peek(){
        return charnum[thefirstone];
    }

    public boolean isEmpty(){
        return thefirstone == -1;
    }

    public boolean isFull() {
        return thefirstone == (maxsize - 1);
    }

    public int size() {
        return thefirstone + 1;
    }

    public char get(int index) {
        return charnum[index];
    }

}

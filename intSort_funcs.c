/*                          J + M + J
File: intSort_funcs
Author: Joseph Gauthier
Purpose: Provides the functions used by the intSort_Main file
*/
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "proj07_intList.h"


/*                          +
Creates a new IntList list by usering user input 

@param fp: The file to pull usr's input from

@return head: The head of the new IntList
*/
IntList *readInput(FILE *fp) {

    // Check if the file is empty
    int c = fgetc(fp);
    if (c == EOF) {
        return NULL; 
    } else {
        ungetc(c, fp);  // Put the input back for next read


        // Malloc and intialize the head
        IntList *head = malloc(sizeof(IntList));
        if (head== NULL) {
            fprintf(stderr, "ERROR: Out of memory.");
            exit(1);
        }        
        head->val = 0;
        head->next = NULL;

        // Begin loop through user's input
        int currNum;
        int scanErr;
        while (1) {
            
            // Read in the user's current number
            scanErr = fscanf(fp, "%d", &currNum);

            // If input is on-numeric, print error and end loop
            if (scanErr == 0) {
                fprintf(stderr, "ERROR: Non-numeric input.\n");
                break;

            // If the input ends, end loop without error
            } else if (scanErr < 0 || scanErr == EOF) {
                break;

            // Else, add a new IntList to the list in sorted order
            } else {

                // Malloc a new Intlist
                IntList *newNum = malloc(sizeof(IntList));
                newNum->val = 0;
                newNum->next = NULL;
                newNum->val = currNum;

                // If the currNum is 0, just put it at the top of the list
                if (currNum == 0) {
                    IntList *hold = head;
                    head = newNum;
                    newNum->next = hold;

                // Else, find the proper spot for the user's int
                } else {

                    // Loop through the list until you either reach the 
                    // end or find a val bigger than the user's current num
                    // keeps track of the nodes that will be before and after newNum
                    IntList *currNode = head;
                    IntList *prevNode = head;

                    while (currNode != NULL && currNode->val < currNum) {
                        prevNode = currNode;
                        currNode = currNode->next;
                    }
                    
                    // If it's not to placed at the head, instert it between 
                    // the found nodes
                    if (prevNode != NULL) {
                        prevNode->next = newNum;
                        newNum->next = currNode;

                    // Else place it at the head
                    } else {
                        head = newNum;
                        head->next = prevNode;
                    }
                }
            }
        }
        return head;
    }
    return NULL;
}


/*                          +
Finds the number of elements in the list past in

@param head: The list whose length is to be found

@return len: The number of elements in list
*/
int getLen(IntList *head) {

    int len = 0;

    IntList *currNode = head->next;
    // printf("FUNCTION START\n");
    while (currNode != NULL) {
        // printf("%d\n", currNode->val);
        currNode = currNode->next;
        len++;
    }
    return len;
}


/*                          +
Writes the contents of the list passed in to the file
passed in.

@param head: The list who vals are to printed out
@param fp: The pointer to the file to print to
*/
void writeOutput(IntList *head, FILE* fp) {

    int i = 0;

    IntList *currNode = head->next;
    while (currNode != NULL) {
        fprintf(fp, "%d: %d\n", i, currNode->val);
        currNode = currNode->next;
        i++;
    }
}


/*                          +
Goes through each IntList in the passed in list and
frees the space used by it.

@param head: The head of the lsit to free
*/
void freeList(IntList *head) {

    IntList *currNode = head;
    while (currNode != NULL) {
        IntList *hold = currNode->next;
        free(currNode);
        currNode = hold;
    }
}
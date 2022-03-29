/*                          J + M + J
File: intSort_funcs
Author: Joseph Gauthier
Purpose: Provides the functions used by the intSort_Main file
*/
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include "proj07_strList.h"


/*                          +
Creates a new StrList list by usering user input 

@param fp: The file to pull usr's input from

@return head: The head of the new StrList
*/
StrList *readInput(FILE *fp) {

    // Check if the file is empty
    int c = fgetc(fp);
    if (c == EOF) {
        return NULL; 
    } else {
        ungetc(c, fp);  // Put the input back for next read
        
        // Malloc the head
        StrList *head = malloc(sizeof(StrList));
        if (head== NULL) {
            fprintf(stderr, "ERROR: Out of memory.");
            exit(1);
        }        

        // Begin loop through user's input
        char *currStr = NULL;
        int scanErr;
        while (1) {
            
            
            // Read in the user's current number
            scanErr = fscanf(fp, "%s", currStr);

            
            // If the input ends, end loop without error
            if (scanErr < 0 || scanErr == EOF) {
                break;

            // Else, add a new StrList to the list in sorted order
            } else {
                
                // Malloc a new StrList
                StrList *newStr = malloc(sizeof(StrList));
                newStr->val = NULL;
                // SEG FAULT HAPPENS HERE  V
                newStr->val = malloc(sizeof(currStr));
                newStr->next = NULL;
                strcpy(newStr->val, currStr);
                

                // Loop through the list until you either reach the 
                // end or find a val bigger than the user's current num
                // keeps track of the nodes that will be before and after newStr
                StrList *currNode = head;
                StrList *prevNode = head;

                while (currNode != NULL && currNode->val < currStr) {
                    prevNode = currNode;
                    currNode = currNode->next;
                 }
                    
                // If it's not to placed at the head, instert it between 
                // the found nodes
                 if (prevNode != NULL) {
                    prevNode->next = newStr;
                    newStr->next = currNode;

                // Else place it at the head
                } else {
                    head = newStr;
                    head->next = prevNode;
                }
            }
        }
        return head;
    }
    return NULL;
}


char* readLongStr (FILE *fp) {
    char* first = NULL;

    fscanf(fp, "%s", first);

    return first;
}


/*                          +
Finds the number of elements in the list past in

@param head: The list whose length is to be found

@return len: The number of elements in list
*/
int getLen(StrList *head) {

    int len = 0;

    StrList *currNode = head;
    while (currNode != NULL) {
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
void writeOutput(StrList *head, FILE* fp) {

    int i = 0;

    StrList *currNode = head;
    while (currNode != NULL) {
        fprintf(fp, "%d: %s\n", i, currNode->val);
        i++;
    }
}


/*                          +
Goes through each StrList in the passed in list and
frees the space used by it.

@param head: The head of the lsit to free
*/
void freeList(StrList *head) {

    StrList *currNode = head;
    while (currNode != NULL) {
        StrList *hold = currNode->next;
        free(currNode);
        currNode = hold;
    }
}
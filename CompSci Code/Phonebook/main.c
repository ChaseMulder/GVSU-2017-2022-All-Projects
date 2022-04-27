#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define MAXLEN 9

void sortLast();
void sortFirst();
void add();
void lookup();
void Remove();

typedef struct entry{
    char first[1024];
    char last[1024];
    char num[1024];
}e;

//struct for node
struct node {
    char *value;            // all void* types replaced by char*
    struct node *p_left;
    struct node *p_right;
};

//use typedef to make calling the compare function easier
typedef int (*Compare)(const char *, const char *);

//inserts elements into the tree
void insert(char* key, struct node** leaf, Compare cmp)
{
    int res;
    if( *leaf == NULL ) {
        *leaf = (struct node*) malloc( sizeof( struct node ) );
        (*leaf)->value = malloc( strlen (key) +1 );     // memory for key
        strcpy ((*leaf)->value, key);                   // copy the key
        (*leaf)->p_left = NULL;
        (*leaf)->p_right = NULL;
        //printf(  "\nnew node for %s" , key);
    } else {
        res = cmp (key, (*leaf)->value);
        if( res < 0)
            insert( key, &(*leaf)->p_left, cmp);
        else if( res > 0)
            insert( key, &(*leaf)->p_right, cmp);
        else                                            // key already exists
            printf ("Key '%s' already in tree\n", key);
    }
}

//compares value of the new node against the previous node
int CmpStr(const char *a, const char *b)
{
    return (strcmp (a, b));     // string comparison instead of pointer comparison
}

char *input( void )
{
    static char line[MAXLEN+1];       // where to place key
    printf("Please enter a string : ");
    fgets( line, sizeof line, stdin );
    return ( strtok(line, "\n" ));    // remove trailing newline
}

//recursive function to print out the tree inorder
void in_order(struct node *root)
{
    if( root != NULL ) {
        in_order(root->p_left);
        printf("%s\n", root->value);     // string type
        in_order(root->p_right);
    }
}
//searches elements in the tree
void search(char* key, struct node* leaf, Compare cmp)  // no need for **
{
    int res;
    if( leaf != NULL ) {
        res = cmp(key, leaf->value);
        if( res < 0)
            search( key, leaf->p_left, cmp);
        else if( res > 0)
            search( key, leaf->p_right, cmp);
        else
            printf("\n'%s' found!\n", key);     // string type
    }
    else printf("\nNot in tree\n");
    return;
}

void delete_tree(struct node** leaf)
{
    if( *leaf != NULL ) {
        delete_tree(&(*leaf)->p_left);
        delete_tree(&(*leaf)->p_right);
        free( (*leaf)->value );         // free the key
        free( (*leaf) );
    }
}

//displays menu for user
void menu()
{
    printf("Press 'i' to insert firstname lastname phonenumber\n");
    printf("Press 's' to search for an element\n");
    printf("Press 'p' to print the tree inorder\n");
    printf("Press 'f' to destroy current tree\n");
    printf("Press 'q' to quit\n");
}

void main( )
{
    struct node *p_root = NULL;
    char *value;
    char option = 'x';

    while( option != 'q' ) {
        //displays menu for program
        menu();

        //gets the char input to drive menu
        option = getch();           // instead of two getchar() calls

        if( option == 'i') {
            value = input();
            printf ("Inserting %s\n", value);
            insert(value,  &p_root, (Compare)CmpStr);
        }
        else if( option == 's' ) {
            value = input();
            search(value, p_root, (Compare)CmpStr);     // no need for **
        }
        else if( option == 'p' ) {
            in_order(p_root);
        }
        else if( option == 'f' ) {
            delete_tree(&p_root);
            printf("Tree destroyed");
            p_root = NULL;
        }
        else if( option == 'q' ) {
            printf("Quitting");
        }
    }
return 0;
}

void sortLast(){

}
void sortFirst(){}
void lookup(){}
void Remove(){}
void add(){

}





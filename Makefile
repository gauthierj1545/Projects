all : strSort_funcs.o strSort_main.o strSort intSort_funcs.o intSort_main.o intSort

intSort :  intSort_main.o intSort_funcs.o
		gcc -Wall -std=c99 -g -o intSort intSort_main.o intSort_funcs.o

intSort_main.o : intSort_main.c proj07_intList.h
	gcc -Wall -std=c99 -g -c intSort_main.c

intSort_funcs.o : intSort_funcs.c proj07_intList.h
	gcc -Wall -std=c99 -g -c intSort_funcs.c

strSort :  strSort_main.o strSort_funcs.o
	gcc -Wall -std=c99 -g -o strSort strSort_main.o strSort_funcs.o

strSort_main.o : strSort_main.c proj07_strList.h
	gcc -Wall -std=c99 -g -c strSort_main.c

strSort_funcs.o : strSort_funcs.c proj07_strList.h
	gcc -Wall -std=c99 -g -c strSort_funcs.c
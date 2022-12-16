# Makefile


CC=gcc
CFLAGS=
OBJS=sudoku.o
LIBS=
all : sudoku.out

sudoku.out : $(OBJS)
	$(CC) $(CFLAGS) -o sudoku.out $(OBJS) $(LIBS)

sudoku.o : sudoku.c
	$(CC) $(CFLAGS) -c sudoku.c

clean:
	rm -f $(OBJS) sudoku.out *.o core

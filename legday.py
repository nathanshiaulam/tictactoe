import fileinput, sys
from random import randint

def main():
	for i in range(0, 20000): 
		sys.stdout.write(str(randint(0, 8)) + " ");
		sys.stdout.flush();

if __name__ == "__main__":
    main()
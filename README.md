# Buddy-System-Memory-allocation-technique-simulation

At the beginning of the run, the program will receive from the user the size of the memory in bytes. The program must accept
the input only if it is a power of 2.
The program should run in a loop and print the following menu:
1. Enter process
2. Exit process
3. Print status
4. Exit
Enter your choice:
The program must run as long as the user has not selected option 4.
1 . If the user chose option 1, the program must record the process number and its size in bytes. After that, the program will allocate suitable memory to the process using the Buddy method. The allocation simulation should be realized as you wish. If it is not possible to allocate memory to the process, the program must print how much external fragmentation there is in the system.
2. If the user chooses option 2, the program must receive the process number and release the memory it occupies.
3. If the user chooses option 3, the program must print the list of running processes and the blocks they occupy (start and end addresses of the block). In addition, the list of free blocks in the system and how much internal fragmentation there is must be printed.

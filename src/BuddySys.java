import java.util.*;

public class BuddySys {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<Process> processList = new ArrayList<Process>();
		System.out.println("enter memory size: ");
		int memoSize = sc.nextInt();
		int freg = 0;
		int sumOfFreg = 0;
		int sumOfAllocation = 0;

		while (!isPowerOfTwo(memoSize)) {
			System.out.println("the size need to be power of 2!");
			System.out.println("enter memory size: ");
			memoSize = sc.nextInt();
		}
			String[] memory = new String[memoSize];

			boolean jobDone = false;
			//find the square of 2
			int x = (int) Math.ceil(Math.log(memoSize) / Math.log(2));

			int input = 0, lastBuffer = 0, pointer = 0;

			lastBuffer = memoSize;
			System.out.println("enter your choise: ");
			while (input != 4) {
				System.out.println("1. add process\n2.exit process\n3.print status\n4.exit");
				input = sc.nextInt();
				switch (input) {
				case 1:
					System.out.println("enter process name:");
					String pName = sc.next();
					System.out.println("enter process size:");
					int processSize = sc.nextInt();
					jobDone = false;
					Process p = new Process(pointer, lastBuffer, pName, processSize);
					while (!jobDone) {
						if (sumOfAllocation + sumOfFreg == memoSize || processSize > memoSize) {
							System.out.println("there is not enough space!");
							System.out.println("external freg: " + (memoSize - (sumOfAllocation + sumOfFreg)));
							break;
							// 2^u-1<x<2^x
						} else if ((processSize != lastBuffer / 2)
								&& (processSize >= Math.pow(2, x - 1) && processSize <= Math.pow(2, x))) {
							
							//If the place we want to occupy is twice as big, divide by 2 the lastBuffer
							while (processSize + pointer < lastBuffer / 2) {
								lastBuffer /= 2;
							}
							//set start and last point of process
							p.setStartAndLastPoint(pointer, lastBuffer - 1);
							
							//thats mean there is internal freg
							if (!isPowerOfTwo(processSize)) {

								freg = lastBuffer - (processSize + pointer);
								p.setInternalFreg(freg);
								sumOfFreg += freg;
							}
							processList.add(p);
							sumOfAllocation += p.size;

							for (int i = pointer; i < pointer + processSize; i++) {
								memory[i] = p.name;
							}
							System.out.println(p);
							
							//there is internal freg
							if (freg > 0) {
								System.out.println("internal freg is:" + freg);
								for (int i = pointer + processSize; i < pointer + processSize + freg; i++) {
									memory[i] = p.name + " external freg";
								}
							}
							//set pointer
							pointer += (processSize + freg);
							lastBuffer = memoSize;
							//we occupy the block
							jobDone = true;
							break;
						} else {
							lastBuffer /= 2;

							x = (int) Math.ceil(Math.log(lastBuffer) / Math.log(2));

						}
					}
					break;
				case 2:
					System.out.println("which process you want to delocate?");
					for (int i = 0; i < processList.size(); i++) {
						System.out.print(i + ":" + processList.get(i).name + ",");
					}
					int choice = sc.nextInt();
					Process delP = processList.get(choice);
					// set pointer to start point of process we delocate
					pointer = delP.startPoint;
					// set lastBuffer to last Point+1 of process we delocate
					lastBuffer = delP.lastPoint + 1;
					for (int i = pointer; i < lastBuffer; i++) {
						memory[i] = null;
					}
					// relase from sum of freg
					sumOfFreg -= delP.InternalFreg;
					// relase from sum of allocation
					sumOfAllocation -= delP.size;
					// remove from my the process list
					processList.remove(choice);
					break;
					
				case 3:
					int start = -1;
					int last = -1;
					int count = 0;
					int i = 0;

					ArrayList<available> availableList = new ArrayList<available>();
					//print process llist
					for (int j = 0; j < processList.size(); j++) {
						System.out.println(processList.get(j));
					}
					
					while ((count < memory.length)) {
						//if in the memory array there is cell that is null
						if (memory[i] == null) {
							start = i;

						}
						//we didnt find free space
						if (start != -1) {
							for (int j = start; j < memory.length; j++) {
								//free space from start to the last place of the memory
								if ((j == memory.length - 1) && (memory[memory.length - 1] == null)) {
									last = memory.length - 1;
									
								} else if (j != memory.length - 1) {
									//the next place isnt free
									if (memory[j + 1] != null) {
										last = j;
									}
								}
								//we find the last free space
								if (last != -1) {
									//ass to available list
									available a = new available(start, last);
									availableList.add(a);
									start = -1;
									last = -1;
									i += (j + 1);
									break;
								}

							}
						} else {
							i++;
						}
						count++;
						if (i >= memory.length) {
							break;
						}
					}

					System.out.println("internal freg:" + sumOfFreg);
					for (int j = 0; j < availableList.size(); j++) {
						available a = availableList.get(j);
						if (a.first != -1 && a.last != -1) {
							System.out.println("available blocks:");
							System.out.println("from " + a.first + " to " + a.last);

						}

					}
				}
			}
		}
	

	public static boolean isPowerOfTwo(int n) {
		return (int) (Math.ceil((Math.log(n) / Math.log(2)))) == (int) (Math.floor(((Math.log(n) / Math.log(2)))));
	}
}
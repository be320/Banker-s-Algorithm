package os;

import java.util.*;

public class OS {

    public static void main(String[] args) {
        int nProcess;
        int nResource;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int nRequests = 50 + rand.nextInt(10);
        System.out.print("Enter Number of Processes...   ");
        nProcess = sc.nextInt();
        System.out.println("");
        System.out.print("Enter Number of Resources...   ");
        nResource = sc.nextInt();
        System.out.println("");
        System.out.println("Enter The Available Number of each Resource");
        int[] available = new int[nResource];
        int[] iniTavailable = new int[nResource];
        Process[] myProcesses = new Process[nProcess];
        Request[] myRequests = new Request[nRequests];
        for (int i = 0; i < nResource; i++) {
            System.out.print("Resource " + (i + 1) + " :   ");
            available[i] = sc.nextInt();
            iniTavailable[i] = available[i];
        }
        System.out.println("");
        //inputs are taken

        for (int i = 0; i < nProcess; i++) {
            myProcesses[i] = new Process(nResource, i, available);
            if (i == 0) {
                myProcesses[i].print(available, true, false);
            } else {
                myProcesses[i].print(available, false, false);
            }
        }
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("");
        //processes are initiallized       
        int Rcounter = 0;
        int Pcounter = 0;
        //
        //nRequests!=0&&notEqual(available, iniTavailable,nRequests)&&
        while (!allfinished(myProcesses)) {
            Process selectedProcess = myProcesses[Pcounter];
            if (!selectedProcess.finished()) {
                int randReqDeallocate = rand.nextInt(Rcounter + 1);
                myRequests[Rcounter] = new Request(nResource, selectedProcess.need, Pcounter);
                Request waiting = myRequests[Rcounter];
                waiting.print();
                if (waiting.safe(available, selectedProcess.need, selectedProcess.allocation)) {
                    selectedProcess.getRequest(waiting, available);
                } else {
                }
                noMore(available, iniTavailable);
                selectedProcess.print(available, true, true);
                if (nRequests < 35) {
                    myRequests[randReqDeallocate].release(available);
                    selectedProcess.finished();
                }
                nRequests--;
                Rcounter++;
            }
            Pcounter++;
            if (Pcounter == nProcess) {
                Pcounter = 0;
            }
        }
    }

    public static boolean allfinished(Process[] myProcesses) {
        for (int i = 0; i < myProcesses.length; i++) {
            if (!myProcesses[i].finished()) {
                return false;
            }
        }
        return true;
    }

    public static void noMore(int[] available, int[] iniTAvailable) {
        for (int i = 0; i < available.length; i++) {
            if (available[i] >= iniTAvailable[i]) {
                available[i] = iniTAvailable[i];
            }
        }
    }

    public static boolean notEqual(int[] available, int[] iniTAvailable, int nRequest) {
        for (int i = 0; i < available.length; i++) {
            if (available[i] != iniTAvailable[i]) {
                return true;
            }
        }
        if (nRequest >= 15) {
            return true;
        } else {
        }
        return false;
    }
}
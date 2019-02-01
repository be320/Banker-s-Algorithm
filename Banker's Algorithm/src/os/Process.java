
package os;
import java.util.*;
public class Process {
    public int[] allocation;
    public int[] max;
    public int[] need;
    public int processId;
    int checker =0;
    public int nResources;
    public ArrayList<Request> myRequests;
    private Random randMax=new Random();
    public Process(int nResources,int id,int[] available)
    {
    this.nResources=nResources;    
    this.processId=id;
    allocation=new int[nResources];
    max=new int[nResources];
    need=new int[nResources];
    myRequests=new ArrayList<>();
    for(int i=0;i<nResources;i++)
    {
    allocation[i]=0;
    max[i]=1+randMax.nextInt(available[i]);
    need[i]=max[i]-allocation[i];
    }
    }
    public void print(int[] available,boolean first,boolean shutRequest)
    {
     //available
        if(first){
        System.out.print("Available Resources: ");
    for(int i=0;i<nResources;i++)
    {
        System.out.print(" "+available[i]+"  ");
    }
        }
        System.out.println("");
        System.out.println("");
     System.out.println(" ProcessId                       ALLOCATION                       Max                         Need");
          System.out.print("              ");   
     System.out.print(processId);
     System.out.print("              ");
           //allocation
    for(int i=0;i<nResources;i++)
        {
            System.out.print(" "+allocation[i]+"  ");
        }
    System.out.print("              ");
    //max
     for(int i=0;i<nResources;i++)
        {
            System.out.print(" "+max[i]+"  ");
        }
    System.out.print("              ");
    //need
     for(int i=0;i<nResources;i++)
        {
            System.out.print(" "+need[i]+"  ");
        }
        System.out.println("");
        System.out.println("");
        if(shutRequest){
    System.out.println("Running Requests on Process "+processId+" :");
    System.out.println("");
    for(int i=0;i<myRequests.size();i++)
    {
        System.out.print("Request "+(i+1)+" : ");
        for(int j=0;j<nResources;j++)
        {
            System.out.print(myRequests.get(i).components[j]+"   ");
        }
        System.out.println("");
    }
    System.out.println("");
        }
    }
public void getRequest(Request waitingRequest,int[] available)
{
if(waitingRequest.reQid==processId)
{
 for(int i=0;i<myRequests.size();i++)
 {
 myRequests.get(i).release(available);
 if(myRequests.get(i).empty())
 {
 myRequests.remove(i);
 }
 }   
myRequests.add(waitingRequest);
    
}
}
public boolean finished()
{
    for(int i=0;i<nResources;i++)
    {
    if(need[i]!=0)
    {
    return false;
    }
    }
    if(checker==0){
    System.out.println("Process  "+processId+"  has finished its work");
    checker=1;
    }
    else{}
    System.out.println("");
return true;
}
}

package os;
import java.util.*;
public class Request {
    public int[] components;
    Random rand =new Random();
    int nResources;
    public int reQid;
    public Request(int nResources,int[] need,int id)
    {
        this.nResources=nResources;
        components=new int[nResources];
        this.reQid=id;
    for(int i=0;i<nResources;i++)
    {
    components[i]=rand.nextInt(need[i]+1);
    }
    }
    public void release(int[] available)
    {
    for(int i=0;i<nResources;i++)
    {
        if(components[i]>0){
            components[i]--;
            available[i]++;
        }
        else
        {}
    }
    }
    public boolean safe(int[] available,int[] need,int[] allocation)
    {
    for(int i=0;i<nResources;i++)
    {
    if(components[i]>available[i])
    {
        System.out.println("");
        System.out.println("The Request is Rejected as it needs more than the available Resources");
        System.out.println("");
    return false;
    }
    }
     for(int i=0;i<nResources;i++)
    {
    available[i]=available[i]-components[i];
    need[i]=need[i]-components[i];
    allocation[i]=allocation[i]+components[i];
            }  
     System.out.println("");
     System.out.println("The Request is Safe");
        System.out.println("");
        return true;
    }
    public void print()
    {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.print("There is a Request for Process "+reQid+"     :   ");
        for(int i=0;i<nResources;i++)
    {
        System.out.print(components[i]+"  ");
    }
        System.out.println("");
    }
    public boolean empty()
    {
    for(int i=0;i<nResources;i++)
    {
    if(components[i]!=0)
        return false;
    }
        System.out.println("Request in Process "+reQid+" has finished and it is deallocated");
        System.out.println("");
        return true;
    }
}

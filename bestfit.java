import java.util.*;
class Memory
{
	int start,size,end;
	boolean empty;
	Memory(int start,int end,boolean empty)
	{
		this.start=start;
		this.end=end;
		this.empty=empty;
		this.size=start-end;
	}
}
public class bestfit
{
	public static void main(String args[])
	{
		int memorysize,start,end,nstart,nend,size,i,j,n;
		Scanner sc=new Scanner(System.in);
		System.out.println("enter no of processes");
		n=sc.nextInt();
		j=0;start=0;end=0;
		System.out.println("enter memry size");
		memorysize=sc.nextInt();
		Memory slice[]=new Memory[1000];
		for(i=0;i<1000;i++)
		{
			if(j!=n)
			{
				System.out.println("Process  "+j+":");
				j++;
				nstart=sc.nextInt();
				nend=sc.nextInt();
				if(end==nstart)
				{
					slice[i]=new Memory(nstart,nend,false);
					end=nend;
				}
				else
				{
					slice[i]=new Memory(end,nstart,true);
					slice[++i]=new Memory(nstart,nend,false);
					end=nend;
					
				}
			}
			else
				break;
		}
		int timeslice=i;
		if(end!=memorysize)
		{
			slice[timeslice++]=new Memory(end,memorysize,true);
		}
		display(slice,timeslice);
		System.out.println("enter process and size");
		size=sc.nextInt();
		bestfit(slice,timeslice,size);
		
	}
	public static void display(Memory[] slice,int timeslice)
	{
		int i;
		System.out.println("id\tstart\tend\tstatus");
		for(i=0;i<timeslice;i++){
		System.out.println(i+"\t"+slice[i].start+"\t"+slice[i].end+"\t"+(slice[i].empty?"free":"occupied"));
		}
	}
	public static void bestfit(Memory[] slice,int totalslice,int size)
	{
		int pos=-1,diff=9999,i;
			for(i=0;i<totalslice;i++){
				if(slice[i].empty && slice[i].size>=size &&(slice[i].size-size)<=diff)
				{
					pos=i;
					diff=slice[i].size-size;
					
				}
			}
				if(pos!=-1)
				{
					slice[pos].size=size;
					slice[pos].end=slice[pos].start+size;
					slice[pos].empty=false;
					if(diff!=0)
					{
					slice[totalslice++]=new Memory(slice[pos].end,slice[pos+1].start,true);
					}
				}
			
			sort(slice,totalslice);
	}
	public static void sort(Memory[] slice,int totalslice)
	{
		int i,j;
		Memory temp;
		for(i=0;i<totalslice;i++){
			for(j=0;i<totalslice-i-1;i++){
				if(slice[j].start>slice[j+1].start)
				{
					temp=slice[j];
					slice[j]=slice[j+1];
					slice[j+1]=temp;
				}
			}
		
		}
		display(slice,totalslice);
	}
}
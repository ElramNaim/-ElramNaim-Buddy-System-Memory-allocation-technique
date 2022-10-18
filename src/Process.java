
public class Process {
	int startPoint;
	int lastPoint;
	String name;
	int size;
	//boolean hasInternalFreg=false;
	int InternalFreg=0;
	public Process(int start, int last, String name, int size) {
		// TODO Auto-generated constructor stub
		this.startPoint = start;
		this.lastPoint = last;
		this.name = name;
		this.size = size;
		
	}

	public void setStartAndLastPoint(int startPoint,int lastPoint) {
		this.startPoint = startPoint;
		this.lastPoint = lastPoint;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+" allocated the Memory from "+this.startPoint+" to "+this.lastPoint;
	}
	public void setInternalFreg(int internalFreg) {
		InternalFreg = internalFreg;
	}
}

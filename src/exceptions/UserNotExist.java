package exceptions;

public class UserNotExist extends Exception {
	private static final long serialVersionUID = 1L;
	public UserNotExist(){
		super();	
	}
	
	public UserNotExist(String s){
		super(s);	
	}
	
}

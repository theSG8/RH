package exceptions;

public class WrongPassword extends Exception  {
	private static final long serialVersionUID = 1L;
	public WrongPassword(){
		super();	
	}
	
	public WrongPassword(String s){
		super(s);	
	}
	

}

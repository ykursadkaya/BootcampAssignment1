package exceptions;

public class CompanyIsBlacklistedException extends RuntimeException
{
	public CompanyIsBlacklistedException(String message)
	{
		super(message);
	}
}

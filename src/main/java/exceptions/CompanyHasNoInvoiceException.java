package exceptions;

public class CompanyHasNoInvoiceException extends RuntimeException
{
	public CompanyHasNoInvoiceException(String message)
	{
		super(message);
	}
}

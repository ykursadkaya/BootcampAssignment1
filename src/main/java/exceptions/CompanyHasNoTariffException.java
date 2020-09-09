package exceptions;

public class CompanyHasNoTariffException extends RuntimeException
{
	public CompanyHasNoTariffException(String message)
	{
		super(message);
	}
}

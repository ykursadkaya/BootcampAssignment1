package exceptions;

import java.util.HashMap;

public class ExceptionMessages
{
	private static final HashMap<Class, String> enMessages = new HashMap<Class, String>()
	{
		{
			put(CompanyHasNoInvoiceException.class, "Company has no invoice ID: ");
			put(CompanyHasNoTariffException.class, "Company has no tariff ID: ");
			put(CompanyIsBlacklistedException.class, "Company is blacklisted ID: ");
			put(CompanyNotFoundException.class, "Company not found ID: ");
		}
	};
	private static final HashMap<Class, String> trMessages = new HashMap<Class, String>()
	{
		{
			put(CompanyHasNoInvoiceException.class, "Firmanin hic faturasi bulunmuyor ID: ");
			put(CompanyHasNoTariffException.class, "Firmanin hic tarifesi bulunmuyor ID: ");
			put(CompanyIsBlacklistedException.class, "Firma kara listeye alinmis ID: ");
			put(CompanyNotFoundException.class, "Firma bulunamadi ID: ");
		}
	};
	public static final HashMap<String, HashMap<Class, String>> messages = new HashMap<String, HashMap<Class, String>>()
	{
		{
			put("EN", enMessages);
			put("TR", trMessages);
		}
	};
}

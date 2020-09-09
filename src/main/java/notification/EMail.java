package notification;

import company.Company;
import exceptions.*;
import invoice.Invoice;
import invoice.InvoiceManager;
import tariff.EMailFixTariff;
import tariff.FlexTariff;
import tariff.Tariff;
import tariff.TariffManager;

import java.util.ArrayList;

public class EMail extends Message implements Notification
{

	public EMail(Company sender, ArrayList<String> recipientList, String content)
	{
		super(sender, recipientList, content);
	}

	@Override
	public void send(String recipient)
	{
		try
		{
			Tariff tariff = TariffManager.getInstance().getTariff(sender.getId(), this.getClass().getSimpleName());
			if (!InvoiceManager.getInstance().isBlacklisted(sender.getId()) && tariff.canSend()) // can be extracted to a method
			{
				Invoice invoice = InvoiceManager.getInstance().getLastInvoice(sender.getId());
				if (tariff instanceof FlexTariff)
				{
					invoice.addAmount(((FlexTariff) tariff).unitPrice());
				}
				else
				{
					EMailFixTariff tariffToAdd = new EMailFixTariff();
					TariffManager.getInstance().addTariffToCompany(sender.getId(), tariffToAdd);
					invoice.addAmount(tariffToAdd.getPRICE());
				}
				System.out.println(sender.getEmailAddress() + " " + recipient + " " + content);
			}
		}
		catch (CompanyNotFoundException | CompanyIsBlacklistedException | CompanyHasNoTariffException | CompanyHasNoInvoiceException e)
		{
			e.printStackTrace();
			System.err.println(ExceptionMessages.messages.get(sender.getLanguage()).get(e.getClass()) + sender.getId().toString());
		}
	}

	@Override
	public void sendAll()
	{
		for (String recipient: recipientList)
		{
			send(recipient);
		}
	}
}

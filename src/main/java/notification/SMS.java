package notification;

import company.Company;
import exceptions.*;
import invoice.Invoice;
import invoice.InvoiceManager;
import tariff.FlexTariff;
import tariff.SMSFixTariff;
import tariff.Tariff;
import tariff.TariffManager;

import java.util.ArrayList;

public class SMS extends Message implements Notification
{
	public SMS(Company sender, ArrayList<String> recipientList, String content)
	{
		super(sender, recipientList, content);
	}

	@Override
	public void send(String recipient)
	{
		try
		{
			Tariff tariff = TariffManager.getInstance().getTariff(sender.getId(), this.getClass().getSimpleName());
			if (!InvoiceManager.getInstance().isBlacklisted(sender.getId()) && tariff.canSend())
			{
				Invoice invoice = InvoiceManager.getInstance().getLastInvoice(sender.getId());
				if (tariff instanceof FlexTariff)
				{
					invoice.addAmount(((FlexTariff) tariff).unitPrice());
				}
				else
				{
					SMSFixTariff tariffToAdd = new SMSFixTariff();
					TariffManager.getInstance().addTariffToCompany(sender.getId(), tariffToAdd);
					invoice.addAmount(tariffToAdd.getPRICE());
				}
				System.out.println(sender.getPhoneNumber() + " " + recipient + " " + content);
			}
		}
		catch (CompanyNotFoundException | CompanyHasNoInvoiceException | CompanyHasNoTariffException | CompanyIsBlacklistedException e)
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

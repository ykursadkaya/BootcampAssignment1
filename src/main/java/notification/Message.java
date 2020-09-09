package notification;

import company.Company;

import java.util.ArrayList;

public abstract class Message
{
	protected Company sender;
	protected ArrayList<String> recipientList;
	protected String content;

	public Message(Company sender, ArrayList<String> recipientList, String content)
	{
		this.sender = sender;
		this.recipientList = recipientList;
		this.content = content;
	}

	public Company getSender()
	{
		return sender;
	}

	public void setSender(Company sender)
	{
		this.sender = sender;
	}

	public ArrayList<String> getRecipientList()
	{
		return recipientList;
	}

	public void setRecipientList(ArrayList<String> recipientList)
	{
		this.recipientList = recipientList;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}

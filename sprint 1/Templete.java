import java.util.ArrayList;

public class Templete {

	private ArrayList<String> item= new ArrayList<>();
	private ArrayList<Integer> itemID=new ArrayList<>();
	private ArrayList<Integer> price = new ArrayList<Integer>();
	private NotificationManger notificationManger = new CRUD();
	private static int ID = 0;

	public ArrayList getItem() {
		return this.item;
	}

	public ArrayList getItemID() {
		return this.itemID;
	}

	public ArrayList getPrice() {
		return this.price;
	}


	/**
	 * 
	 * @param itemName
	 * @param price
	 */
	public void addItem(String itemName, int price) 
	{
		itemID.add(ID++);
		item.add(itemName);
		this.price.add(price);
	}

	/**
	 * 
	 * @param user
	 * @param itemID
	 */
	public void callCreate(User user, int itemID,Type type)
	{
		String[] data;
		Content content;

		if(itemID == -1)
		{
			data = new String[1];
			content = new activation();
		}
		else if(itemID == -2)
		{
			data = new String[1];
			content = new forgetPw();
		}
		else
		{
			data = new String[2];
			content = new bookItem();
			data[1]= item.get(itemID);
		}
		content.setType(type);
		data[0]= user.getName();
		user.notificationList.add(notificationManger.create(data,content));
	}

	/**
	 * 
	 * @param user
	 * @param notificationId
	 * @param itemID
	 */
	public void callUpdate(User user, int notificationId, int itemID) 
	{
		int index=-1;
        for (int i = 0; i < user.notificationList.size(); i++) 
        {
            if (user.notificationList.get(i).getNotificationID() == notificationId)
            {
                index=i;
                break;
            }
        }
        if (index==-1)
        {
        	System.out.println("wrong notification ID");
            throw new UnsupportedOperationException();
        }
        
        String[] data = new String[2];
        data[0]=user.getName();
        if (itemID<ID)
        	data[1]=item.get(itemID);
        else{
			System.out.println("Wrong Item Id");
			return;
		}
        user.notificationList.set(index, notificationManger.update(user.notificationList.get(index),data));
	}

}
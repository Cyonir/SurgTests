package ca.ubc.cpsc310.gitlab.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.ajaxloader.client.AjaxLoader;
import com.google.gwt.ajaxloader.client.AjaxLoader.AjaxLoaderOptions;

import ca.ubc.cpsc310.gitlab.client.products.ProductItem;
import ca.ubc.cpsc310.gitlab.client.user.IUser;
import ca.ubc.cpsc310.gitlab.client.user.User;

import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.MapTypeId;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapTypeId;
import com.google.maps.gwt.client.GoogleMap;

import java.util.List;

import ca.ubc.cpsc310.gitlab.client.service.LoadUsersService;
import ca.ubc.cpsc310.gitlab.client.service.LoadUsersServiceAsync;
import ca.ubc.cpsc310.gitlab.client.user.IUser;
import ca.ubc.cpsc310.gitlab.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GitLab implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";



	private final FlexTable flexTable = new FlexTable();
	private final FlexTable flex = new FlexTable();
	private final FlexTable flexer = new FlexTable();
	ListBox lb = new ListBox();
	ListBox lb1 = new ListBox();
	final LoadUsersServiceAsync service = GWT.create(LoadUsersService.class);
	String b;
	final HashMap<String, List<String>> hm = new HashMap<String, List<String>>();
	final ArrayList<String> arr1 = new ArrayList<String>();
	GoogleMap map;
	final HorizontalPanel mapLayout = new HorizontalPanel();  
	String g;
	final TextBox text = new TextBox();
	HorizontalPanel panelforaddress = new HorizontalPanel();
	final Button showMap = new Button("Plot Location");
	final TextArea addresstext = new TextArea();


	private String Address;
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad(){ 
		
		
		final HashMap<String, List<String>> hm = new HashMap<String, List<String>>();
		
		
		
		  addresstext.setCharacterWidth(40);
		  addresstext.setVisibleLines(5);
		  
		  addresstext.addKeyPressHandler(new KeyPressHandler() {
			  	public void onKeyPress(KeyPressEvent event) {
		    	  int keyCode = event.getUnicodeCharCode();
					if(keyCode == KeyCodes.KEY_ENTER){
						String f = addresstext.getText();
						
						boolean z = Window.confirm("Is this your entered Address? " + f);
						if (z == true){
						SetAddress(f);
						}
		        }
		      }
		    });
		  
		  addresstext.addKeyPressHandler(new KeyPressHandler() {
			  	public void onKeyPress(KeyPressEvent event) {
		    	  int keyCode = event.getUnicodeCharCode();
					if(keyCode == KeyCodes.KEY_TAB){
						Window.confirm(Address);
		        }
		      }
		    });

		panelforaddress.add(addresstext);
		 
		   
		   
		
		flex.setText(0,0, "Hospital Name");
		flex.setText(0,1,"Surgery");
		flex.setStyleName("centered-table", true);
		flexer.setText(0, 0, "This is a FlexTable within another FlexTable that is being added dynamically upon the entered search call.");
		flexer.setStyleName("centered-table", true);
		
		final ListBox lb = new ListBox();

		final SimplePanel widg = new SimplePanel() ;

		final ArrayList<String> array = new ArrayList<String>();
		    array.add("Vancouver");
		    array.add("bar");
		    array.add("baz");
		    array.add("toto");
		    array.add("Surgery 2");
		
		   for (int i=0; i < array.size(); i++){
			   lb.addItem(array.get(i));
		   }
		   
		   final ArrayList<String> h1 = new ArrayList<String>();
		   final ArrayList<String> h2 = new ArrayList<String>();
		   
		   h1.add("Hospital 1");
		   h2.add("Hospital 2");
		   
		   hm.put("Surgery 1", h1);
		   hm.put("Surgery 2", h2);

		    lb.addKeyPressHandler(new KeyPressHandler() {
				@Override
				public void onKeyPress(KeyPressEvent event) {
					int keyCode = event.getUnicodeCharCode();
					
					if(keyCode == KeyCodes.KEY_ENTER){
						boolean a = false;
					a =	Window.confirm("Would you like to select " + lb.getItemText(lb.getSelectedIndex()) + " surgery?");
						
					if (a == true){
						Window.alert("You confirmed the previously selected surgery.");
						g = lb.getItemText(lb.getSelectedIndex());
						
					}
					else {
						b = Window.prompt("Please type the name of the surgery then: ", "Surgery Name");
		
					}
					}	
				}
		    	
		    });
		    
		    lb.addKeyPressHandler(new KeyPressHandler() {
				@Override
				public void onKeyPress(KeyPressEvent event) {
					int keyCode = event.getUnicodeCharCode();
					
					if(keyCode == KeyCodes.KEY_BACKSPACE){
						if (array.contains(b)){
							for (int i=0; i < array.size(); i++){
								if (b.equals(array.get(i))){
									lb.setSelectedIndex(i);
									//pass out as a method that sets a field
									
									
//									int j = 0;
//									List<String> z = hm.remove(b);
//								while(z.size() >= j){
//									lb1.addItem(z.get(j));
//								j++;
//								}
								addlb1();
												}
							
											}
									}	
						else Window.alert("That is an invalid Surgery name.");
							}	
					}

			
		    });
		    
	    
//		    lb.addClickHandler(new ClickHandler(){
//		    	public void onClick(ClickEvent event){
//		    		String a = lb.getItemText(lb.getSelectedIndex());
//		    		Window.alert(a);
//		    	}
//		    });
		    
		    lb.setVisibleItemCount(1);

			VerticalPanel panel = new VerticalPanel();
			 panel.setSpacing(10);
			 panel.add(flex);
			 panel.add(lb);
			 panel.add(showMap);
			 panel.setTitle("Hospital Selector");
			 
			 RootPanel.get("gwtContainer").add(panel);  
		
		
		
		final LoadUsersServiceAsync service = GWT.create(LoadUsersService.class);
		
		service.getUsers(new AsyncCallback<List<IUser>>(){

			@Override
			public void onFailure(Throwable caught) {
					Window.alert("Error occured " + caught.getClass() + " : " + caught.getMessage());
				
			}

			@Override
			public void onSuccess(List<IUser> result) {
				displayUsers(result);
				
			}});
	
		
		// This is the layout which will hold the button
		

		//--- This is the layout which will hold the Map  
		widg.setSize("700px", "200px");   
		flexer.setWidget(1, 1, mapLayout);
		mapLayout.setVisible(false);

		// This is the Click Handler where the map rendering process has been written
		showMap.addClickHandler(new ClickHandler() {
			
			LatLng t;
		    public void onClick(ClickEvent event) {
		    	if (g != "Vancouver"){
		    		double lat = 49.2569777;
		    		double lng = -123.123904;
		    	
		    	t = LatLng.create(lat, lng);
		    	}
		    	
		    	else{
		    		double	lat = 39.509;
		    		double 	lng = -98.213;
		    		
		    	t =	LatLng.create(lat, lng);
		    	}
		    	
		        MapOptions options  = MapOptions.create();
		        options.setCenter(t); 
		        options.setZoom(6);
		        options.setMapTypeId(MapTypeId.ROADMAP);
		        options.setDraggable(true);
		        options.setMapTypeControl(true);
		        options.setScaleControl(true);
		        options.setScrollwheel(true);

		        GoogleMap theMap = GoogleMap.create(widg.getElement(), options) ;
		        mapLayout.add(widg);
		        mapLayout.setVisible(true);
		        
	
		        
		    }  
		});
		
	}
	
	private void addlb1() {
		flex.setWidget(2, 1, lb1);
		flex.setWidget(2,2, flexer);
	}
	

	/**
	 * Used to display users 
	 * @param users
	 */
	public void displayUsers(List<IUser> users)
	{

		RootPanel.get("root").add(flexTable);
		
		flexTable.setText(0,0, "Hospital Name");
		
		flexTable.setText(0,1,"Surgery Type");
		flexTable.setStyleName("centered-table", true);
		
		for(int i=0; i < users.size(); i++)
		{
		
			IUser user = users.get(i);
			
			flexTable.setText(i+1,0,user.getName());
			
			if (user.getLanguage().trim().equals("FR")){
				flexTable.setWidget(i+1, 1, dropDownPopulate(lb));
			}
			
			flexTable.setText(i+1,2, "<-Please Select");
			flexTable.setWidget(2, 1, panelforaddress);
			flexTable.setText(2, 0, "Please type your address:");
			
		//	flexTable.setText(i+1,3,String.valueOf(user.getWishList().size()));
		}
	}
	
public ListBox dropDownPopulate(ListBox lb){
	
	 ArrayList<String> array = new ArrayList<String>();
	    array.add("Surgery 1");
	    array.add("Surgery 2");
	    array.add("Surgery 3");
	    array.add("Surgery 4");
	    array.add("Surgery 5");
	
	   for (int i=0; i < array.size(); i++){
		   lb.addItem(array.get(i));
	   }
	return lb;
}

private void SetAddress(String f) {
	Address = f;
	Window.alert("Your address is: " + getAddress());
}

public String getAddress(){
	if(Address.length() > 1){
	return Address;
		}
	else return "No Address Specified.";
	}

}

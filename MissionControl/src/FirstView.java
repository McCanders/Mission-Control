import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.MarkerLayer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.render.SurfaceEllipse;
import gov.nasa.worldwind.render.markers.BasicMarker;
import gov.nasa.worldwind.render.markers.BasicMarkerAttributes;
import gov.nasa.worldwind.render.markers.BasicMarkerShape;
import gov.nasa.worldwind.render.markers.Marker;
import gov.nasa.worldwind.render.markers.MarkerAttributes;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

public class FirstView {
	
	protected WorldWindow worldWindCanvas;
	
	FirstView(){
		
		
		System.out.println("Started");
		//create a WorldWind main object
				WorldWindow worldWindCanvas = new WorldWindowGLCanvas();
				worldWindCanvas.setModel(new BasicModel());
				
				//build Java swing interface
				JFrame frame = new JFrame("World Wind");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add((Component) worldWindCanvas);
				frame.setSize(800,600);
				frame.setVisible(true);
				
				
				//List to hold positions and use them later for creating picture 
				LinkedList<Position> mousePositionOnMap = new LinkedList<>();
				
				//adding mouse listener to window
				worldWindCanvas.getInputHandler().addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent pE) {
                        //getting position from current mouse click
				    final Position mouseCurrentPosition = worldWindCanvas.getCurrentPosition();

				        //Or whatever work:      
				        if(mouseCurrentPosition != null) {
				        	
				        	//constructor for creating placeMarks from mouse position
							
							
				        	
							drawView(mousePositionOnMap);
							
							
				        	//after null check to make sure coordinates are not null avoiding null point exceptions
				        	mousePositionOnMap.add(mouseCurrentPosition);

				            System.out.println("Current Pos= " + mouseCurrentPosition);
				            
				            
				           
				             
				        }//ends if loop if position is not null
				        
				        else {

				            System.out.println("Current Pos is null!");

				        }//ends else loop it position is null 
				       
				    }//ends click event
				    
				    
				});//ends mouse Listener 
				
				drawView(mousePositionOnMap);
				
				//if(mousePositionOnMap.size()>0)drawView(mousePositionOnMap);
				
				
				
				
				

			
	}//ends first view Constructor
	
	//function to draw layer or view from list
	void drawView(List<Position> givenList){
		if(givenList.size()>0){
		
		//loop for iterating through Position list
		for(int i = 0; i< givenList.size(); i++){
			final RenderableLayer	layer = new RenderableLayer();
			
			//getting value to pass in  of PointPlacemark
			
			java.awt.EventQueue.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	                // Create an AppFrame and immediately make it visible. As per Swing convention, this
	                // is done within an invokeLater call so that it executes on an AWT thread.
	            	for(int i = 0; i<givenList.size(); i++ ){
	            	Position mouseCurrentPositionFromList = givenList.get(i);
	    			PointPlacemark pointPlacemarkOnMapList= new  PointPlacemark (mouseCurrentPositionFromList);
	    			layer.addRenderable(pointPlacemarkOnMapList);
	    			worldWindCanvas.
	    			
	            	}
	            }
	        });
	    
			
		}
			
			
			//constructor for creating placeMarks from mouse position
			
			//Shoving number of placemarker in list and how it is added to map
			//pointPlacemarkOnMap.setLabelText("Point " + givenList.get(i));
			
			//System.out.println("From list "+mouseCurrentPositionFromList );
			
			//adding to layer every marker
			
			
			
		}//ends for loop in drawView
		
		
	}//ends method drawView
   
	
}//ends first view class

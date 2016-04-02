import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JFrame;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.util.BasicDragger;

public class FirstView {
	
	protected WorldWindow worldWindCanvas;
	static RenderableLayer layer = null;
	
	FirstView(){
		
		
		System.out.println("Started");
		//create a WorldWind main object
				WorldWindow worldWindCanvas = new WorldWindowGLCanvas();
				//setting model for now Basic model
				worldWindCanvas.setModel(new BasicModel());
				//adding for dragging
				worldWindCanvas.getInputHandler().addSelectListener(new BasicDragger(worldWindCanvas));
				
				//build Java swing interface
				JFrame frame = new JFrame("World Wind");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add((Component) worldWindCanvas);
				frame.setSize(800,600);
				frame.setVisible(true);
				
				
				//List to hold positions and use them later for creating picture 
				LinkedList<Position> mousePositionOnMap = new LinkedList<>();
				//for now layer shared with others so screen has only one layer on top 
				layer = new RenderableLayer();
				
				//adding mouse listener to window
				worldWindCanvas.getInputHandler().addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent pE) {
                        //getting position from current mouse click
				    final Position mouseCurrentPosition = worldWindCanvas.getCurrentPosition();

				        //Or whatever work:      
				        if(mouseCurrentPosition != null) {
				        	//after null check to make sure coordinates are not null avoiding null point exceptions
				        	mousePositionOnMap.add(mouseCurrentPosition);

				        	//constructor for creating placeMarks from mouse position
				        	PointPlacemark pointPlacemarkOnMapList = new  PointPlacemark (mouseCurrentPosition);
			    				
			    			
			    			layer.addRenderable(pointPlacemarkOnMapList);
			    			
			    			//creating poly line checking if more than one entry in list for line
	    					
							
				        	
							
							//adding to window
							worldWindCanvas.getModel().getLayers().add(layer);
				        	
				            System.out.println("Current Pos= " + mouseCurrentPosition);
				            
				            
				           
				             
				        }//ends if loop if position is not null
				        
				        else {

				            System.out.println("Current Pos is null!");

				        }//ends else loop it position is null 
				       
				    }//ends click event
				    
				    
				});//ends mouse Listener 
				
				//adding select listener to window 
				worldWindCanvas.getInputHandler().addSelectListener(new SelectListener(){
						   //running method if for selection 
			    			public void selected(SelectEvent event){
			    				//checking what is selected
			    				if(event.getEventAction().equals(SelectEvent.DRAG_END) && event.hasObjects() && event.getTopObject() instanceof Polyline){
			    					//logic what to do after selection is finished
			    					System.out.println("Checcking markers poly drag");
			    					
			    					 
			    					
			    					
			    				}//ends logic for first type (if) statement
			    				//adding one more selection criteria
			    				else if (event.getEventAction().equals(SelectEvent.DRAG_END) && event.hasObjects() && event.getTopObject() instanceof  PointPlacemark){
			    					System.out.println("Checcking markers  drag");
			    					
			    					layer.dispose();
			    					 if (mousePositionOnMap.size()>1){
				 				        	Polyline polyline = new Polyline(mousePositionOnMap);
				 				        	polyline.setColor(Color.RED);
				 				        	polyline.setLineWidth(2);
				 				        	layer.addRenderable(polyline);}//end of polyline creating
			    					//TODO
			    					
			    				}//end second if (else if) logic
			    			
			    			}//ends selected method
						
				
						
			    			});//ends select listeners		
				
				
				
				
				//if(mousePositionOnMap.size()>0)drawView(mousePositionOnMap);
				
				
				
				
				

			
	}//ends first view Constructor
	
	
   
	
}//ends first view class

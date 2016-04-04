import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Earth.BMNGOneImage;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwindx.applications.sar.TrackPanel;
import gov.nasa.worldwind.layers.*;

public class FirstView {
	
	protected WorldWindow worldWindCanvas;
	static RenderableLayer layer = null;
	private Position mouseCurrentPosition = null;
	private List<Position> mousePositionOnMap;
	
	
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
				// Create one set of layers.
	            
	            
				layer = new RenderableLayer();
				
				//adding mouse listener to window
				worldWindCanvas.getInputHandler().addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent pE) {
                        //getting position from current mouse click
				   
				    	final Position mouseCurrentPosition = worldWindCanvas.getCurrentPosition();

				        //Or whatever work:      
				        if(mouseCurrentPosition != null && (mousePositionOnMap.contains(mouseCurrentPosition) != true )) {
				        	//after null check to make sure coordinates are not null avoiding null point exceptions
				        	 
				        	//constructor for creating placeMarks from mouse position
				        	mousePositionOnMap.add(mouseCurrentPosition);
				        	
				        	
				        	
				        	
				        	
			    			
			    			
			    			//adding select listener to window 
							worldWindCanvas.getInputHandler().addSelectListener(new SelectListener(){
									   //running method if for selection 
						    			public void selected(SelectEvent event){
						    				//checking what is selected
						    				if(event.getEventAction().equals(SelectEvent.HOVER) && event.hasObjects()){
						    					//logic what to do after selection is finished

						    					layer.dispose();
						    				
						    					PointPlacemark hoveredPointPlacemark = (PointPlacemark) event.getTopObject();
						    					
						    					Position pickedPosition = hoveredPointPlacemark.getPosition();
						    					
						    					//end of polyline creating
						    				
						    						Position moveEndPosition = worldWindCanvas.getCurrentPosition();
							    					if(mousePositionOnMap.contains(pickedPosition)) {
							    						int indexToReplace = mousePositionOnMap.indexOf(pickedPosition);
							    						
							    						System.out.println("Position found" + mousePositionOnMap.indexOf(pickedPosition));
							    						mousePositionOnMap.set(indexToReplace, moveEndPosition);
							    						
							    						
							    						
							    					};
						    					 
							    					
						    					
						    					
						    				}//ends logic for first type (if) statement
						    				//adding one more selection criteria
						    				else if (event.getEventAction().equals(SelectEvent.DRAG) && event.hasObjects() && event.getTopObject() instanceof  PointPlacemark  ){
						    					
						    					
						    					
						    					
						    					
						    					//TODO
						    					
						    				}//end second if (else if) logic
						    			
						    			}//ends selected method
									
							
									
						    			});//ends select listeners		
							
	    					
							
				        	
							
							//adding to window
							
				        	
				            System.out.println("Current Pos= " + mouseCurrentPosition);
				            
				            
				            drawPolyLines(mousePositionOnMap);
				        	drawMarkers(mousePositionOnMap);
				        	worldWindCanvas.getModel().getLayers().add(layer);
				             
				        }//ends if loop if position is not null
				        
				        else {

				            System.out.println("NOTHING added this position is on list, or other reason");

				        }//ends else loop it position is null 
				       
				    }//ends click event
				       
				    
				});//ends mouse Listener 
				
				
				
				
				
				//if(mousePositionOnMap.size()>0)drawView(mousePositionOnMap);
				
				
				
				
				

			
	}//ends first view Constructor
	
 private void drawPolyLines(List<Position> list){
	 if (list.size()>1){
      	Polyline polyline = new Polyline(list);
      	
      	
      	polyline.setColor(Color.RED);
      	polyline.setLineWidth(2);
      	layer.addRenderable(polyline);}
	 
 }	
 
 private void drawMarkers(List<Position> list){
	 
	 for(Position pos : list){
	 PointPlacemark pointPlacemarkOnMapList = new  PointPlacemark (pos);
	 layer.addRenderable(pointPlacemarkOnMapList);
	 
	 }
 }
   
	
}//ends first view class

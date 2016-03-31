import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.render.SurfaceEllipse;
import gov.nasa.worldwind.render.SurfacePolyline;
import gov.nasa.worldwind.util.BasicDragger;

public class FirstView {
	public static RenderableLayer layer = null;
	
	
	
	FirstView(){
		
		
		System.out.println("Started");
		//create a WorldWind main object
		WorldWindowGLCanvas worldWindCanvas = new WorldWindowGLCanvas();
		worldWindCanvas.setModel(new BasicModel());
		worldWindCanvas.addSelectListener(new BasicDragger(worldWindCanvas,true));
				
				//build Java swing interface
				JFrame frame = new JFrame("World Wind");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(worldWindCanvas);
				frame.setSize(800,600);
				frame.setVisible(true);
				
				
				LinkedList<Position> list = new LinkedList<>();
				worldWindCanvas.getInputHandler().addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent pE) {
				    
				    	final Position aCurrentPosition = worldWindCanvas.getCurrentPosition();
				    	 	
				    	list.add(aCurrentPosition);
				    	
				    	

				        //Or whatever work:      
				        if(aCurrentPosition != null) {

				            System.out.println("Current Pos= " + aCurrentPosition);
				            
				            SurfaceEllipse e2 = new SurfaceEllipse(
				            		   LatLon.fromDegrees(aCurrentPosition.getLatitude().degrees,aCurrentPosition.getLongitude().degrees), // reference position - order is LATITUDE, LONGITUDE
					                100000.0d, // major radius
					                50000.0d, // minor radius
					                Angle.fromRadians(aCurrentPosition.getElevation())// rotation
					                );
				            
				           
				            
				            layer = new RenderableLayer();
					        layer.setName("Interactive ellipses");
					        
					        
					        
					        worldWindCanvas.getModel().getLayers().add(layer);
					        layer.addRenderable(e2);
					      
							
							
						

				        } else {

				            System.out.println("Current Pos is null!");

				        }
				        if (list.size()>1){
				        	Polyline polyline = new Polyline(list);
				        	polyline.setColor(Color.RED);
				        	polyline.setLineWidth(2);
				        	layer.addRenderable(polyline);
				        	
				        	
				    	}
				        
				        }
				    
				    
				});
			
			        
			        
			       
				
				
		        
		    
				
				
				
				
				
				
				
				//create some "Position" to build a polyline
				;
				
					//in this case, points are in geographic coordinates.
					//If you are using cartesian coordinates, you have to convert them to geographic coordinates.
					//Maybe, there are some functions doing that in WWJ API...
					
				
				
				
	
	
	/*
	
	
	
	
	Canvas.getInputHandler().addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent pE) {

        Position aCurrentPosition = aCanvas.getCurrentPosition();

        //Or whatever work:      
        if(aCurrentPosition != null) {

            System.out.println("Current Pos= " + aCurrentPosition);

        } else {

            System.out.println("Current Pos is null!");

        }
    }
});Canvas.getInputHandler().addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent pE) {

        Position aCurrentPosition = aCanvas.getCurrentPosition();

        //Or whatever work:      
        if(aCurrentPosition != null) {

            System.out.println("Current Pos= " + aCurrentPosition);

        } else {

            System.out.println("Current Pos is null!");

        }
    }
});Canvas.getInputHandler().addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent pE) {

        Position aCurrentPosition = aCanvas.getCurrentPosition();

        //Or whatever work:      
        if(aCurrentPosition != null) {

            System.out.println("Current Pos= " + aCurrentPosition);

        } else {

            System.out.println("Current Pos is null!");

        }
    }
});Canvas.getInputHandler().addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent pE) {

        Position aCurrentPosition = aCanvas.getCurrentPosition();

        //Or whatever work:      
        if(aCurrentPosition != null) {

            System.out.println("Current Pos= " + aCurrentPosition);

        } else {

            System.out.println("Current Pos is null!");

        }
    }
});Canvas.getInputHandler().addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent pE) {

        Position aCurrentPosition = aCanvas.getCurrentPosition();

        //Or whatever work:      
        if(aCurrentPosition != null) {

            System.out.println("Current Pos= " + aCurrentPosition);

        } else {

            System.out.println("Current Pos is null!");

        }
    }
});Canvas.getInputHandler().addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent pE) {

        Position aCurrentPosition = aCanvas.getCurrentPosition();

        //Or whatever work:      
        if(aCurrentPosition != null) {

            System.out.println("Current Pos= " + aCurrentPosition);

        } else {

            System.out.println("Current Pos is null!");

        }
    }
});*/
	}
}

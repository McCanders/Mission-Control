import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

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
	public static RenderableLayer layer = null;
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
				
				((WorldWindow)worldWindCanvas).getInputHandler().addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent pE) {

				        Position aCurrentPosition = worldWindCanvas.getCurrentPosition();

				        //Or whatever work:      
				        if(aCurrentPosition != null) {

				            System.out.println("Current Pos= " + aCurrentPosition);
				            

				        } else {

				            System.out.println("Current Pos is null!");

				        }
				    }
				});
				
				
				
				

			/*	// Create an empty layer, give it a name
		        layer = new RenderableLayer();
		        layer.setName("Interactive ellipses");
		        
		     // Use the built-in renderer code to draw an ellipse (near Perth, Australia).
		        SurfaceEllipse e1 = new SurfaceEllipse(
		                LatLon.fromDegrees(-31.0d, 115.0d), // reference position - order is LATITUDE, LONGITUDE
		                100000.0d, // major radius
		                50000.0d, // minor radius
		                Angle.fromDegrees(45.0d) // rotation
		                );
		        
		        e1.getAttributes().setInteriorMaterial(Material.ORANGE);
		        layer.addRenderable(e1);
		        
		        worldWindCanvas.getModel().getLayers().add(layer);
				worldWindCanvas.addSelectListener(new SelectListener(){
					
					// triggered when any selection event occurs
		            public void selected(SelectEvent event) {
	System.out.println("*** SELECT EVENT FIRED **** (" + event.getEventAction() + ")");
					
					
					
					
				}*/
				
				
				
				
				
				
				
			/*	//create some "Position" to build a polyline
				LinkedList<Position> list = new LinkedList<Position>();
				for(int i = 0 ; i < 90 ; i++) {
					//in this case, points are in geographic coordinates.
					//If you are using cartesian coordinates, you have to convert them to geographic coordinates.
					//Maybe, there are some functions doing that in WWJ API...
					list.add(Position.fromDegrees(i,0.0,i*20000));
				}
				
				//create "Polyline" with list of "Position" and set color / thickness
				Polyline polyline = new Polyline(list);
				polyline.setColor(Color.RED);
				polyline.setLineWidth(3.0);
				
				//create a layer and add Polyline
				RenderableLayer layer = new RenderableLayer();
				layer.addRenderable(polyline);
				
				//add layer to WorldWind
				worldWindCanvas.getModel().getLayers().add(layer);
				
				
	
	
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
	// Create the default model as described in the current worldwind properties.
   
	
}

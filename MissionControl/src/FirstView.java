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
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwindx.examples.ApplicationTemplate;


public class FirstView  {
   
	protected WorldWindow worldWindCanvas;
	static RenderableLayer layer = null;

	private List<Position> mousePositionOnMap;
	private Position pickedPosition;
	 /**
     * Flag to indicate something was selected 
     */
    public static boolean selected = false;
	
	

	FirstView() {
		

		System.out.println("Started");
		
		// create a WorldWind main object
		WorldWindowGLCanvas worldWindCanvas = new WorldWindowGLCanvas();
		// setting model for now Basic model
		worldWindCanvas.setModel(new BasicModel());
		// adding for dragging
		worldWindCanvas.getInputHandler().addSelectListener(new BasicDragger(worldWindCanvas));

		// build Java swing interface
		JFrame frame = new JFrame("World Wind");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add((Component) worldWindCanvas);
		frame.setSize(800, 600);
		frame.setVisible(true);

		// List to hold positions and use them later for creating picture
		LinkedList<Position> mousePositionOnMap = new LinkedList<>();
		// for now layer shared with others so screen has only one layer on top
		// Create one set of layers.

		layer = new RenderableLayer();
        layer.setPickEnabled(true);
		// adding mouse listener to window
        // create an instance of the dragger and give it a WWJ instance to use
        
		worldWindCanvas.getInputHandler().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent pE) {
				// getting position from current mouse click

				final Position mouseCurrentPosition = worldWindCanvas.getCurrentPosition();

				// Making sure that not adding null positions and the same
				// position again
				if (mouseCurrentPosition != null && (mousePositionOnMap.contains(mouseCurrentPosition) != true)
						&& pE.getButton() == MouseEvent.BUTTON1) {
					// after null check to make sure coordinates are not null
					// avoiding null point exceptions

					// creating list of position from map
					mousePositionOnMap.add(mouseCurrentPosition);

					// adding select listener to window
					worldWindCanvas.getInputHandler().addSelectListener(new SelectListener() {
						// running method if for selection
						public void selected(SelectEvent event) {
							
							// checking what is selected
							if (event.getEventAction().equals(SelectEvent.HOVER) && event.hasObjects()
									&& event.getTopObject() instanceof PointPlacemark) {
								// logic what to do after selection is finished
								 final Object topObject = event.getTopObject();
							     String	 giveEvent = event.getEventAction();
							     boolean give = mousePositionOnMap.contains(pickedPosition);
								 new Interaction(topObject,giveEvent,mousePositionOnMap,give);
					               
                                PointPlacemark hoveredPointPlacemark = (PointPlacemark) event.getTopObject();
                                
								pickedPosition = hoveredPointPlacemark.getPosition();
								System.out.println(mousePositionOnMap.contains(pickedPosition));

							} // ends logic for first type (if) statement

							if (event.getEventAction().equals(SelectEvent.DRAG_END)) {
								// it's possible to have a stack of objects at a given lat lon,
				                // this ensures you get the one that was really clicked on 
				               
								Position dragEndPosition = worldWindCanvas.getCurrentPosition();
								removeFromListAfterAction(mousePositionOnMap, pickedPosition, dragEndPosition);
								Object dragObject = event.getTopObject();

								dragObject = null;

								System.out.println("Drag start   " + pickedPosition);
								layer.dispose();
								drawPolyLines(mousePositionOnMap);
								drawMarkers(mousePositionOnMap);
								worldWindCanvas.getModel().getLayers().add(layer);
							}

							if (event.getEventAction().equals(SelectEvent.RIGHT_CLICK)) {

								removeFromListAfterScreen(mousePositionOnMap, pickedPosition);
								layer.dispose();
								drawPolyLines(mousePositionOnMap);
								drawMarkers(mousePositionOnMap);

								System.out.println("Removed succ");
							}
							

							// adding one more selection criteria
							else if (event.getEventAction().equals(SelectEvent.DRAG) && event.hasObjects()
									&& event.getTopObject() instanceof PointPlacemark) {

								// TODO

							} // end second if (else if) logic

						}// ends selected method

					});// ends select listeners

					// adding to window

					System.out.println("Current Pos= " + mouseCurrentPosition);

					drawPolyLines(mousePositionOnMap);
					drawMarkers(mousePositionOnMap);
					worldWindCanvas.getModel().getLayers().add(layer);

				} // ends if loop if position is not null

				else {

					System.out.println("NOTHING added this position is on list, or other reason");

				} // ends else loop it position is null

			}// ends click event

		});// ends mouse Listener

		// if(mousePositionOnMap.size()>0)drawView(mousePositionOnMap);

	}// ends first view Constructor

	private void drawPolyLines(List<Position> list) {
		if (list.size() > 1) {
			Polyline polyline = new Polyline(list);

			polyline.setColor(Color.RED);
			polyline.setLineWidth(2);
			layer.addRenderable(polyline);
		}

	}

	private void drawMarkers(List<Position> list) {

		for (Position pos : list) {
			PointPlacemark pointPlacemarkOnMapList = new PointPlacemark(pos);
			layer.addRenderable(pointPlacemarkOnMapList);

		}
	}

	private List<Position> removeFromListAfterAction(List<Position> list, Position pick, Position end) {
		if (list.contains(pick)) {
			System.out.println("Is found");
			int indexToReplace = list.indexOf(pick);
			list.set(indexToReplace, end);
		}

		return mousePositionOnMap;
	}

	private List<Position> removeFromListAfterScreen(List<Position> list, Position pick) {
		if (list.contains(pick)) {
			System.out.println("Is found");
			int indexToReplace = list.indexOf(pick);
			list.remove(indexToReplace);
		}

		return mousePositionOnMap;
	}
	
	

}// ends first view class

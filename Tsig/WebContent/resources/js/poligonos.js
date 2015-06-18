//VARIABLES GLOBALES

var bounds = new OpenLayers.Bounds(366582.582, 6127927, 858252.252, 6671738);


var mapoptions = {

	units : 'm',
	projection : "EPSG:32721",
	maxExtent : bounds,
	center : new OpenLayers.LonLat(574553.537, 6141267.75),
	maxResolution : 1000

};

var map, drawControls,drawPolygon;

var saveStrategy = new OpenLayers.Strategy.Save();
saveStrategy.events.on({
    'success': function(event) {
       //  alert('Changes saved');
         document.getElementById("botonGuardar").style.display = "none";
    },
    'fail': function(event) {
        // alert('Error! Changes not saved');
    },
    scope: this
});

/////////////////

function init() {
	
	document.getElementById("botonGuardar").style.display = "none";
		
    map = new OpenLayers.Map('map', mapoptions);
	// setup tiled layer
	var tiled = new OpenLayers.Layer.WMS(

	"mapabase", "http://localhost:8080/geoserver/sige/wms", {
		LAYERS : 'sige:mapabase',
		projection : new OpenLayers.Projection('EPSG:32721')
	}

	);

	
	
	
	polygonLayer = new OpenLayers.Layer.Vector("zonageom", {
		strategies : [ new OpenLayers.Strategy.BBOX(), saveStrategy ],
		projection : new OpenLayers.Projection("EPSG:32721"),
		protocol : new OpenLayers.Protocol.WFS({
			version : "1.1.0",
			srsName : "EPSG:32721",
			url : "http://localhost:8080/geoserver/wfs",
			featurePrefix : 'sige', // geoserver worspace name
			featureType : "zonageom", // geoserver Layer Name
			featureNS : "localhost:8080/geoserver/sige", // Edit Workspace
			// Namespace URI
			geometryName : "geom", // field in Feature Type details with type
		// "Geometry"

		}),
		
		 filter:"",
        /* styleMap: new OpenLayers.StyleMap(stylePoli)*/

	});

		
    map.addLayers([ tiled, polygonLayer]);    
    map.addControl(new OpenLayers.Control.LayerSwitcher());    
    map.zoomTo(7);
	
	
	drawPolygon = new OpenLayers.Control.DrawFeature(polygonLayer, OpenLayers.Handler.Polygon,{displayClass: 'olControlDrawFeaturePolygon'})
	map.addControl(drawPolygon);

	var panel = new OpenLayers.Control.Panel({
		displayClass : 'olControlEditingToolbar'
	});
	
	panel.addControls([ new OpenLayers.Control.Navigation(), drawPolygon ]);	
	map.addControl(panel);
	
	drawPolygon.featureAdded = function(feature) {	//drawControls[0] = polygon
		
		feature.attributes.nombre="ZonaPolygon";   
		feature.attributes.visitas=0; 
        feature.state = OpenLayers.State.INSERT;
        feature.layer.drawFeature(feature);
                
        drawPolygon.deactivate();
		OpenLayers.Util.removeItem(panel.controls, drawPolygon);
		panel.redraw();
		
		document.getElementById("botonGuardar").style.display = "block";
		
   }
	
}

function save() {
	
	saveStrategy.save();

}

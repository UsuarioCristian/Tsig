//VARIABLES GLOBALES

var bounds = new OpenLayers.Bounds(366582.582, 6127927, 858252.252, 6671738);

var saveStrategy = new OpenLayers.Strategy.Save();

var mapoptions = {

	units : 'm',
	projection : "EPSG:32721",
	maxExtent : bounds,
	center : new OpenLayers.LonLat(574553.537, 6141267.75),
	maxResolution : 1000

};

var map, drawControls,drawPolygon,casageom,aptos,polygonLayer;



/////////////////

function init() {
	

		
    map = new OpenLayers.Map('map', mapoptions);
	// setup tiled layer
	var tiled = new OpenLayers.Layer.WMS(

	"mapabase", "http://localhost:8080/geoserver/sige/wms", {
		LAYERS : 'sige:mapabase',
		projection : new OpenLayers.Projection('EPSG:32721')
	}

	);

    var stylePoli = new OpenLayers.Style(
	        // the first argument is a base symbolizer
	        // all other symbolizers in rules will extend this one
	        {
	        	 fillColor: '#00D100',
	        	 fillOpacity:1,
	        	 
	        	 strokeWidth: 2,
	        	
	        	 strokeColor: '#000000'
	           
	        },
	        // the second argument will include all rules
	        {
	            rules: [
	                new OpenLayers.Rule({
	                    // a rule contains an optional filter
	                    filter: new OpenLayers.Filter.Comparison({
	                        type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO,
	                        property: "visitas", // the "foo" feature attribute
	                        value: 0
	                    }),
	                    // if a feature matches the above filter, use this symbolizer
	                    symbolizer: {
	        	  
	                    	fillOpacity:0.1,
	                    }
	                }),
	                new OpenLayers.Rule({
	                    // a rule contains an optional filter
	                    filter: new OpenLayers.Filter.Comparison({
	                        type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO,
	                        property: "visitas", // the "foo" feature attribute
	                        value: 3
	                    }),
	                    // if a feature matches the above filter, use this symbolizer
	                    symbolizer: {
	        	  
	                    	fillOpacity:0.3,
	                    }
	                }) ,
	                new OpenLayers.Rule({
	                    // a rule contains an optional filter
	                    filter: new OpenLayers.Filter.Comparison({
	                        type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO,
	                        property: "visitas", // the "foo" feature attribute
	                        value: 10
	                    }),
	                    // if a feature matches the above filter, use this symbolizer
	                    symbolizer: {
	        	  
	                    	fillOpacity:0.6,
	                    }
	                }),
	                new OpenLayers.Rule({
	                    // a rule contains an optional filter
	                    filter: new OpenLayers.Filter.Comparison({
	                        type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO,
	                        property: "visitas", // the "foo" feature attribute
	                        value: 20
	                    }),
	                    // if a feature matches the above filter, use this symbolizer
	                    symbolizer: {
	        	  
	                    	fillOpacity:0.9,
	                    }
	                })

	            ]
	        }
	    );
	
 
    var styleAptos = new OpenLayers.Style(

	        {
	            graphicWidth: 50,
	            graphicHeight: 50,
	            externalGraphic:"resources/Images/apto.png",
	           
	        });

 var styleCasas = new OpenLayers.Style(
	        // the first argument is a base symbolizer
	        // all other symbolizers in rules will extend this one
	        {
	            graphicWidth: 40,
	            graphicHeight: 32,
	            externalGraphic:"resources/Images/casa.png",
	           
	        });			       
    aptos = new OpenLayers.Layer.Vector(
            "aptogeom",
            {
                strategies: [new OpenLayers.Strategy.BBOX()]
                , projection: new OpenLayers.Projection("EPSG:32721")
                 ,protocol: new OpenLayers.Protocol.WFS({
                    version: "1.1.0",
                    srsName: "EPSG:32721",
                    url: "http://localhost:8080/geoserver/wfs",
                    featurePrefix: 'sige', //geoserver worspace name
                    featureType: "aptogeom", //geoserver Layer Name
                    featureNS: "localhost:8080/geoserver/sige", // Edit Workspace Namespace URI
                    geometryName: "punto", // field in Feature Type details with type "Geometry"
                    
                }),

                filter:"",
                styleMap: new OpenLayers.StyleMap(styleAptos)
            
            });
		           
    casageom = new OpenLayers.Layer.Vector(
        "casageom",
        {
            strategies: [new OpenLayers.Strategy.BBOX()]
            , projection: new OpenLayers.Projection("EPSG:32721")
             ,protocol: new OpenLayers.Protocol.WFS({
                version: "1.1.0",
                srsName: "EPSG:32721",
                url: "http://localhost:8080/geoserver/wfs",
                featurePrefix: 'sige', //geoserver worspace name
                featureType: "casageom", //geoserver Layer Name
                featureNS: "localhost:8080/geoserver/sige", // Edit Workspace Namespace URI
                geometryName: "punto", // field in Feature Type details with type "Geometry"
               
            }),
            filter:"",
            styleMap: new OpenLayers.StyleMap(styleCasas)
        
        });

   
	polygonLayer = new OpenLayers.Layer.Vector("zonageom", {
		strategies : [ new OpenLayers.Strategy.BBOX(),saveStrategy ],
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
         styleMap: new OpenLayers.StyleMap(stylePoli)

	});


		
    map.addLayers([ tiled, polygonLayer,casageom,aptos]);  
    
  
    
    var deleteFeatureControl = new OpenLayers.Control.SelectFeature(polygonLayer, {
        clickout: false,
        toggle: false,
        title: "Delete",
        displayClass: "olControlDelete"
    });
    
    deleteFeatureControl.events.register("featurehighlighted", this, function(e) {
        if (confirm('Are you sure you want to delete this feature?')) {
        	 
        		var attr=  document.getElementById('zonas:idzona');
        		
        		attr.value = e.feature.attributes.id;
        	
        	
        	 
        	
        	polygonLayer.removeFeatures([e.feature]);
            deleteFeatureControl.deactivate();
            document.getElementById('zonas:borrar').click();
          
        } else {
            deleteFeatureControl.unselect(e.feature);
        }
    });
   
    var panel = new OpenLayers.Control.Panel({
        displayClass: 'olControlEditingToolbar'
    });
    
    panel.addControls([ deleteFeatureControl]);
    
    map.addControls([
        panel
    ]);


    map.addControl(new OpenLayers.Control.LayerSwitcher());    
    map.zoomTo(7);
	

		
}


function save() {
	saveStrategy.save();
		
}

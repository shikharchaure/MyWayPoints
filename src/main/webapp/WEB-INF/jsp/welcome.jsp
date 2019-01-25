<!-- This page displays the route and weather information to the user -->
<doctypehtml!>
    <html>
    <head>
    <title>Simple Map</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
         height: 70%;
      }
      h1 {background-color: powderblue;}
      /* Optional: Makes the sample page fill the window. */
     /*  html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      } */
    </style>
    <title>Register </title>
    <link rel="stylesheet" href="style.css" />
    </head>	
    <body>
    <div id="colorstrip">
    <font size="3"> 
     <h1 align="center">
     Welcome to MyWayPoints
     </h1> 
    </font>
    </div>
    <div class="topcorner">	
    <form method="post">
    <p>Start: <input type="text" name="start" placeholder=${ start }> &nbsp;&nbsp;&nbsp;&nbsp;
    End: <input type="text" name="end" placeholder=${ end }> <br><br>
      <input type="Submit" value="Submit" >
    </form>
    </div>
    <div id="map"></div>
      <script>
      //var map;
      /* Display map */
      function initMap() {
    	var myLatLng = {lat: -25.363, lng: 131.044};
    	var renderer = new google.maps.DirectionsRenderer({map});
  		var service = new google.maps.DirectionsService();
  		 console.log(${mark})
    	var locs=${latlon};
    	console.log('locs'+locs);
    	
    	var cities=${keys};
    	console.log('cities'+cities);
    	var arr=new Array();
    	console.log(locs)
    	var center = {lat: -25.363, lng: 131.044}      
          var map = new google.maps.Map(document.getElementById('map'), {
            center:myLatLng,
            zoom: 4
          }); 
    	 for(i=0;i<locs.length;i++){
    		 var position=new google.maps.LatLng(locs[i][0], locs[i][1])
    		 console.log('pos'+position)
    		 // foreach(var position:locs){
          var marker = new google.maps.Marker({
        	  position: position,
              map: map,
              title:cities[i]
            });
    	
    	  }
    		var response=${Response};
          var request=${Request};
    	  typecastRoutes(response.routes);
          console.log('request'+ JSON.stringify(request))
           response.request=request;
           renderer.setDirections(response);
           renderer.setMap(map);   
           renderer.setOptions({
           	suppressMarkers:true 
             });
    	 }	 
      function typecastRoutes(routes){
  	    routes.forEach(function(route){
  	        route.bounds = asBounds(route.bounds);
  	        route.overview_path = asPath(route.overview_polyline);
  	        route.legs.forEach(function(leg){
  	            leg.start_location = asLatLng(leg.start_location);
  	            leg.end_location   = asLatLng(leg.end_location);
  	            leg.steps.forEach(function(step){
  	                step.start_location = asLatLng(step.start_location);
  	                step.end_location   = asLatLng(step.end_location);
  	                step.path = asPath(step.polyline);
  	            });

  	        });
  	    });
  	}

  	function asBounds(boundsObject){
  	    return new google.maps.LatLngBounds(asLatLng(boundsObject.southwest),
  	                                    asLatLng(boundsObject.northeast));
  	}

  	function asLatLng(latLngObject){
  	    return new google.maps.LatLng(latLngObject.lat, latLngObject.lng);
  	}

  	function asPath(encodedPolyObject){
  	    return google.maps.geometry.encoding.decodePath( encodedPolyObject.points );
  	}
     	 
    	 
    </script>
    
    
    </form>
    <script src="https://maps.googleapis.com/maps/api/js?libraries=geometry&key=AIzaSyCO2l0fimWffHW7mIp9ScYkUGrolqhXhk0&callback=initMap"
    async defer></script>
    
    </body>
    </html>
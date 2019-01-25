<!-- This page is displayed when the user enters the applications for the first time--> 
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
        height: 75%;
      }
      #text { 
   width:300px; 
   float:right;
}
#button{
    height:20px; 
    width:100px; 
    margin: -20px -50px; 
    position:relative;
    top:50%; 
    left:50%;
}
h1 {background-color: powderblue;}
      /* Optional: Makes the sample page fill the window. */
      /* html, body {
        height: 90%;
        margin: 0;
        padding: 0;
      } */
    </style>
    <title>Register </title>
    <link rel="stylesheet" href="style.css" />
    </head>	
    <body>
    <font size="3"> 
     <h1 align="center" font color:"white">
     Welcome to MyWayPoints
     </h1> 
    </font>
    <div class="topcorner">	
    <form method="post">
    <p>Start: <input type="text" name="start"> &nbsp;&nbsp;&nbsp;&nbsp;
    End: <input type="text" name="end"> <br><br>
      <input type="Submit" value="Submit" >
    </form>
    </div>
    
    <div id="map"></div>
      <script>
      //var map;
      function initMap() {
    	var myLatLng = {lat: -25.363, lng: 131.044};
    	var renderer = new google.maps.DirectionsRenderer({map});
  		var service = new google.maps.DirectionsService();
    	var center = {lat:42.8859561, lng: -78.8783997}      
          var map = new google.maps.Map(document.getElementById('map'), {
            center:center,
            zoom: 5 	
          }); 
	 
      }
    	 
    </script>
    
    <!-- <div id="text"> -->
    
    <script src="https://maps.googleapis.com/maps/api/js?libraries=geometry&key=AIzaSyCO2l0fimWffHW7mIp9ScYkUGrolqhXhk0&callback=initMap"
    async defer></script>
    
    </body>
    </html>
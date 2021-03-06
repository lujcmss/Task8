<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<script>
	// This example adds a search box to a map, using the Google Place Autocomplete
	// feature. People can enter geographical searches. The search box will return a
	// pick list containing a mix of places and predicted search terms.
var locations = new Array;
	var titles = new Array;
	var mapTest = null;
	var map = null;
	var latsgn = 1;
	var lgsgn = 1;
	var marker = null;
	var posset = 0;
	var ls='';
	var lm='';
	var ld='';
	var lgs='';
	var lgm='';
	var lgd='';
	var mrks = {mvcMarkers: new google.maps.MVCArray()};
	var iw;
	var drag=false;
	function initialize() {

		var markers = [];
		
		var map = new google.maps.Map(document.getElementById('map-canvas'), {
			mapTypeId : google.maps.MapTypeId.ROADMAP
		});

		var defaultBounds = new google.maps.LatLngBounds(
				new google.maps.LatLng(-33.8902, 151.1759),
				new google.maps.LatLng(-33.8474, 151.2631));
		map.fitBounds(defaultBounds);

		// Create the search box and link it to the UI element.
		var input = /** @type {HTMLInputElement} */
		(document.getElementById('pac-input'));
		map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

		var searchBox = new google.maps.places.SearchBox(
		/** @type {HTMLInputElement} */
		(input));

		// [START region_getplaces]
		// Listen for the event fired when the user selects an item from the
		// pick list. Retrieve the matching places for that item.
		google.maps.event.addListener(searchBox, 'places_changed', function() {
			var places = searchBox.getPlaces();

			for (var i = 0, marker; marker = markers[i]; i++) {
				marker.setMap(null);
			}
			

			// For each place, get the icon, place name, and location.
			markers = [];
			var bounds = new google.maps.LatLngBounds();
			for (var i = 0, place; place = places[i]; i++) {
				var image = {
					url : place.icon,
					size : new google.maps.Size(71, 71),
					origin : new google.maps.Point(0, 0),
					anchor : new google.maps.Point(17, 34),
					scaledSize : new google.maps.Size(25, 25)
				};

				// Create a marker for each place.
				var marker = new google.maps.Marker({
					map : map,
					icon : image,
					title : "Hello",
					position : place.geometry.location
				});

				markers.push(marker);

				bounds.extend(place.geometry.location);
			}

			map.fitBounds(bounds);
		});
		// [END region_getplaces]

		// Bias the SearchBox results towards places that are within the bounds of the
		// current map's viewport.
		google.maps.event.addListener(map, 'bounds_changed', function() {
			var bounds = map.getBounds();
			searchBox.setBounds(bounds);
		});

		google.maps.event.addListener(map, 'click', function(e) {
			mapTest = map;

			placeMarker(e.latLng, map);
			document.getElementById("latbox").value = e.latLng.lat();
			document.getElementById("lonbox").value = e.latLng.lng();
		});
		



	
	}
	
	function showLatLong(latitude, longitude,title) {
		
		if (isNaN(latitude)) {alert(' Latitude must be a number. e.g. Use +/- instead of N/S'); return false;}
		if (isNaN(longitude)) {alert(' Longitude must be a number.  e.g. Use +/- instead of E/W'); return false;}

		latitude1 = Math.abs( Math.round(latitude * 1000000.));
		if(latitude1 > (90 * 1000000)) { alert(' Latitude must be between -90 to 90. ');  document.getElementById("latbox1").value=''; return;}
		longitude1 = Math.abs( Math.round(longitude * 1000000.));
		if(longitude1 > (180 * 1000000)) { alert(' Longitude must be between -180 to 180. ');  document.getElementById("lonbox1").value='';  return;}

		var point = new google.maps.LatLng(latitude,longitude);
		posset = 1;

	//	map.setMapTypeId(google.maps.MapTypeId.NORMAL);


		fc(point,title);
		computepos(point);
		}
	
	function fc(point,title)
	{
	gC(point);
	var map = mapTest;
	var html= "<div style='color:#000;background-color:#fff;padding:3px;width:150px;'><p>Latitude - Longitude:<br />" + String(point.toUrlValue()) + "<br /><br />Lat: " + ls +  "&#176; " + lm +  "&#39; "  + ld + "&#34;<br />Long: " + lgs +  "&#176; " + lgm +  "&#39; " + lgd + "&#34;</p></div>";
	var title2 = title;
 placeMarker(point,map,title2);
	
	
	}
	
	function gC(ll){
		var latA = Math.abs(Math.round(ll.lat() * 1000000.));
		var lonA = Math.abs(Math.round(ll.lng() * 1000000.));
		if(ll.lat() < 0)
		{
			var tls = '-' + Math.floor((latA / 1000000)).toString();
		}
		else
		{
			var tls = Math.floor((latA / 1000000)).toString();
		}
		var tlm = Math.floor(((latA/1000000) - Math.floor(latA/1000000)) * 60).toString();
		var tld = ( Math.floor(((((latA/1000000) - Math.floor(latA/1000000)) * 60) - Math.floor(((latA/1000000) - Math.floor(latA/1000000)) * 60)) * 100000) *60/100000 ).toString();
		ls = tls.toString();
		lm = tlm.toString();
		ld = tld.toString();

		if(ll.lng() < 0)
		{
		  var tlgs = '-' + Math.floor((lonA / 1000000)).toString();
		}
		else
		{
			var tlgs = Math.floor((lonA / 1000000)).toString();
		}
		var tlgm = Math.floor(((lonA/1000000) - Math.floor(lonA/1000000)) * 60).toString();
		var tlgd = ( Math.floor(((((lonA/1000000) - Math.floor(lonA/1000000)) * 60) - Math.floor(((lonA/1000000) - Math.floor(lonA/1000000)) * 60)) * 100000) *60/100000 ).toString();
		lgs = tlgs.toString();
		lgm = tlgm.toString();
		lgd = tlgd.toString();
		}
	
	
	function computepos (point)
	{
	var latA = Math.abs(Math.round(point.lat() * 1000000.));
	var lonA = Math.abs(Math.round(point.lng() * 1000000.));
	if(point.lat() < 0)
	{
		var ls = '-' + Math.floor((latA / 1000000)).toString();
	}
	else
	{
		var ls = Math.floor((latA / 1000000)).toString();
	}
	var lm = Math.floor(((latA/1000000) - Math.floor(latA/1000000)) * 60).toString();
	var ld = ( Math.floor(((((latA/1000000) - Math.floor(latA/1000000)) * 60) - Math.floor(((latA/1000000) - Math.floor(latA/1000000)) * 60)) * 100000) *60/100000 ).toString();
	if(point.lng() < 0)
	{
	  var lgs = '-' + Math.floor((lonA / 1000000)).toString();
	}
	else
	{
		var lgs = Math.floor((lonA / 1000000)).toString();
	}
	var lgm = Math.floor(((lonA/1000000) - Math.floor(lonA/1000000)) * 60).toString();
	var lgd = ( Math.floor(((((lonA/1000000) - Math.floor(lonA/1000000)) * 60) - Math.floor(((lonA/1000000) - Math.floor(lonA/1000000)) * 60)) * 100000) *60/100000 ).toString();
	document.getElementById("latbox").value=point.lat().toFixed(6);

	document.getElementById("lonbox").value=point.lng().toFixed(6);

	}
	
	
	
	function placeMarker(position,map,title) {
		
		var marker = new google.maps.Marker({
			position : position,
			map : map,
			title : title
		});
	
	}

	function reset() {
		location.reload(); 
		}

	
	
	
	function placeNew(){
		alert(locations[0]);
		for (var i=0 ; i < locations.length-1 ; i+=2) {

		    var lat = locations[i];
		    var lng = locations[i+1];

		    var myLatlng = new google.maps.LatLng(lat, lng);
		    var myOptions = {
		          zoom: 4,
		          center: myLatlng,
		          mapTypeId: google.maps.MapTypeId.ROADMAP
		    };
		    var marker = new google.maps.Marker({
		          position: myLatlng,
		          map: map,
		          title: "Fast marker"
		    });
		}
		
		
		
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>

<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<style>
body {
	font-size: 62.5%;
}

label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
<script>
	$(function() {
		var name = $("#name"), email = $("#email"), password = $("#password"), allFields = $(
				[]).add(name).add(email).add(password), tips = $(".validateTips");
		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}
		function checkLength(o, n, min, max) {
			if (o.val().length > max || o.val().length < min) {
				o.addClass("ui-state-error");
				updateTips("Length of " + n + " must be between " + min
						+ " and " + max + ".");
				return false;
			} else {
				return true;
			}
		}
		function checkRegexp(o, regexp, n) {
			if (!(regexp.test(o.val()))) {
				o.addClass("ui-state-error");
				updateTips(n);
				return false;
			} else {
				return true;
			}
		}
		$("#dialog-form").dialog(
				{
					autoOpen : false,
					height : 300,
					width : 350,
					modal : true,
					buttons : {
						"Create an account" : function() {
							var bValid = true;

							if (bValid) {
								$("#users tbody").append(
										"<tr>" + "<td>" + name.val() + "</td>"
												+ "<td>" + email.val()
												+ "</td>" + "<td>"
												+ password.val() + "</td>"
												+ "</tr>");
								$(this).dialog("close");
							}
						},
						Cancel : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						allFields.val("").removeClass("ui-state-error");
					}
				});
		$("#create-user").button().click(function() {
			$("#dialog-form").dialog("open");
		});
		$("#like").button().click(function() {

		});
		$("#dislike").button().click(function() {

		});
	});
</script>
<script>
	var flickrKey = "a25b5cda824a720c6316fec894353b3b";
	var flickrSecret = "da701b7803628a9d";
	var image = $("#imagecontainer");
	var flickrServiceURL = "http://api.flickr.com/services/rest/?";
	var mycars = new Array();
	var latitude = new Array();
	var longitude = new Array();
	var photoURL;
	var photoURLb;

	function imageFunction(lat2, lon2, tags2) {

		var tags = tags2;
		var lat = lat2;
		var lon = lon2;
		var url = flickrServiceURL
				+ "method=flickr.photos.search&api_key="
				+ flickrKey
				+ "&tags="
				+ tags
				+ "&has_geo=1&lat="
				+ lat
				+ "&lon="
				+ lon
				+ "&radius=32&sort=interestingness-desc&extras=url_l%2Cviews%2Cgeo%2Cdescription&tag_mode=all&per_page=30&format=json&nojsoncallback=1";
		$.getJSON(url, a);
		
	}
var pp = 0;
	function a(data) {
		var photos = data.photos.photo;
		
		
		$("#filter-container").empty();

		$.each(photos,function(index, photo) {
							var str = photo.title;
							
							locations[pp] = photo.latitude;
							locations[pp+1] = photo.longitude;
							
							showLatLong(photo.latitude,photo.longitude,photo.title);			

							if (str.length < 26) {
								photoURL = "http://farm" + photo.farm
										+ ".staticflickr.com/" + photo.server
										+ "/" + photo.id + "_" + photo.secret
										+ ".jpg";
								photoURLb = "http://farm" + photo.farm
										+ ".staticflickr.com/" + photo.server
										+ "/" + photo.id + "_" + photo.secret
										+ "_b.jpg";
		
								$("#filter-container").append("<figure class=web>"
														+ "<a href="+photoURLb+" target=_blank><img src="+photoURL+" alt=alt  class=image width=300px height=300px/><h3>"
														+ photo.title
														+ "</h3></a>"
														+ "<figcaption>"
														+ "<a href=project.html></a><br>"
														+ "<div id=dialog-form title=Create new user>"
														+ "		"
														+ "		<form method=post action=viewFlickr.do>"
														+ "<input type=hidden name=imageSourceOri value="+photoURLb+" />"
														+ "<input type=hidden name=imageSource value="+photoURL+" />"
														+ "<input type=hidden name=photoId value="+photo.id+" />"
														+ "<input type=hidden name=title value="+photo.title+" />"
														+ "<input type=hidden name=lat value=" + document.getElementById("latbox").value + " />"
														+ "<input type=hidden name=lon value=" + document.getElementById("lonbox").value + " />"
														+ "<input type=hidden name=tags value="+ document.getElementById("tags").value +" />"
														+ "		<fieldset>"
														+ "		<label for=name><h4>Share Comment!</h4></label>"
														+ "		<input type=text size= 10 name=comment id=name class=text ui-widget-content ui-corner-all>"
														+ "		</fieldset>"
														+ "		<button class='link-button green' type=submit name=button value=Comment alt=share><img src=/Task8/img/social/twitter_bird.png></button>"
														+ "		<button class='link-button blue' type=submit name=button alt=Like value=Like><img src=/Task8/img/social/heart.png></button>"
														+ "		<button class='link-button red' type=submit name=button alt =Dislike value=Dislike><img src=/Task8/img/social/feed_burner.png></button>"
														+ "		</form>"
														+ "		</div>"
														+ "</figcaption>"

														+ "</figure>");

							}

						});
		
		
	}
	
	
	
	window.onload = imageFunction(
			document.getElementById("latbox").value,
			document.getElementById("lonbox").value,
			document.getElementById("tags").value);
</script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script type="text/javascript"
	src="http://gettopup.com/releases/latest/top_up-min.js"></script>
<script type="text/javascript">
	var map = null;
	var latsgn = 1;
	var lgsgn = 1;
	var marker = null;
	var posset = 0;
	var ls = '';
	var lm = '';
	var ld = '';
	var lgs = '';
	var lgm = '';
	var lgd = '';
	var mrks = {
		mvcMarkers : new google.maps.MVCArray()
	};
	var iw;
	var drag = false;
	var photoURL;
	var title;

	var flickrKey = "a25b5cda824a720c6316fec894353b3b";
	var flickrSecret = "da701b7803628a9d";
	var image = $("#imagecontainer");
	var flickrServiceURL = "http://api.flickr.com/services/rest/?";
	var mycars = new Array();
	var latitude = new Array();
	var longitude = new Array();
	var i = 0;

	function xz() {

		ll = new google.maps.LatLng(20.0, -10.0);
		zoom = 2;

		var mO = {
			scaleControl : true,
			zoom : zoom,
			zoomControl : true,
			zoomControlOptions : {
				style : google.maps.ZoomControlStyle.LARGE
			},
			center : ll,
			disableDoubleClickZoom : true,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("map"), mO);
		map.setTilt(0);
		map.panTo(ll);
		marker = new google.maps.Marker({});

		google.maps.event
				.addListener(
						marker,
						'click',
						function(mll) {
							gC(mll.latLng);
							var html = "<div style='color:#000;background-color:#fff;padding:5px;width:150px;'><p>Latitude - Longitude:<br />"
									+ String(mll.latLng.toUrlValue())
									+ "<br /><br />Lat: "
									+ ls
									+ "&#176; "
									+ lm
									+ "&#39; "
									+ ld
									+ "&#34;<br />Long: "
									+ lgs
									+ "&#176; "
									+ lgm
									+ "&#39; "
									+ lgd
									+ "&#34;</p></div>";
							iw = new google.maps.InfoWindow({
								content : html
							});
							iw.open(map, marker);
						});
		google.maps.event.addListener(marker, 'dragstart', function() {
			if (iw) {
				iw.close();
			}
		});

		google.maps.event.addListener(marker, 'dragend', function(event) {
			posset = 1;

			map.setCenter(event.latLng);
			computepos(event.latLng);
			drag = true;
			setTimeout(function() {
				drag = false;
			}, 250);
		});

		google.maps.event.addListener(map, 'click', function(event) {
			if (drag) {
				return;
			}
			posset = 1;
			fc(event.latLng);

			map.panTo(event.latLng);
			computepos(event.latLng);
		});

	}

	function computepos(point) {
		var latA = Math.abs(Math.round(point.lat() * 1000000.));
		var lonA = Math.abs(Math.round(point.lng() * 1000000.));
		if (point.lat() < 0) {
			var ls = '-' + Math.floor((latA / 1000000)).toString();
		} else {
			var ls = Math.floor((latA / 1000000)).toString();
		}
		var lm = Math.floor(
				((latA / 1000000) - Math.floor(latA / 1000000)) * 60)
				.toString();
		var ld = (Math
				.floor(((((latA / 1000000) - Math.floor(latA / 1000000)) * 60) - Math
						.floor(((latA / 1000000) - Math.floor(latA / 1000000)) * 60)) * 100000) * 60 / 100000)
				.toString();
		if (point.lng() < 0) {
			var lgs = '-' + Math.floor((lonA / 1000000)).toString();
		} else {
			var lgs = Math.floor((lonA / 1000000)).toString();
		}
		var lgm = Math.floor(
				((lonA / 1000000) - Math.floor(lonA / 1000000)) * 60)
				.toString();
		var lgd = (Math
				.floor(((((lonA / 1000000) - Math.floor(lonA / 1000000)) * 60) - Math
						.floor(((lonA / 1000000) - Math.floor(lonA / 1000000)) * 60)) * 100000) * 60 / 100000)
				.toString();
		document.getElementById("latbox").value = point.lat().toFixed(6);

		document.getElementById("lonbox").value = point.lng().toFixed(6);

	}

	function showAddress(address) {
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				map.setCenter(results[0].geometry.location);
				map.setMapTypeId(google.maps.MapTypeId.HYBRID);

				marker.setPosition(results[0].geometry.location);
				posset = 1;
				computepos(results[0].geometry.location);
			} else {
				alert("Geocode was not successful for the following reason: "
						+ status);
			}
		});
	}
	var count = 0;
	function showLatLong(latitude, longitude) {
		if (isNaN(latitude)) {
			alert(' Latitude must be a number. e.g. Use +/- instead of N/S');
			return false;
		}
		if (isNaN(longitude)) {
			alert(' Longitude must be a number.  e.g. Use +/- instead of E/W');
			return false;
		}

		latitude1 = Math.abs(Math.round(latitude * 1000000.));
		if (latitude1 > (90 * 1000000)) {
			alert(' Latitude must be between -90 to 90. ');
			document.getElementById("latbox1").value = '';
			return;
		}
		longitude1 = Math.abs(Math.round(longitude * 1000000.));
		if (longitude1 > (180 * 1000000)) {
			alert(' Longitude must be between -180 to 180. ');
			document.getElementById("lonbox1").value = '';
			return;
		}

		var point = new google.maps.LatLng(latitude, longitude);
		posset = 1;

		map.setMapTypeId(google.maps.MapTypeId.NORMAL);

		map.setCenter(point);
		fc(point);
		computepos(point);
	}

	function showLatLong1(latitude, latitudem, latitudes, longitude,
			longitudem, longitudes) {
		if (isNaN(latitude)) {
			alert(' Latitude must be a number. e.g. Use +/- instead of N/S');
			return false;
		}
		if (isNaN(latitudem)) {
			alert(' Latitude must be a number. e.g. Use +/- instead of N/S');
			return false;
		}
		if (isNaN(latitudes)) {
			alert(' Latitude must be a number. e.g. Use +/- instead of N/S');
			return false;
		}
		if (isNaN(longitude)) {
			alert(' Longitude must be a number.  e.g. Use +/- instead of E/W');
			return false;
		}
		if (isNaN(longitudem)) {
			alert(' Longitude must be a number.  e.g. Use +/- instead of E/W');
			return false;
		}
		if (isNaN(longitudes)) {
			alert(' Longitude must be a number.  e.g. Use +/- instead of E/W');
			return false;
		}

		if (latitude.indexOf('-') < 0) {
			latsgn = 1;
		} else {
			latsgn = -1;
		}
		alat = Math.abs(Math.round(latitude * 1000000.));
		if (alat > (90 * 1000000)) {
			alert(' Degrees Latitude must be between -90 to 90. ');
			document.getElementById("latbox1m").value = '';
			document.getElementById("latbox1md").value = '';
			document.getElementById("latbox1ms").value = '';
			return;
		}
		latitudem = Math.abs(Math.round(latitudem * 1000000.) / 1000000); //integer
		absmlat = Math.abs(Math.round(latitudem * 1000000.)); //integer
		if (absmlat >= (60 * 1000000)) {
			alert(' Minutes Latitude must be between 0 to 59. ');
			document.getElementById("latbox1md").value = '';
			document.getElementById("latbox1ms").value = '';
			return;
		}
		latitudes = Math.abs(Math.round(latitudes * 1000000.) / 1000000);
		absslat = Math.abs(Math.round(latitudes * 1000000.));
		if (absslat > (59.99999999 * 1000000)) {
			alert(' Seconds Latitude must be between 0 and 59.99. ');
			document.getElementById("latbox1ms").value = '';
			return;
		}

		if (longitude.indexOf('-') < 0) {
			lgsgn = 1;
		} else {
			lgsgn = -1;
		}
		alon = Math.abs(Math.round(longitude * 1000000.));
		if (alon > (180 * 1000000)) {
			alert(' Degrees Longitude must be between -180 to 180. ');
			document.getElementById("lonbox1m").value = '';
			document.getElementById("lonbox1md").value = '';
			document.getElementById("lonbox1ms").value = '';
			return;
		}
		longitudem = Math.abs(Math.round(longitudem * 1000000.) / 1000000);
		absmlon = Math.abs(Math.round(longitudem * 1000000));
		if (absmlon >= (60 * 1000000)) {
			alert(' Minutes Longitude must be between 0 to 59. ');
			document.getElementById("lonbox1md").value = '';
			document.getElementById("lonbox1ms").value = '';
			return;
		}
		longitudes = Math.abs(Math.round(longitudes * 1000000.) / 1000000);
		absslon = Math.abs(Math.round(longitudes * 1000000.));
		if (absslon > (59.99999999 * 1000000)) {
			alert(' Seconds Longitude must be between 0 and 59.99. ');
			document.getElementById("lonbox1ms").value = '';
			return;
		}

		latitude = Math.round(alat + (absmlat / 60.) + (absslat / 3600.))
				* latsgn / 1000000;
		longitude = Math.round(alon + (absmlon / 60) + (absslon / 3600))
				* lgsgn / 1000000;
		var point = new google.maps.LatLng(latitude, longitude);
		posset = 1;

		map.setMapTypeId(google.maps.MapTypeId.HYBRID);
		map.setCenter(point);
		fc(point);
		computepos(point);
	}

	function streetview() {
		if (posset == 0) {
			alert("Position Not Set.  Please click on the spot on the map to set the street view point.");
			return;
		}

		var point = map.getCenter();
		var t1 = String(point);
		t1 = t1.replace(/[() ]+/g, "");
		var str = "http://www.satelliteview.co/?e=" + t1
				+ ":0:Latitude-Longitude Point:sv:0";
		var popup = window.open(str, "llwindow");
		popup.focus();
	}

	function googleearth() {
		if (posset == 0) {
			alert("Position Not Set.  Please click on the spot on the map to set the Google Earth map point.");
			return;
		}
		var point = map.getCenter();
		var gearth_str = "/?r=googleearth&mt=Latitude-Longitude Point&ml="
				+ point.lat() + "&mg=" + point.lng();
		var popup = window.open(gearth_str, "llwindow");
		popup.focus();
	}

	function pictures() {
		if (posset == 0) {
			alert("Position Not Set.  Please click on the spot on the map to set the photograph point.");
			return;
		}
		var point = map.getCenter();
		var pictures_str = "http://www.picturepastime.com?r=pictures&mt=Latitude-Longitude Point&ml="
				+ point.lat() + "&mg=" + point.lng();
		var popup = window.open(pictures_str, "llwindow");
		popup.focus();
	}

	function lotsize() {
		if (posset == 0) {
			alert("Position Not Set.  Please click on the spot on the map to set the lot size map point.");
			return;
		}
		var point = map.getCenter();
		var t1 = String(point);
		t1 = t1.replace(/[() ]+/g, "");
		var lotsize_str = "http://www.satelliteview.co/?e=" + t1
				+ ":0:Latitude-Longitude Point:measure:0";
		var popup = window.open(lotsize_str, "llwindow");
		popup.focus();
	}

	function getaddress() {
		if (posset == 0) {
			alert("Position Not Set.  Please click on the spot on the map to set the get address map point.");
			return;
		}
		var point = map.getCenter();
		var t1 = String(point);
		t1 = t1.replace(/[() ]+/g, "");
		var getaddr_str = "http://www.satelliteview.co/?e=" + t1
				+ ":0:Latitude-Longitude Point:map:0";
		var popup = window.open(getaddr_str, "llwindow");
		popup.focus();
	}

	function fc(point) {
		gC(point);
		var html = "<div style='color:#000;background-color:#fff;padding:3px;width:150px;'><p>Latitude - Longitude:<br />"
				+ String(point.toUrlValue())
				+ "<br /><br />Lat: "
				+ ls
				+ "&#176; "
				+ lm
				+ "&#39; "
				+ ld
				+ "&#34;<br />Long: "
				+ lgs
				+ "&#176; " + lgm + "&#39; " + lgd + "&#34;</p></div>";
		var iw = new google.maps.InfoWindow({
			content : html
		});
		var marker = new google.maps.Marker({
			position : point,
			map : map,
			icon : '/Task8/images/dance_class.png',
			draggable : true
		});
		mrks.mvcMarkers.push(marker);
		google.maps.event
				.addListener(
						marker,
						'click',
						function(event) {
							gC(event.latLng);
							var html = "<div style='color:#000;background-color:#fff;padding:3px;width:150px;'>Picture Title:"
									+ mycars[count] + "</div>";
							var iw = new google.maps.InfoWindow({
								content : html
							});
							iw.open(map, marker);
							computepos(event.latLng);
						});
	}

	function rL() {
		var marker = mrks.mvcMarkers.pop();
		if (marker) {
			marker.setMap(null);
			document.getElementById("latbox").value = '';
			document.getElementById("latboxm").value = '';
			document.getElementById("latboxmd").value = '';
			document.getElementById("latboxms").value = '';
			document.getElementById("lonbox").value = '';
			document.getElementById("lonboxm").value = '';
			document.getElementById("lonboxmd").value = '';
			document.getElementById("lonboxms").value = '';
		}
	}

	function gC(ll) {
		var latA = Math.abs(Math.round(ll.lat() * 1000000.));
		var lonA = Math.abs(Math.round(ll.lng() * 1000000.));
		if (ll.lat() < 0) {
			var tls = '-' + Math.floor((latA / 1000000)).toString();
		} else {
			var tls = Math.floor((latA / 1000000)).toString();
		}
		var tlm = Math.floor(
				((latA / 1000000) - Math.floor(latA / 1000000)) * 60)
				.toString();
		var tld = (Math
				.floor(((((latA / 1000000) - Math.floor(latA / 1000000)) * 60) - Math
						.floor(((latA / 1000000) - Math.floor(latA / 1000000)) * 60)) * 100000) * 60 / 100000)
				.toString();
		ls = tls.toString();
		lm = tlm.toString();
		ld = tld.toString();

		if (ll.lng() < 0) {
			var tlgs = '-' + Math.floor((lonA / 1000000)).toString();
		} else {
			var tlgs = Math.floor((lonA / 1000000)).toString();
		}
		var tlgm = Math.floor(
				((lonA / 1000000) - Math.floor(lonA / 1000000)) * 60)
				.toString();
		var tlgd = (Math
				.floor(((((lonA / 1000000) - Math.floor(lonA / 1000000)) * 60) - Math
						.floor(((lonA / 1000000) - Math.floor(lonA / 1000000)) * 60)) * 100000) * 60 / 100000)
				.toString();
		lgs = tlgs.toString();
		lgm = tlgm.toString();
		lgd = tlgd.toString();
	}

	function reset() {
		mrks.mvcMarkers.forEach(function(elem, index) {
			elem.setMap(null);
		});
		mrks.mvcMarkers.clear();

		marker.setPosition(map.getCenter());
	}

	function reset1() {
		marker.setPosition(map.getCenter());
		computepos(map.getCenter());
	}

	function imageFunction(lat2, lon2, tags2) {
		reset();

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
				+ "&radius=32&sort=interestingness-desc&extras=url_m%2Cviews%2Cgeo&tag_mode=all&per_page=10&format=json&nojsoncallback=1";
		$.getJSON(url, a);
		alert(url);

	}

	function a(data) {
		var photos = data.photos.photo;
		$(".clase1").empty();
		$.each(photos, function(index, photo) {
			photoURL = "http://farm" + photo.farm + ".staticflickr.com/"
					+ photo.server + "/" + photo.id + "_" + photo.secret
					+ "_m.jpg";
			var photoURLb = "http://farm" + photo.farm + ".staticflickr.com/"
					+ photo.server + "/" + photo.id + "_" + photo.secret
					+ "_b.jpg";
			mycars[i] = photo.title;

			latitude[i] = photo.latitude;
			longitude[i] = photo.longitude;

			var imagen = $('<img></img>');
			var mybutton = $('<input/>');
			var myform = $('<form><form/>');
			var mydiv = $('<div></div>')

			var a = $('<a></a>');
			a.attr("href", photoURLb);
			a.addClass("top_up");

			myform.attr("id", photo.id);
			imagen.attr("src", photoURL);
			imagen.attr("title", photo.title);
			a.append(imagen);
			mydiv.append(photo.title + photo.views);

			showLatLong(photo.latitude, photo.longitude);
			mybutton.click(function() {
				$('[name="urlClicked"]').val($(this).data("imageURL"));
			});
			$(".clase1").append(myform).append(mydiv);
			$("#" + photo.id).append(a);
			i++;
		});

		map.setMapTypeId(google.maps.MapTypeId.NORMAL);

	}

	window.onload = xz;
</script>

<script type="text/javascript" src="http://gettopup.com/releases/latest/top_up-min.js"></script>

<script type="text/javascript">
	var flickrKey = "a25b5cda824a720c6316fec894353b3b";
	var flickrSecret = "da701b7803628a9d";
	var flickrServiceURL = "http://api.flickr.com/services/rest/?";
	var count = "10";

	//http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5fe90bd111bfe2fc9686ff04f7860bcf&tags=bobmarley&per_page=3&format=json&nojsoncallback=1
	function imageFunction() {
		var url = flickrServiceURL + "method=flickr.interestingness.getList"
			+ "&api_key=" + flickrKey + "&extras=tags%2Cviews%2Curl_m%2Cdescription"
			+ "&per_page=10&format=json&nojsoncallback=1";
		$.getJSON(url, a);
	}

	function a(data) {
		var photos = data.photos.photo;
		$.each(photos, function(index, photo) {

			var photoURL = "http://farm" + photo.farm + ".staticflickr.com/"
					+ photo.server + "/" + photo.id + "_" + photo.secret
					+ ".jpg";
			var photoURLori = "http://farm" + photo.farm + ".staticflickr.com/"
			+ photo.server + "/" + photo.id + "_" + photo.secret
			+ "_b.jpg";

			var image = $('<img></img>');
			var mya = $('<a></a>');
			var mydiv = $('<div></div>');

			image.attr("src", photoURL);
			mya.attr("id", "a"+photo.id);
			mya.addClass("top_up");
			mya.attr("href", photoURLori);

			mydiv.append("<tr><td>title :</td><td>" + photo.title
					+ "</td></tr><tr><td>views: </td><td>" + photo.views
					+ "</td></tr><tr><td>description :</td><td>"
					+ photo.description._content + "</td></tr>");
			
			$("#imagecontainer").append(mya).append(mydiv);
			$("#a"+photo.id).append(image);
		});
	}

	window.onload = imageFunction;
</script>
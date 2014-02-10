<script src="/Task8/js/jquery-2.1.0.min.js"></script>

<script>
	var flickrKey = "a25b5cda824a720c6316fec894353b3b";
	var flickrSecret = "da701b7803628a9d";
	var image = $("#imagecontainer");
	var flickrServiceURL = "http://api.flickr.com/services/rest/?";
	var count = "10";

	//http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5fe90bd111bfe2fc9686ff04f7860bcf&tags=bobmarley&per_page=3&format=json&nojsoncallback=1
	function imageFunction() {
		var tags = document.getElementById("tags").innerHTML;
		var url = flickrServiceURL + "method=flickr.photos.search&api_key="
				+ flickrKey + "&text=" + tags
				+ "&per_page=" + count + "&format=json&nojsoncallback=1";
		$.getJSON(url, a);
	}

	function a(data) {
		var photos = data.photos.photo;
		$.each(photos, function(index, photo) {

			var photoURL = "http://farm" + photo.farm + ".staticflickr.com/"
					+ photo.server + "/" + photo.id + "_" + photo.secret
					+ "_q.jpg";

			var imagen = $('<img></img>');
			var myform = $('<form></form>');
			var myinput = $('<input/>');
			var myhidden = $('<input/>');
			var mybutton = $('<input/>');
			
			imagen.attr("src", photoURL);
			myform.attr("id", photo.id);
			myform.attr("method", "POST");
			myform.attr("action", "commentFlickr.do");
			myinput.attr("type", "text");
			myinput.attr("name", "comment");
			myhidden.attr("type", "hidden");
			myhidden.attr("name", "imageSource");
			myhidden.attr("value", photoURL);
			mybutton.attr("type", "submit");
			mybutton.attr("name", "button");
			mybutton.attr("value", "Comment");
			
			$("#imagecontainer").append(myform);
			$("#"+photo.id).append(imagen).append(myinput)
				.append(myhidden).append(mybutton);
		})
	}

	window.onload = imageFunction;
</script>
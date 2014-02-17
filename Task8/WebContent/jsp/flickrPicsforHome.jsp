<script class="cssdeck">
	var flickrKey = "a25b5cda824a720c6316fec894353b3b";
	var flickrSecret = "da701b7803628a9d";
	var flickrServiceURL = "http://api.flickr.com/services/rest/?";
	var count = "15";

	//http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=5fe90bd111bfe2fc9686ff04f7860bcf&tags=bobmarley&per_page=3&format=json&nojsoncallback=1
	function homeImageFunction() {
		var url = flickrServiceURL + "method=flickr.interestingness.getList"
				+ "&api_key=" + flickrKey
				+ "&extras=tags%2Cviews%2Curl_b%2Cdescription" + "&per_page="
				+ count + "&format=json&nojsoncallback=1";
		$.getJSON(url, getimgfromurl);

	}
	var photoURL;
	var photoURLori;
	function getimgfromurl(data) {

		var photos = data.photos.photo;
		$
				.each(
						photos,
						function(index, photo) {
							photoURL = "https://farm" + photo.farm
									+ ".staticflickr.com/" + photo.server + "/"
									+ photo.id + "_" + photo.secret + "_b.jpg";
							photoURLori = "https://farm" + photo.farm
									+ ".staticflickr.com/" + photo.server + "/"
									+ photo.id + "_" + photo.secret + "_m.jpg";

							$("#image_slider")
									.append(
											"<li><img src="+photoURL+" class = image width=940px height=500px /> <h2 color=white><span>WELCOME!&nbsp;<br/>&nbsp;Locate, Explore, and Share!<br /></h2></li>");

						});
		init();
	}

	var ul;
	var li_items;
	var li_number;
	var image_number = 0;
	var slider_width = 0;
	var image_width;
	var current = 0;
	function init() {

		ul = document.getElementById('image_slider');
		li_items = ul.children;
		li_number = li_items.length;
		for (var i = 0; i < li_number; i++) {
			// nodeType == 1 means the node is an element.
			// in this way it's a cross-browser way.
			//if (li_items[i].nodeType == 1){
			//clietWidth and width???
			image_width = li_items[i].childNodes[0].clientWidth;
			slider_width += image_width;
			image_number++;
		}

		ul.style.width = parseInt(slider_width) + 'px';
		slider(ul);
	}

	function slider() {
		animate({
			delay : 17,
			duration : 3000,
			delta : function(p) {
				return Math.max(0, -1 + 2 * p)
			},
			step : function(delta) {
				ul.style.left = '-'
						+ parseInt(current * image_width + delta * image_width)
						+ 'px';
			},
			callback : function() {
				current++;
				if (current < li_number - 1) {
					slider();
				} else {
					var left = (li_number - 1) * image_width;
					setTimeout(function() {
						goBack(left)
					}, 2000);
					setTimeout(slider, 4000);
				}
			}
		});
	}
	function goBack(left_limits) {
		current = 0;
		setInterval(function() {
			if (left_limits >= 0) {
				ul.style.left = '-' + parseInt(left_limits) + 'px';
				left_limits -= image_width / 10;
			}
		}, 17);
	}
	function animate(opts) {
		var start = new Date;
		var id = setInterval(function() {
			var timePassed = new Date - start;
			var progress = timePassed / opts.duration
			if (progress > 1) {
				progress = 1;
			}
			var delta = opts.delta(progress);
			opts.step(delta);
			if (progress == 1) {
				clearInterval(id);
				opts.callback();
			}
		}, opts.dalay || 17);
	}

	$(function() {

		$("h2").wrapInner("<span>");

		$("h2 br").before("<span class='spacer'>").before(
				"<span class='spacer'>").before("<span class='spacer'>").after(
				"<span class='spacer'>");

	});

	window.onload = homeImageFunction;
</script>

<script src="/assets/js/prefixfree.min.js"></script>
<script type="text/javascript">
</script>
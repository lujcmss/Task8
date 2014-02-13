<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="http://gettopup.com/releases/latest/top_up-min.js"></script>
<script type="text/javascript">

var flickrKey ="a25b5cda824a720c6316fec894353b3b";
var flickrSecret ="da701b7803628a9d";
var image =$("#imagecontainer");
var flickrServiceURL = "http://api.flickr.com/services/rest/?";
var mycars = new Array();
var latitude = new Array();
var longitude = new Array();
var i = 0;

function imageFunction(){
    var tags ="apple";
    var url = flickrServiceURL+"method=flickr.photos.search&api_key="+flickrKey+"&tags="+tags+"&has_geo=1&in_gallery=true&extras=url_o%2Cgeo&tag_mode=all&per_page=10&format=json&nojsoncallback=1";
    $.getJSON(url,a);
    
}

function a(data){
    var photos = data.photos.photo;
    $.each(photos,function(index, photo){
           var photoURL = "http://farm"+photo.farm+".staticflickr.com/"+photo.server+"/"+photo.id+"_"+photo.secret+"_m.jpg";
           var photoURLb = "http://farm"+photo.farm+".staticflickr.com/"+photo.server+"/"+photo.id+"_"+photo.secret+"_b.jpg";
           mycars[i] = photo.title;
           
           latitude [i] = photo.latitude;
           longitude[i] = photo.longitude;
           
           var imagen = $('<img></img>');
           var mybutton =$('<input/>');
           var myform =$('<form><form/>');
           
           var a = $('<a></a>');
           a.attr("href",photoURLb);
           a.addClass("top_up");
           
           myform.attr("id",photo.id);
           imagen.attr("src",photoURL);
           imagen.attr("title",photo.title);
           a.append(imagen);
           
           mybutton.click(function(){
                          $('[name="urlClicked"]').val($(this).data("imageURL"));
                          });
           $(".clase1").append(myform);
           $("#"+photo.id).append(a);
           i++;
    });

    var geoData = google.visualization.arrayToDataTable([
                                                         ['Lat', 'Lon', 'Title',],
                                                         [latitude[0], longitude[0] , mycars[0]],
                                                         [latitude[1], longitude[1] , mycars[1]],
                                                         [latitude[2], longitude[2] , mycars[2]],
                                                         [latitude[3], longitude[3] , mycars[3]],
                                                         [latitude[4], longitude[4] , mycars[4]],
                                                         [latitude[5], longitude[5] , mycars[5]],
                                                         [latitude[6], longitude[6] , mycars[6]],
                                                         [latitude[7], longitude[7] , mycars[7]],
                                                         [latitude[8], longitude[8] , mycars[8]],
                                                         [latitude[9], longitude[9] , mycars[9]],
                                                         ]);
    
    var geoView = new google.visualization.DataView(geoData);
    geoView.setColumns([0, 1]);
    
    var table =
    new google.visualization.Table(document.getElementById('table_div'));
    table.draw(geoData, {showRowNumber: false});
    
    var map =
    new google.visualization.Map(document.getElementById('map_div'));
    map.draw(geoView, {showTip: true, mapType:'normal', useMapTypeControl: true});
    
    google.visualization.events.addListener(table, 'select',
                                            function() {
                                            map.setSelection(table.getSelection());
                                            });
    google.visualization.events.addListener(map, 'select',
                                            function() {
                                            table.setSelection(map.getSelection());
                                            });
    
}

window.onload = imageFunction;
google.load('visualization', '1', {'packages': ['table', 'map']});
function draw(response) {
    if (response.isError()) {
        alert('Error in query');
    }
}
</script>
<script type="text/javascript">
google.setOnLoadCallback(drawVisualization);
</script>

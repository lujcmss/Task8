<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript"
	src="http://gettopup.com/releases/latest/top_up-min.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- page Viewed per Day -->
<script type="text/javascript">
	function drawViewPerDay() {
		// Create and populate the data table.
		var data = new google.visualization.DataTable();

		data.addColumn('string', 'Date');
		var pageslength = 0;
		<c:forEach var="item" items='${pages}'>
			data.addColumn('number', '${item}');
			pageslength += 1;
		</c:forEach>
		
		var visits = new Array();
		<c:forEach var="item" items='${visits}'>
			visits.push('${item}');
		</c:forEach>

		var row = 0;
		<c:forEach var="item" items='${dates}'>
			var tmp = 0;
			data.addRow();
			data.setCell(row, tmp, '${item}');

			<c:forEach var="page" items='${pages}'>
				data.setCell(row, tmp + 1, visits[row * pageslength + tmp]);
				tmp += 1;
			</c:forEach>
			row += 1;
		</c:forEach>

		// Create and draw the visualization.
		new google.visualization.LineChart(document.getElementById('ViewPerDay'))
				.draw(data, {
					title : "Page Viewed per Day",
					curveType: "function",
					width : 1000,
					height : 400,
					is3D: true,
					backgroundColor : 'transparent',
					fontName: 'Voltaire, sans-serif',
					vAxis : {
						title : "Pages & Dates"
					},
					hAxis : {
						title : "View (Times)"
					}
				});
	}

	google.setOnLoadCallback(drawViewPerDay);
</script>

<!-- Top five comment users -->
<script>
	function drawTopCommentUsers() {
		// Create and populate the data table.
		var data = new google.visualization.DataTable();

		data.addColumn('string', 'User');
		data.addColumn('number', 'Comments');
		
		var row = 0;
		<c:forEach var="item" items="${commentGraph}">
			data.addRow();
			data.setCell(row, 0, '${item.username}');
			data.setCell(row, 1, '${item.count}');
			row += 1;
		</c:forEach>

		// Create and draw the visualization.
		new google.visualization.PieChart(document
				.getElementById('TopCommentUsers')).draw(data, {
			title : "Top Comment Users",
			width : 1000,
			height : 400,
			is3D: true,
			backgroundColor : 'transparent',
			fontName: 'Voltaire, sans-serif',
		});
	}

	google.setOnLoadCallback(drawTopCommentUsers);
</script>

<script type="text/javascript">
	google.load('visualization', '1', {
		'packages' : [ 'table', 'map', 'corechart' ]
	});
	google.setOnLoadCallback(initialize);

	function initialize() {
		// The URL of the spreadsheet to source data from.
		var query = new google.visualization.Query(
				'https://spreadsheets.google.com/pub?key=pCQbetd-CptF0r8qmCOlZGg');
		query.send(draw);
	}

	function draw(response) {
		if (response.isError()) {
			alert('Error in query');
		}
	}
</script>
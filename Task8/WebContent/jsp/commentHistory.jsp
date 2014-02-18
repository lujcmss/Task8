<jsp:include page="header.jsp" />

</div>
</header>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<jsp:include page="success-list.jsp" />
<jsp:include page="error-list.jsp" />
<c:choose>
	<c:when test="${page=='mostLike'}">
		<h3 align="center">Top Liked</h3>
	</c:when>
	<c:when test="${page=='mostDislike'}">
		<h3 align="center">Top Disliked</h3>
	</c:when>
	<c:otherwise>
		<h3 align="center">Top Comments</h3>
	</c:otherwise>
</c:choose>
<!-- MAIN -->

<form method="POST" action="commentHistory.do" align="center">
	<br>
	<button class="link-button green" type="submit" name="button"
		value="mostComment">Top Comments</button>
	<button class="link-button blue" type="submit" name="button"
		value="mostLike">Like</button>
	<button class="link-button red" type="submit" name="button"
		value="mostDislike">Disliked</button>
</form>

<div id="main">
	<div class="wrapper cf">

		<!-- featured -->

		<!-- Filter container -->
		<div id="filter-container" class="cf">

			<c:forEach var="item" items="${ topBean }">
				<figure>
					<a href="${ item.imageSourceOri }" class=top_up><img
						src="${ item.imageSource }" alt=alt class=image width=300px
						height=300px />
						<h3>"${ item.title }"</h3>
						<br></a>
					<h3>
						<c:choose>
							<c:when test="${page=='mostLike'}">
								Liked Times:<c:out value="${ item.count }" />
							</c:when>
							<c:when test="${page=='mostDislike'}">
								Disliked Times:<c:out value="${ item.count }" />
							</c:when>
							<c:otherwise>
								Commented Times:<c:out value="${ item.count }" />
							</c:otherwise>
						</c:choose>
					</h3>
					<figcaption>
						<a href="project.html"></a><br>
						<div id="dialog-form">

							<form method="post" action="commentHistory.do">
								<input type="hidden" name="imageSource"
									value="${ item.imageSource }" /> <input type="hidden"
									name="imageSourceOri" value="${ item.imageSourceOri }" /> <input
									type="hidden" name="photoId" value="${item.photoId}" /> <input
									type="hidden" name="title" value="${item.title}" />
								<fieldset>
									<label for="name">Share Comment!</label> <input type="text"
										size="10" name="comment" id="name"
										class="text ui-widget-content ui-corner-all">
								</fieldset>
								<button class="link-button green" type="submit" name="button"
									value="Comment">
									<img src="/Task8/img/social/twitter_bird.png">
								</button>
								<button class="link-button blue" id="like" name="button"
									value="Like">
									<img src="/Task8/img/social/heart.png">
								</button>
								<button class="link-button red" id="dislike" name="button"
									value="Dislike">
									<img src="/Task8/img/social/feed_burner.png">
								</button>
							</form>
						</div>
					</figcaption>
				</figure>
			</c:forEach>
		</div>
		<!-- ENDS Filter container -->
	</div>
	<!-- ENDS WRAPPER -->

	<div id="myCarousel" class="carousel slide" data-ride="carousel"
		style="margin-left: 173px;">
		<div class="carousel-inner">
			<div class="item">
				<div id="TopCommentUsers" style="width: 600px; height: 400px;"></div>
			</div>
		</div>
	</div>
</div>
<!-- ENDS MAIN -->

<jsp:include page="googleWithWebsite.jsp" />
</body>
</html>
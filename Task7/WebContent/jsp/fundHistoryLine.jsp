<%@ page contentType="text/xml; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bean" class="task7.databeans.FundGraphBean" />

<anychart>
	<settings>
		<animation enabled="True" />
	</settings>
	<charts>
		<chart plot_type="CategorizedVertical">
      		<chart_settings>
        		<title>
          			<text>History Price for <c:out value="${fundName}" /></text>
          			<background enabled="false" />
        		</title>
        		<legend enabled="true">
		          <title enabled="false" />
		          <icon>
		            <marker enabled="true" type="%MarkerType" size="8" />
		          </icon>
		        </legend>
		        <axes>
		          <x_axis tickmarks_placement="Center">
		            <title>
		              <text>Date</text>
		            </title>
		          </x_axis>
		          <y_axis>
		            <title>
		              <text>Price</text>
		            </title>
		            <labels>
		              <format><![CDATA[{%Value}{numDecimals:0}]]></format>
		            </labels>
		            <minor_grid enabled="false" />
		            <major_grid interlaced="false" />
		            <minor_tickmark enabled="false" />
		          </y_axis>
		        </axes> 
      		</chart_settings>
      	<data_plot_settings default_series_type="Spline">
        <line_series>
			<marker_settings>
            	<marker size="8" />
            	<states>
              	<hover>
                	<marker size="12" />
              	</hover>
      		    </states>
          	</marker_settings>
            <tooltip_settings enabled="true"/>   
        </line_series>
	</data_plot_settings>

	<data>  
		<series name="${fundName}">
			<c:forEach var="item" items="${bean.reportsList}" varStatus="count">
				<c:if test="${ item.name == fundName }"> 
				<point name="${item.period} " y="${item.value / 100.0}" />
          		</c:if>
			</c:forEach>
        </series>
    </data>
</chart>
</charts>
</anychart>
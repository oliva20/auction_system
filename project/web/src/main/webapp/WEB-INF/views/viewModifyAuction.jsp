<%@page import="org.solent.com504.project.model.auction.dto.AuctionType"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.solent.com504.project.model.auction.service.AuctionService"%>
<%@page import="org.solent.com504.project.model.auction.dto.Auction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var = "selectedPage" value = "home" scope="request" />
<!-- start of home.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />

<%
    boolean createAuction = Boolean.valueOf((String) request.getAttribute("createAuction"));
%>

<head>
    <!-- Custom css -->
    <link href="./resources/css/custom.css" rel="stylesheet">
</head>

<main role="main" class="container">
    <h1>Auction Modification</h1>
    
    <%if(createAuction){%>
    
    <tabel class="tabel">
        <thead>
        </thead>
        <!-- TODO create auction functionality -->
        <tbody>
            <tr>
                <td><label>Auction start date: </label></td>
                <td><input type="date" name="startTime"></td>
            </tr>
            <tr>
                <td><label>Auction Type: </label></td>
                <td>
                    <select name="auctionType">
                        <% for(AuctionType type : AuctionType.values()){%>
                            <option value="<%= type.toString() %>"><%= type.toString() %></option>
                        <%}%>
                    </select>
                </td>
            </tr>
        </tbody>
    </tabel>
    <%}%>
    
</main>

<jsp:include page="footer.jsp" />
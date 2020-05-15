<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.solent.com504.project.model.auction.service.AuctionService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="org.solent.com504.project.model.auction.dto.Auction"%>
<c:set var = "selectedPage" value = "home" scope="request" />
<!-- start of home.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />
<style>
    #popup{
    display: none;
    border: 1px solid black; 
    width: 50%;
    height: 250px;
    padding-left: 15px;
}
</style>
<head>
    <!-- Custom css -->
    <link href="./resources/css/custom.css" rel="stylesheet">
</head>

<%  
    AuctionService auctionServ = (AuctionService) request.getAttribute("auctionService"); 
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy | hh:mm aa ");
%>

<!-- Begin page content -->
<main role="main" class="container">
    <H1>Home</H1>
    <% for(Auction auction : auctionServ.getAuctions()){ %>
    <div class="auctionContainer">
        <p>Auction Start: <%=df.format(auction.getStartTime()) %></p>
        <p>Auction Type: <%=auction.getType().toString()%></p>
        <p>Auction Lot amount: <%=auction.getLots().size()%></p>
        <button onclick="document.getElementById('popup').style.display='block'" class="btn btn-primary">View Lots</button>
        <p>
            
        </p>
        <div id="popup">
            <p></p>
            <button onclick="document.getElementById('popup').style.display='none'" class="btn btn-primary">Close</button>
            <p></p>
            <p>  No of lots: <%=auction.getLots().size()%> </p>
            <p>  Reserved price: £10</p>
            <p>  Flower grade: A</p>
            <p>  Flower Lifespan: 5 days</p>
            <p>  Quantity: 100</p>
            <input id="bid_amount" type="number" placeholder="£">
            <button class="btn btn-primary">BID</button>
        </div>
    </div>
    <%}%>
</main>

<jsp:include page="footer.jsp" />

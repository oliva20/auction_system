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
    <% for(Auction auction : auctionServ.getAuctionList()){ %>
    <div class="auctionContainer">
        <p>Auction Start: <%=df.format(auction.getStartTime()) %></p>
        <p>Auction Type: <%=auction.getAuctionType().toString()%></p>
        <p>Auction Lot amount: <%=auction.getLots().size()%></p>
        
        <form method="POST" action="./viewAuctionLots">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" form="auctionData"/>
            <input type="hidden" name="auctionuuid" value="<%= auction.getAuctionuuid() %>" />
            <input class="btn btn-success" type="submit" value="View Lots"/>
        </form>
    </div>
    <%}%>
</main>

<jsp:include page="footer.jsp" />

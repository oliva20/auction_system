<%-- 
    Document   : viewAuctionLots
    Created on : 11-May-2020, 22:01:39
    Author     : Josh
--%>

<%@page import="org.solent.com504.project.model.auction.dto.Lot"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.solent.com504.project.model.auction.service.AuctionService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./resources/css/custom.css" rel="stylesheet">
        <title>View Auction Lots</title>
    </head>
    
    <%  
    AuctionService auctionServ = (AuctionService) request.getAttribute("auctionService"); 
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy | hh:mm aa ");
    %>
    
    <main role="main" class="container">
        <h1>Auction Lots</h1>
        <% for(Lot lots : auctionServ.getAuctionLots((String) request.getAttribute("selectedAuction"))){ %>
        <div class="auctionContainer">
            <p>Lot Flower Name: <% try { %>
                <%= lots.getFlowerType().getCommonName() %>
                <% }catch(NullPointerException e){%>
                <span>No Flower Name</span>
                <%}%>
            </p>
            <p>Lot flower quantity: <%= lots.getQuantity() %></p>
            <p>Lot status: <%= lots.getLotStatus() %></p>
            <p>Lot current bid price: <%= lots.getCurrentPrice()%></p>
            <p>Lot reserved price: <%= lots.getReservePrice() %></p>
            <form action="#" method="POST">
                <input class="btn btn-primary" type="submit" value="Place Bid"/>
                <input type="text" placeholder="£0.00" />
            </form>
        </div>
        <% } %> 
    </main>
</html>

<jsp:include page="footer.jsp" />
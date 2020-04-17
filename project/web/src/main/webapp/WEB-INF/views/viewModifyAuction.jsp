<%@page import="org.solent.com504.project.model.auction.dto.Lot"%>
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
    boolean createAuction = Boolean.valueOf((String) request.getAttribute("createAuction")); // Variable decides whether or not the auction has already been made or if it should be made
    Auction blankAuction = (Auction) request.getAttribute("blankAuction");
%>

<head>
    <!-- Custom css -->
    <link href="./resources/css/custom.css" rel="stylesheet">
</head>

<main role="main" class="container">
    <h1>Blank auction: <% if(blankAuction.getLots() == null){ %>
    Null
    <%}else{%>
    <%= blankAuction.getLots().size()%>
    <%}%>
    </h1>
    
    <%if(createAuction){%>
    <h1>Auction Creation</h1>
    
    <form id="auctionData" method="POST" action="./saveAuction"></form>
    <table class="table">
        <tr>
            <th><label>Auction Start Date</label></th>
            <td><input type="date" name="startTime" form="auctionData"></td>
        </tr>
        <tr>
          <td><label>Auction Type: </label></td>
          <td>
              <select name="auctionType" form="auctionData">
                <% for(AuctionType type : AuctionType.values()){%>
                    <option value="<%= type.toString() %>"><%= type.toString() %></option>
                <%}%>
            </select>
          </td>
        </tr>
        <tr>
            <td><label>Auction Lot Management: </label></td>
            <td>
                <form action="./viewModifyLot" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="createLot" value="true">
                    <button class="btn" type="submit">Create Lot</button>
                </form>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="border: none;">
                <tabel class="tabel">
                    <tr>
                        <td>Duration (Milliseconds)</td>
                        <td>Reserved Price</td>
                        <td>Highest Price</td>
                        <td>Seller</td>
                        <td>Quantity</td>
                    </tr>
                    <% 
                        if(blankAuction.getLots() != null){
                            for(Lot lot: blankAuction.getLots()){
                    %>
                    <tr>
                        <td><%= lot.getLotDuraton() %></td>
                        <td><%= lot.getReservePrice() %></td>
                        <td><%= lot.getSoldPrice() %></td>
                        <td><%= lot.getSeller().getFirstName() %></td>
                        <td><%= lot.getQuantity() %></td>
                    </tr>
                    <%  }
                    }%>
                </tabel>
            </td>
        </tr>
        <tr>
            <%if(blankAuction.getLots() != null){ %>
            <td>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" form="auctionData"/>
                <input class="btn btn-success" type="submit" value="Submit" form="auctionData"/>
            </td>
            <%}else{%>
            <td><input class="btn btn-success" type="submit" value="Submit" disabled/></td>
            <%}%>
        </tr>
    </table>
    <%}else{%>
    <h1>Auction Modification</h1>
    
    <%}%>
    
</main>

<jsp:include page="footer.jsp" />
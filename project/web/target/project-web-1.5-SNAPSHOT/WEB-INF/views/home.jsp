<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page import="org.solent.com504.project.model.party.dto.Party"%>
<%@page import="org.solent.com504.project.model.user.dto.User"%>
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
    User user = (User) request.getAttribute("user");
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
        
        <%
            boolean hasRegistered = false;
            for(Party aucParty : auction.getRegisteredPartys()){
                for(Party usrParty : user.getParties()){
                    if(usrParty.getUuid().equals(aucParty.getUuid())){ // Check if any of the user parties have the same universal unique identification as any of the registered parties in the auction
                        %>
                        <button class="btn btn-primary">View Lots</button> 
                        <div style="float: right;">
                            <p style="display: inline;">Registered as: </p>
                            <label style="display: inline;"><%= usrParty.getFirstName() %> <%= usrParty.getSecondName() %></label>
                        </div>
                        <%
                        hasRegistered = true;
                        break;
                    }
                }
            }
            if(!hasRegistered){
                %>
                <form action="./registerToAuction" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="auctionuuid" value="<%=auction.getAuctionuuid() %>"/>
                    <input class="btn btn-primary" value="Register to Auction" type="submit" />
                    <div style="float: right;">
                        <p style="display: inline;">Register to auction with: </p>
                        <select name="partyuuid" style="display: inline;">
                        <% for(Party usrParty : user.getParties()){%>
                            <option value="<%= usrParty.getUuid() %>"><%= usrParty.getFirstName() %> <%= usrParty.getSecondName() %></option>
                        <%}%>
                    </select>
                    </div>
                </form>
                
                
                <%
            }
        %>
    </div>
    <%}%>
</main>

<jsp:include page="footer.jsp" />

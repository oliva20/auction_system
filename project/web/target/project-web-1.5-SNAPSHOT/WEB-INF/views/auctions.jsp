<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.solent.com504.project.model.user.dto.User"%>
<%@page import="org.solent.com504.project.model.user.dto.UserRoles"%>
<%@page import="org.solent.com504.project.model.auction.dto.Auction"%>
<%@page import="org.solent.com504.project.model.auction.service.AuctionService"%>
<c:set var = "selectedPage" value = "admin" scope="request"/>
<jsp:include page="header.jsp" />
<!-- start of partys.jsp selectedPage=${selectedPage}-->

<%
    AuctionService auctionServ = (AuctionService) request.getAttribute("auctionService");
%>


<!-- Begin page content -->
<main role="main" class="container">

    <div>
        <h1>Manage Auctions</h1>
        <!--<p>showing ${partyListSize} partys: </p>-->
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Auction Type</th>
                    <th scope="col">Start Time</th>
                    <th scope="col">Lot amounts</th>
                </tr>
            </thead>
            <tbody>
                <%for(Auction auction : auctionServ.getAuctions()){%>
                    <tr>
                        <td><%=auction.getId()%></td>
                        <td><%=auction.getType().toString() %></td>
                        <td><%=auction.getStartTime() %></td>
                        <td><%=auction.getLots().size() %></td>
                        <td>
                            <!-- send user to modify the current auction TODO create auction modify jsp -->
                            <form action="./viewModifyAuction" method="POST">
                                <input type="hidden" name="auctionId" value="<%=auction.getId() %>">
                                <button class="btn" type="submit">Modify Auction</button>
                            </form>
                        </td>
                    </tr>
                <%}%>

            </tbody>
        </table>
        <form action="./viewModifyAuction" method="POST">
            <!-- createAuction = "true" creates a new auction -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="createAuction" value="true">
            <button class="btn" type="submit" >Add Auction</button>
        </form> 
    </div>
</main>

<jsp:include page="footer.jsp" />

<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var = "selectedPage" value = "home" scope="request" />
<!-- start of home.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />

<head>
    <!-- Custom css -->
    <link href="./resources/css/custom.css" rel="stylesheet">
</head>

<%   
    boolean createLot = Boolean.valueOf((String) request.getAttribute("createLot")); 
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy | hh:mm aa ");
%>

<!-- Begin page content -->
<main role="main" class="container">
    
    <%if(createLot){%>
        <H1>Lot Creation</H1>
        <form action="./viewModifyAuction" method="POST" id="lotForm"></form>
            <table class="table">
                <tr>
                    <td><label>Lot Duration (HH:MM): </label></td>
                    <td><input type="time" value="00:00" form="lotForm" name="lotDuration" required/></td>
                </tr>
                <tr>
                    <td><label>Reserve Price: </label></td>
                    <td>£<input type="number" min="0.00" step="0.01" placeholder="0.00" form="lotForm" name="lotReservePrice" required></td>
                </tr>
                <tr>
                    <td><label>Sold Price: </label></td>
                    <td>£<input type="number" min="0.00" step="0.01" placeholder="0.00" form="lotForm" name="lotSoldPrice" required></td>
                </tr>
                <tr>
                    <td><label>Flower: </label></td> <!-- Since the other team are dealing with sellers and their flowers, we will only refer to them by common name -->
                    <td><input type="text" placeholder="Rose" form="lotForm" name="lotFlower" required></td>
                </tr>
                <tr>
                    <td><label>Flower Seller: </label></td>
                    <td><input type="text" placeholder="uuid" form="lotForm" name="lotSelleruuid" required></td>
                </tr>
                <tr>
                    <td><label>Flower quantity: </label></td>
                    <td><input type="number" min="0" step="1" placeholder="0" form="lotForm" name="lotQuantity" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="createLot" form="lotForm" value="<%=createLot%>"/>
                        <input type="hidden" name="createAuction" form="lotForm" value="true"/>
                        <input type="hidden" form="lotForm" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" value="Submit" form="lotForm" class="btn-primary btn"/>
                    </td>
                </tr>
            </table>
    <%}else{%>
        <H1>Lot Management</H1>
    <%}%>
</main>

<jsp:include page="footer.jsp" />

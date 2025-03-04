<h1>Your Wishlist</h1>
<table>
    <thead>
        <tr>
            <th>Product Name</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${wishlist}">
            <tr>
                <td>${product.name}</td>
                <td>
                    <form action="RemoveFromWishlist" method="post">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="submit" value="Remove">
                    </form>
                    <form action="MoveToCart" method="post">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="submit" value="Move to Cart">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

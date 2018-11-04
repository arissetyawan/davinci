<%-- 
    Document   : form
    Created on : Oct 31, 2018, 5:25:15 PM
    Author     : ASUS
--%>
    <table class="table">  
        <tr>
            <td><b>Name Category</b></td>
            <td>
                <div class="form-group">
                <input class="form-control"  type="text" id="name" name="name" value="<c:out value='${category.name}' />" />
                </div>
            </td>
        </tr>  
        <tr >
            <td><b>Parent Id</b></td>
            <td>
                <div class="form-group">
                    <input class="form-control" type="text" id="category_id" name="category_id" value="<c:out value='${category.category_id}' />" />
<!--                    <select class="form-control" id="category_id" name="st Of Parent Category<<<</bcategory_id" >
                        <option value=''><b>>>>Li></option>
                        <c:forEach var="categories" items="${categories}">
                            <option value="<c:out value='${categories.category_id}'/> "><c:out value="${categories.name}"/></option>
                        </c:forEach>
                    </select>-->
                </div>
            </td>
        </tr>  
        <tr>
            <td><b>Description</b></td>
            <td>
                <div class="form-group">
                    <textarea class="form-control" rows="5" name="description" id="description"><c:out value='${category.description}'/></textarea>
                </div> 
            </td>
        </tr>  
        <tr>
            <td></td>
            <td colspan="2"><input class="btn btn-primary" style="background-color: green;" type="submit" value="Save"  /></td>
        </tr>  
    </table>
    
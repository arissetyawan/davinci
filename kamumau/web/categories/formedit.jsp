<%-- 
    Document   : formedit
    Created on : Nov 4, 2018, 9:53:33 PM
    Author     : ASUS
--%>
<table class="table">  
        <tr>
            <td><b>Name Category</b></td>
            <td>
                <div class="form-group">
                <input class="form-control"  type="text" id="name" name="name" value="<c:out value='${categories.getName()}' />" />
                </div>
            </td>
        </tr>  
        <tr >
            <td><b>Parent Id</b></td>
            <td>
                <div class="form-group">
                    <input class="form-control" type="text" id="category_id" name="category_id" value="<c:out value='${categories.getCategory_id()}' />" />
                   
                </div>
            </td>
        </tr>  
        <tr>
            <td><b>Description</b></td>
            <td>
                <div class="form-group">
                    <textarea class="form-control" rows="5" name="description" id="description"><c:out value='${categories.getDescription()}'/></textarea>
                </div> 
            </td>
        </tr>  
        <tr>
            <td></td>
            <td colspan="2"><input class="btn btn-primary" style="background-color: green;" type="submit" value="Save"  /></td>
        </tr>  
    </table>
    
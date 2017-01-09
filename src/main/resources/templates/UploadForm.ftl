<!DOCTYPE html>
<html lang="en">
<body>
    <div>
        <h1> Files</h1>
        <form method="POST" enctype="multipart/form-data" action="/files">
            <table>
                <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
                <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
            </table>
        </form>
    </div>
    <div>
        <ul>
        <#list files as file>
            <li>
                <a href="${file}">${file}</a>
            </li>
        </#list>
        </ul>
</div>
</body>
</html>
<!DOCTYPE html>

<html lang="en">

<body>
	<H1>Home </H1>
	<table border="1">
		<tr>
            <th>Bucket Name</th>
            <th>Name</th>
            <th>Size</th>
		</tr>
		<#list s3Objects as s3Oject>
		    <tr>
                <td>${s3Oject.bucketName}</td>
                <td>${s3Oject.key}</td>
                <td>${s3Oject.size}</td>
                <td><a href="/s3home/delete?bucket=${s3Oject.bucketName}&key=${s3Oject.key}">Delete</a> </td>
			</tr>
		</#list>
	</table>
    <a href="/s3home/deleteAll?bucket=${bucketName}">Delete All</a>

    <div>
        <h1>Files to upload</h1>
        <form method="POST" enctype="multipart/form-data" action="/s3home/upload">
            <table>
                <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
                <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
            </table>
        </form>
    </div>
</body>

</html>

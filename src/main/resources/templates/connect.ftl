<html>
<head>
    <title>代码生成</title>
</head>
<body>
ip:<input type="text" id="ip" /><br></br>
db:<select id="db">
    <option value="1">mysql</option>
    <option value="2">oracle</option>
    <option value="3">sqlserver</option>
</select><br></br>
port:<input type="text" id="port" /><br></br>
dbName:<input type="text" id="dbName" /><br></br>
username:<input type="text" id="username" /><br></br>
password:<input type="text" id="password" /><br></br>
<button onclick="connect()">连接</button>
<hr/>
<div id="tableDiv">
    basepackage:<input type="text" id="basepackage" /><br></br>
    author:<input type="text" id="author" /><br></br>
</div>
<button onclick="generate()">生成</button>
<script src="./static/jquery-1.7.2.min.js"></script>
<script src="./static/connect.js"></script>
</body>
</html>
